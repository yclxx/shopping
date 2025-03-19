package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.lock.LockInfo;
import com.baomidou.lock.LockTemplate;
import com.baomidou.lock.executor.RedissonLockExecutor;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Coupon;
import org.dromara.shopping.domain.RefundCouponLog;
import org.dromara.shopping.domain.bo.RefundCouponLogBo;
import org.dromara.shopping.domain.vo.CouponVo;
import org.dromara.shopping.domain.vo.OrderVo;
import org.dromara.shopping.domain.vo.RefundCouponLogVo;
import org.dromara.shopping.mapper.CouponMapper;
import org.dromara.shopping.mapper.RefundCouponLogMapper;
import org.dromara.shopping.service.IRefundCouponLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;

/**
 * 返还记录Service业务层处理
 *
 * @author yzg
 * @date 2025-01-15
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class RefundCouponLogServiceImpl implements IRefundCouponLogService {

    private final RefundCouponLogMapper baseMapper;
    private final CouponMapper couponMapper;
    private final LockTemplate lockTemplate;

    /**
     * 查询返还记录
     */
    @Override
    public RefundCouponLogVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询返还记录列表
     */
    @Override
    public TableDataInfo<RefundCouponLogVo> queryPageList(RefundCouponLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<RefundCouponLog> lqw = buildQueryWrapper(bo);
        Page<RefundCouponLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询返还记录列表
     */
    @Override
    public List<RefundCouponLogVo> queryList(RefundCouponLogBo bo) {
        LambdaQueryWrapper<RefundCouponLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<RefundCouponLog> buildQueryWrapper(RefundCouponLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<RefundCouponLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCollectiveNumber() != null, RefundCouponLog::getCollectiveNumber, bo.getCollectiveNumber());
        lqw.eq(bo.getNumber() != null, RefundCouponLog::getNumber, bo.getNumber());
        lqw.eq(bo.getCouponId() != null, RefundCouponLog::getCouponId, bo.getCouponId());
        lqw.eq(StringUtils.isNotBlank(bo.getRedeemCode()), RefundCouponLog::getRedeemCode, bo.getRedeemCode());
        lqw.eq(StringUtils.isNotBlank(bo.getLogType()), RefundCouponLog::getLogType, bo.getLogType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), RefundCouponLog::getStatus, bo.getStatus());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
                RefundCouponLog::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    private List<RefundCouponLogVo> queryListByCollectiveNumber(Long collectiveNumber) {
        LambdaQueryWrapper<RefundCouponLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(RefundCouponLog::getCollectiveNumber, collectiveNumber);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 新增返还记录
     */
    @Transactional
    @Override
    public Boolean insertByBo(RefundCouponLogBo bo) {
        LockInfo lock = lockTemplate.lock("refundCoupon:" + bo.getCollectiveNumber(), 30000L, 5000L, RedissonLockExecutor.class);
        if (null == lock) {
            throw new ServiceException("操作频繁");
        }
        try {
            // 查询是否有做过返还
            List<RefundCouponLogVo> refundCouponLogVos = queryListByCollectiveNumber(bo.getCollectiveNumber());
        } finally {
            lockTemplate.releaseLock(lock);
        }
        return true;
    }

    private void multiRefund(RefundCouponLogBo bo, List<RefundCouponLogVo> refundCouponLogVos, List<OrderVo> orderVos, List<CouponVo> couponVos) {
        if (null == bo.getNumber()) {
            // 多个，必须指定订单
            log.info("有多个订单，子订单号不能为空，大订单：{}", bo.getCollectiveNumber());
            return;
        }

        OrderVo refundOrderVo = null;

        // 退款成功订单
        Map<Long, OrderVo> refundSuccessOrderVoMap = new HashMap<>();
        // 退款中的订单
        Map<Long, OrderVo> refundHandleOrderVoMap = new HashMap<>();

        for (OrderVo orderVo : orderVos) {
            if (bo.getNumber().equals(orderVo.getNumber())) {
                // 返还指定订单
                refundOrderVo = orderVo;
            }
            if ("5".equals(orderVo.getStatus())) {
                refundSuccessOrderVoMap.put(orderVo.getNumber(), orderVo);
            }
            if ("4".equals(orderVo.getStatus()) || "6".equals(orderVo.getStatus())) {
                refundHandleOrderVoMap.put(orderVo.getNumber(), orderVo);
            }
        }

        if (null == refundOrderVo) {
            // 多个，必须指定订单
            log.info("订单不存在，或与大订单不匹配,大订单号：{}，子订单号：{}", bo.getCollectiveNumber(), bo.getNumber());
            throw new ServiceException("订单不存在，或与大订单不匹配");

        }
        if (!"5".equals(refundOrderVo.getStatus())) {
            log.info("订单不是退款成功的状态，不做返还，订单号：{},订单状态：{}", refundOrderVo.getNumber(), refundOrderVo.getStatus());
            return;
        }

        if (ObjectUtil.isNotEmpty(refundCouponLogVos)) {
            // 有订单做过返还，直接继续做返还操作
            for (RefundCouponLogVo refundCouponLogVo : refundCouponLogVos) {
                if (refundCouponLogVo.getNumber().equals(refundOrderVo.getNumber())) {
                    // 订单已做过返还，直接返回
                    log.info("订单{}已做过返还，不重复操作", refundOrderVo.getNumber());
                    return;
                }
            }
            createCoupon(couponVos, refundOrderVo);
        } else {
            // 第一次处理，校验所有订单是否都已申请退款
            int refundOrderCount = refundSuccessOrderVoMap.size() + refundHandleOrderVoMap.size();
            if (refundOrderCount == orderVos.size()) {
                if (refundSuccessOrderVoMap.size() == orderVos.size()) {
                    // 全部退款成功了，返还 否则不操作，等待退款完成
                    backCoupon(couponVos, bo.getCollectiveNumber(), null);
                }
            } else {
                // 有订单没有申请退款 按照订单补发优惠券
                if (!refundSuccessOrderVoMap.isEmpty()) {
                    for (OrderVo orderVo : refundSuccessOrderVoMap.values()) {
                        createCoupon(couponVos, orderVo);
                    }
                }
            }
        }
    }

    /**
     * 补发优惠券
     */
    private void createCoupon(List<CouponVo> couponVos, OrderVo orderVo) {
        if (orderVo.getReducedPrice().compareTo(BigDecimal.ZERO) <= 0) {
            log.info("订单{},优惠金额小于0，优惠金额：{}", orderVo.getNumber(), orderVo.getReducedPrice());
            return;
        }
        // 查询一下，防止重复补发
        if (selectCountByNumber(orderVo.getNumber()) > 0) {
            log.info("订单{}已做过返还，不重复操作", orderVo.getNumber());
            return;
        }

        CouponVo couponVo = getMaxDateCouponVo(couponVos);

        Coupon coupon = new Coupon();
        coupon.setCouponName(orderVo.getReducedPrice().toPlainString() + "元优惠券");
        coupon.setRedeemCode(RandomUtil.randomNumbers(13));
        coupon.setCouponAmount(orderVo.getReducedPrice());
        coupon.setMinAmount(BigDecimal.ZERO);
        coupon.setCouponType(couponVo.getCouponType());
        coupon.setPeriodOfStart(couponVo.getPeriodOfStart());
        coupon.setPeriodOfValidity(couponVo.getPeriodOfValidity());
        coupon.setUseStatus("1");
        coupon.setGenTime(new Date());
        coupon.setCouponDescription(couponVo.getCouponDescription());
        coupon.setUserAddTime(new Date());
        coupon.setActionNo(couponVo.getActionNo());
        coupon.setCouponImage(couponVo.getCouponImage());
        coupon.setUserId(couponVo.getUserId());
        coupon.setConversionStartDate(couponVo.getConversionStartDate());
        coupon.setConversionEndDate(couponVo.getConversionEndDate());
        coupon.setPlatformKey(couponVo.getPlatformKey());
        couponMapper.insert(coupon);

        RefundCouponLog add = new RefundCouponLog();
        add.setCollectiveNumber(orderVo.getCollectiveNumber());
        add.setNumber(orderVo.getNumber());
        // 单个订单 走返还
        add.setLogType("1");
        add.setCouponId(coupon.getCouponId());
        add.setRedeemCode(coupon.getRedeemCode());
        add.setStatus("2");

        baseMapper.insert(add);
    }

    /**
     * 获取有效期最长的优惠券
     *
     * @param couponVos 优惠券列表
     * @return 优惠券
     */
    private CouponVo getMaxDateCouponVo(List<CouponVo> couponVos) {
        return couponVos.stream().max(Comparator.comparing(CouponVo::getPeriodOfValidity)).orElse(couponVos.get(0));
    }

    private void singleRefund(RefundCouponLogBo bo, List<RefundCouponLogVo> refundCouponLogVos, List<OrderVo> orderVos, List<CouponVo> couponVos) {
        if (ObjectUtil.isNotEmpty(refundCouponLogVos)) {
            log.info("大订单{}，已做过返还，不重复操作", bo.getCollectiveNumber());
            return;
        }

        // 订单信息
        OrderVo orderVo = orderVos.get(0);
        if (!"5".equals(orderVo.getStatus())) {
            log.info("订单不是退款成功的状态，不做返还，订单号：{},订单状态：{}", orderVo.getNumber(), orderVo.getStatus());
            return;
        }
        backCoupon(couponVos, bo.getCollectiveNumber(), bo.getNumber());
    }

    /**
     * 优惠券返还
     *
     * @param couponVos        优惠券信息
     * @param collectiveNumber 大订单号
     * @param number           小订单号
     */
    private void backCoupon(List<CouponVo> couponVos, Long collectiveNumber, Long number) {
        List<RefundCouponLog> refundCouponLogs = new ArrayList<>();
        List<Long> couponIds = new ArrayList<>();

        // 修改优惠券状态为未使用
        for (CouponVo couponVo : couponVos) {
            RefundCouponLog add = new RefundCouponLog();
            add.setCollectiveNumber(collectiveNumber);
            add.setNumber(number);
            // 单个订单 走返还
            add.setLogType("0");
            add.setCouponId(couponVo.getCouponId());
            add.setRedeemCode(couponVo.getRedeemCode());
            add.setStatus("2");

            refundCouponLogs.add(add);

            couponIds.add(couponVo.getCouponId());
        }
        // 批量修改
        Coupon coupon = new Coupon();
        coupon.setUseStatus("1");
        int update = couponMapper.update(coupon, new LambdaQueryWrapper<Coupon>().in(Coupon::getCouponId, couponIds));

        // 批量新增
        boolean b = baseMapper.insertBatch(refundCouponLogs);
        if (!b || update < 1) {
            throw new ServiceException("返还失败");
        }
    }

    private List<CouponVo> queryCouponVoListByNumber(List<Long> numbers) {
        LambdaQueryWrapper<Coupon> lqw = Wrappers.lambdaQuery();
        lqw.in(Coupon::getNumber, numbers);
        lqw.eq(Coupon::getUseStatus, "3");

        return couponMapper.selectVoList(lqw);
    }

    private Long selectCountByNumber(Long number) {
        LambdaQueryWrapper<RefundCouponLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(RefundCouponLog::getNumber, number);

        return baseMapper.selectCount(lqw);
    }

    /**
     * 修改返还记录
     */
    @Override
    public Boolean updateByBo(RefundCouponLogBo bo) {
        RefundCouponLog update = BeanUtil.toBean(bo, RefundCouponLog.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除返还记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
