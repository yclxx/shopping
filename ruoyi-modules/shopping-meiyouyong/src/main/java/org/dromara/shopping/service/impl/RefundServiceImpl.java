package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.shopping.domain.*;
import org.dromara.shopping.domain.bo.OrderBackTransBo;
import org.dromara.shopping.domain.bo.RefundBo;
import org.dromara.shopping.domain.vo.RefundVo;
import org.dromara.shopping.mapper.HistoryOrderMapper;
import org.dromara.shopping.mapper.OrderGoodsMapper;
import org.dromara.shopping.mapper.OrderMapper;
import org.dromara.shopping.mapper.RefundMapper;
import org.dromara.shopping.service.IRefundService;
import org.dromara.shopping.service.ISupplierUserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 退款订单登记Service业务层处理
 *
 * @author yzg
 * @date 2023-08-07
 */
@RequiredArgsConstructor
@Service
public class RefundServiceImpl implements IRefundService {

    private final RefundMapper baseMapper;
    private final OrderMapper orderMapper;
    private final HistoryOrderMapper historyOrderMapper;
    private final OrderGoodsMapper orderGoodsMapper;
    private final ISupplierUserService supplierUserService;

    /**
     * 查询退款订单登记
     */
    @Override
    public RefundVo queryById(Long refundId) {
        return baseMapper.selectVoById(refundId);
    }

    /**
     * 查询退款订单登记列表
     */
    @Override
    public TableDataInfo<RefundVo> queryPageList(RefundBo bo, PageQuery pageQuery) {
        Page<RefundVo> result = baseMapper.selectVoPage(pageQuery.build(), buildQueryWrapper(bo));
        return TableDataInfo.build(result);
    }

    /**
     * 查询退款订单登记列表
     */
    @Override
    public List<RefundVo> queryList(RefundBo bo) {
        LambdaQueryWrapper<Refund> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Refund> buildQueryWrapper(RefundBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Refund> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getNumber() != null, Refund::getNumber, bo.getNumber());
        lqw.eq(bo.getRefundAmount() != null, Refund::getRefundAmount, bo.getRefundAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundApplicant()), Refund::getRefundApplicant, bo.getRefundApplicant());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundReviewer()), Refund::getRefundReviewer, bo.getRefundReviewer());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Refund::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getRefuseReason()), Refund::getRefuseReason, bo.getRefuseReason());
        lqw.eq(StringUtils.isNotBlank(bo.getRefundRemark()), Refund::getRefundRemark, bo.getRefundRemark());
        return lqw;
    }

    /**
     * 新增退款订单登记
     */
    @Override
    public Boolean insertByBo(RefundBo bo) {
        Refund add = BeanUtil.toBean(bo, Refund.class);
        validEntityBeforeSave(add);
        Order order = orderMapper.selectById(bo.getNumber());
        if (null == order) {
            throw new ServiceException("订单不存在");
        }
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRefundId(add.getRefundId());
        }
        return flag;
    }

    /**
     * 修改退款订单登记
     */
    @Override
    public Boolean updateByBo(RefundBo bo) {
        Refund update = BeanUtil.toBean(bo, Refund.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    @Transactional
    @Override
    public void agreeSubmit(Long refundId) {
        Refund refund = baseMapper.selectById(refundId);
        Order order = orderMapper.selectById(refund.getNumber());
        OrderBackTransBo orderBackTransBo = new OrderBackTransBo();
        orderBackTransBo.setNumber(refund.getNumber());
        boolean isHistoryOrder = false;
        if (ObjectUtil.isEmpty(order)) {
            //订单不存在 查询历史订单
            HistoryOrder historyOrder = historyOrderMapper.selectById(refund.getNumber());
            if (ObjectUtil.isEmpty(historyOrder)) {
                throw new ServiceException("订单不存在");
            }
            isHistoryOrder = true;
            order = BeanUtil.toBean(historyOrder, Order.class);
        }
        checkSupplier(LoginHelper.getUserId(), order.getSupplier());
        orderBackTransBo.setRefund(order.getOutAmount());
        //美食订单 判断供应商是否已经退款
        //嘟嘟城验证用户是否核销
        if (order.getOrderType().equals("30") || order.getOrderType().equals("33")) {
            LambdaQueryWrapper<Code> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(Code::getNumber, refund.getNumber());
        }
        if ("25".equals(order.getOrderType())) {
            //实物订单
            OrderGoods orderGoods = orderGoodsMapper.selectById(order.getNumber());
            if (ObjectUtil.isNotNull(orderGoods)) {
                if (!"0".equals(orderGoods.getLogisticsStatus())) {
                    throw new ServiceException("订单已发货，不可申请退款");
                }
            }
        }
        if (StringUtils.isNotBlank(order.getCancelStatus()) && !"1".equals(order.getCancelStatus()) && !"30".equals(order.getOrderType()) && !"33".equals(order.getOrderType()) && !"25".equals(order.getOrderType())) {
            throw new ServiceException("供应商未成功退款");
        }
        refund.setStatus("1");
        baseMapper.updateById(refund);
    }

    @Override
    public void refuseSubmit(Long refundId, String refuseReason) {
        Refund refund = baseMapper.selectById(refundId);
        refund.setStatus("2");
        refund.setRefuseReason(refuseReason);
        Order order = orderMapper.selectById(refund.getNumber());
        if (ObjectUtil.isEmpty(order)) {
            //订单不存在 查询历史订单
            HistoryOrder historyOrder = historyOrderMapper.selectById(refund.getNumber());
            if (ObjectUtil.isEmpty(historyOrder)) {
                throw new ServiceException("订单不存在");
            }
            checkSupplier(LoginHelper.getUserId(), historyOrder.getSupplier());
            historyOrder.setStatus("2");
            historyOrderMapper.updateById(historyOrder);
            baseMapper.updateById(refund);
            return;
        }
        checkSupplier(LoginHelper.getUserId(), order.getSupplier());
        //审核拒绝状态
        order.setStatus("2");
        orderMapper.updateById(order);
        baseMapper.updateById(refund);
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Refund entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除退款订单登记
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    private void checkSupplier(Long userId, Long supplierId) {
        Long supplierUserId = supplierUserService.querySupplierId(userId);
        if (null != supplierUserId) {
            if (!supplierUserId.equals(supplierId)) {
                throw new ServiceException("无权操作");
            }
        }
    }
}
