package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.DateUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.*;
import org.dromara.shopping.domain.bo.CouponBo;
import org.dromara.shopping.domain.vo.CouponVo;
import org.dromara.shopping.domain.vo.ProductVo;
import org.dromara.shopping.mapper.CouponMapper;
import org.dromara.shopping.mapper.ProductCouponMapper;
import org.dromara.shopping.mapper.ProductMapper;
import org.dromara.shopping.service.ICouponService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 优惠券Service业务层处理
 *
 * @author yzg
 * @date 2023-10-16
 */
@RequiredArgsConstructor
@Service
public class CouponServiceImpl implements ICouponService {

    private final CouponMapper baseMapper;
    private final ProductCouponMapper productCouponMapper;
    private final ProductMapper productMapper;

    /**
     * 查询优惠券
     */
    @Override
    public CouponVo queryById(Long couponId) {
        return baseMapper.selectVoById(couponId);
    }

    @Override
    public Long queryNumberByActionNo(String actionNo) {
        LambdaQueryWrapper<Coupon> lqw = Wrappers.lambdaQuery();
        lqw.eq(Coupon::getActionNo, actionNo);
        return baseMapper.selectCount(lqw);
    }

    /**
     * 查询优惠券列表
     */
    @Override
    public TableDataInfo<CouponVo> queryPageList(CouponBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Coupon> lqw = buildQueryWrapper(bo);
        Page<CouponVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询优惠券列表
     */
    @Override
    public TableDataInfo<ProductVo> queryProductPageList(CouponBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Product> lqw = Wrappers.lambdaQuery();
        lqw.last("AND product_id IN ( SELECT product_id FROM `t_product_coupon` WHERE coupon_id =" + bo.getCouponId() + " );");
        Page<ProductVo> result = productMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询优惠券列表
     */
    @Override
    public List<CouponVo> queryList(CouponBo bo) {
        LambdaQueryWrapper<Coupon> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    @Async
    @Override
    public void addCoupon(Action action, Long number, List<ProductAction> productActions) {
        List<Coupon> addList = new ArrayList<>();
        List<ProductCoupon> productCoupons = new ArrayList<>();
        String longUrl = "https://discounts.yzgnet.com/discounts-ui/#/pages/template/couponIndex?code=";
        for (int i = 0; i < number; i++) {
            Coupon coupon = new Coupon();
            coupon.setCouponId(IdUtil.getSnowflakeNextId());
            coupon.setRedeemCode(RandomUtil.randomNumbers(13));
            coupon.setCouponName(action.getCouponName());
            coupon.setPlatformKey(action.getPlatformKey());
            coupon.setUseStatus("0");
            coupon.setGenTime(DateUtils.getNowDate());
            coupon.setActionNo(action.getActionNo());
            if (ObjectUtil.isNotEmpty(action.getCouponAmount()))
                coupon.setCouponAmount(action.getCouponAmount());
            if (ObjectUtil.isNotEmpty(action.getMinAmount()))
                coupon.setMinAmount(action.getMinAmount());
            if (StringUtils.isNotEmpty(action.getCouponType()))
                coupon.setCouponType(action.getCouponType());
            if (ObjectUtil.isNotEmpty(action.getPeriodOfStart()))
                coupon.setPeriodOfStart(action.getPeriodOfStart());
            //此处增加判断
            if (ObjectUtil.isNotEmpty(action.getCouponExpiryType()) && "1".equals(action.getCouponExpiryType())){
                Date expiryDate = null;
                if (NumberUtil.isLong(action.getCouponExpiryDate())) {
                    int addDay = Integer.parseInt(action.getCouponExpiryDate());
                    if (addDay < 1) {
                        // TODO 填写当天的结束时间
                        expiryDate = DateUtil.endOfDay(new Date()).offset(DateField.MILLISECOND, -999).toJdkDate();
                    } else {
                        expiryDate = DateUtil.endOfDay(DateUtil.offsetDay(new Date(), addDay)).offset(DateField.MILLISECOND, -999).toJdkDate();
                    }
                }
                coupon.setPeriodOfValidity(expiryDate);

            }else if (ObjectUtil.isNotEmpty(action.getCouponExpiryType()) && "0".equals(action.getCouponExpiryType())){
                coupon.setPeriodOfValidity(action.getPeriodOfValidity());
            }else {
                coupon.setPeriodOfValidity(action.getPeriodOfValidity());
            }

            if (StringUtils.isNotEmpty(action.getCouponDescription()))
                coupon.setCouponDescription(action.getCouponDescription());
            if (StringUtils.isNotEmpty(action.getCouponImage())) coupon.setCouponImage(action.getCouponImage());
            if (ObjectUtil.isNotEmpty(action.getConversionStartDate()))
                coupon.setConversionStartDate(action.getConversionStartDate());
            if (ObjectUtil.isNotEmpty(action.getConversionEndDate()))
                coupon.setConversionEndDate(action.getConversionEndDate());
            if (ObjectUtil.isNotEmpty(action.getAutoPay()))
                coupon.setAutoPay(action.getAutoPay());
            addList.add(coupon);
            if (ObjectUtil.isNotEmpty(productActions)) {
                for (ProductAction productAction : productActions) {
                    ProductCoupon productCoupon = new ProductCoupon();
                    productCoupon.setCouponId(coupon.getCouponId());
                    productCoupon.setProductId(productAction.getProductId());
                    productCoupons.add(productCoupon);
                }
            }
        }
        if (ObjectUtil.isNotEmpty(addList)) {
            baseMapper.insertBatch(addList);
            if (ObjectUtil.isNotEmpty(productCoupons)) {
                productCouponMapper.insertBatch(productCoupons);
            }
        }
    }

    private LambdaQueryWrapper<Coupon> buildQueryWrapper(CouponBo bo) {
        LambdaQueryWrapper<Coupon> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getCouponName()), Coupon::getCouponName, bo.getCouponName());
        lqw.eq(StringUtils.isNotBlank(bo.getRedeemCode()), Coupon::getRedeemCode, bo.getRedeemCode());
        lqw.eq(bo.getCouponAmount() != null, Coupon::getCouponAmount, bo.getCouponAmount());
        lqw.eq(bo.getMinAmount() != null, Coupon::getMinAmount, bo.getMinAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getCouponType()), Coupon::getCouponType, bo.getCouponType());
        lqw.eq(StringUtils.isNotBlank(bo.getUseStatus()), Coupon::getUseStatus, bo.getUseStatus());
        lqw.eq(bo.getUseTime() != null, Coupon::getUseTime, bo.getUseTime());
        lqw.eq(StringUtils.isNotBlank(bo.getNumber()), Coupon::getNumber, bo.getNumber());
        lqw.eq(bo.getUserAddTime() != null, Coupon::getUserAddTime, bo.getUserAddTime());
        lqw.eq(StringUtils.isNotBlank(bo.getActionNo()), Coupon::getActionNo, bo.getActionNo());
        lqw.eq(StringUtils.isNotBlank(bo.getCouponImage()), Coupon::getCouponImage, bo.getCouponImage());
        lqw.eq(bo.getUserId() != null, Coupon::getUserId, bo.getUserId());
        lqw.eq(bo.getPlatformKey() != null, Coupon::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getAutoPay() != null, Coupon::getAutoPay, bo.getAutoPay());
        lqw.orderByDesc(Coupon::getCreateTime);
        return lqw;
    }

    /**
     * 新增优惠券
     */
    @Override
    public Boolean insertByBo(CouponBo bo) {
        Coupon add = BeanUtil.toBean(bo, Coupon.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCouponId(add.getCouponId());
        }
        return flag;
    }

    /**
     * 修改优惠券
     */
    @Override
    public Boolean updateByBo(CouponBo bo) {
        Coupon coupon = new Coupon();
        coupon.setUseStatus("5");
        LambdaQueryWrapper<Coupon> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Coupon::getActionNo, bo.getActionNo());
        lqw.in(Coupon::getUseStatus, "0", "1");
        return baseMapper.update(coupon, lqw) > 0;
    }

    /**
     * 批量作废优惠券
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        Coupon coupon = new Coupon();
        coupon.setUseStatus("5");
        LambdaQueryWrapper<Coupon> lqw = new LambdaQueryWrapper<>();
        lqw.in(Coupon::getCouponId, ids);
        lqw.in(Coupon::getUseStatus, "0", "1");
        return baseMapper.update(coupon, lqw) > 0;
    }
}
