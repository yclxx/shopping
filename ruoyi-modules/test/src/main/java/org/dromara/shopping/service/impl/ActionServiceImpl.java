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
import org.dromara.shopping.domain.Action;
import org.dromara.shopping.domain.ProductAction;
import org.dromara.shopping.domain.bo.ActionBo;
import org.dromara.shopping.domain.vo.ActionVo;
import org.dromara.shopping.service.IActionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * 优惠券批次Service业务层处理
 *
 * @author yzg
 * @date 2023-10-12
 */
@RequiredArgsConstructor
@Service
public class ActionServiceImpl implements IActionService {

    private final ActionMapper baseMapper;
    private final ProductActionMapper productActionMapper;
    private final ICouponService couponService;

    /**
     * 查询优惠券批次
     */
    @Override
    public ActionVo queryById(Long actionId) {
        return baseMapper.selectVoById(actionId);
    }

    /**
     * 查询优惠券批次列表
     */
    @Override
    public TableDataInfo<ActionVo> queryPageList(ActionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Action> lqw = buildQueryWrapper(bo);
        Page<ActionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询优惠券批次列表
     */
    @Override
    public List<ActionVo> queryList(ActionBo bo) {
        LambdaQueryWrapper<Action> lqw = Wrappers.lambdaQuery();
        lqw.like(ObjectUtil.isNotEmpty(bo.getActionId()), Action::getActionId, bo.getActionId());
        lqw.like(StringUtils.isNotBlank(bo.getCouponName()), Action::getCouponName, bo.getCouponName());
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Action> buildQueryWrapper(ActionBo bo) {
        LambdaQueryWrapper<Action> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getActionNo()), Action::getActionNo, bo.getActionNo());
        lqw.eq(StringUtils.isNotBlank(bo.getActionNote()), Action::getActionNote, bo.getActionNote());
        lqw.like(StringUtils.isNotBlank(bo.getCouponName()), Action::getCouponName, bo.getCouponName());
        lqw.eq(StringUtils.isNotBlank(bo.getCouponType()), Action::getCouponType, bo.getCouponType());
        lqw.eq(bo.getPeriodOfStart() != null, Action::getPeriodOfStart, bo.getPeriodOfStart());
        lqw.eq(bo.getPeriodOfValidity() != null, Action::getPeriodOfValidity, bo.getPeriodOfValidity());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Action::getStatus, bo.getStatus());
        lqw.eq(bo.getConversionStartDate() != null, Action::getConversionStartDate, bo.getConversionStartDate());
        lqw.eq(bo.getConversionEndDate() != null, Action::getConversionEndDate, bo.getConversionEndDate());
        lqw.eq(bo.getPlatformKey() != null, Action::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getAutoPay() != null, Action::getAutoPay, bo.getAutoPay());
        return lqw;
    }

    /**
     * 新增优惠券批次
     */
    @Override
    public Boolean insertByBo(ActionBo bo) {
        LambdaQueryWrapper<Action> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getActionNo()), Action::getActionNo, bo.getActionNo());
        Long count = baseMapper.selectCount(lqw);
        if (count > 0) throw new ServiceException("此批次号已存在");

        Action add = BeanUtil.toBean(bo, Action.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActionId(add.getActionId());
        }
        return flag;
    }

    /**
     * 创建优惠券
     */
    @Override
    public Boolean createCoupon(Long actionId, Long number) {
        Action action = baseMapper.selectById(actionId);
        if (ObjectUtil.isEmpty(action)) throw new ServiceException("无此批次信息");
        if (!action.getStatus().equals("0")) throw new ServiceException("此批次已停用");
        LambdaQueryWrapper<ProductAction> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(ProductAction::getActionId, actionId);
        // 通兑券条件处理
        if (action.getCouponType().equals("1")) {
            Long actionCount = productActionMapper.selectCount(queryWrapper);
            if (actionCount <= 0) throw new ServiceException("此批次必须先绑定商品");
        }
        List<ProductAction> productActions = productActionMapper.selectList(queryWrapper);
        if (ObjectUtil.isNotEmpty(action.getCouponCount()) && !action.getCouponCount().equals(-1L)) {
            Long couponNumber = couponService.queryNumberByActionNo(action.getActionNo());
            couponNumber += number;
            if (couponNumber > action.getCouponCount()) throw new ServiceException("此批次已到达创建数量上限。");
            couponService.addCoupon(action, number, productActions);
        } else {
            couponService.addCoupon(action, number, productActions);
        }
        return true;
    }

    public int updateActionProduct(List<Long> productIds, Long actionId, Integer type) {
        if (ObjectUtil.isEmpty(productIds)) throw new ServiceException("商品信息无效");
        if (ObjectUtil.isEmpty(actionId)) throw new ServiceException("此批次信息无效");
        if (type.equals(1)) {
            for (Long productId : productIds) {
                ProductAction productAction = new ProductAction();
                productAction.setProductId(productId);
                productAction.setActionId(actionId);
                productActionMapper.insert(productAction);
            }
        } else if (type.equals(0)) {
            for (Long productId : productIds) {
                LambdaQueryWrapper<ProductAction> wrapper = Wrappers.lambdaQuery();
                wrapper.eq(ProductAction::getActionId, actionId);
                wrapper.eq(ProductAction::getProductId, productId);
                productActionMapper.delete(wrapper);
            }
        }
        return productIds.size();
    }

    /**
     * 修改优惠券批次
     */
    @Override
    public Boolean updateByBo(ActionBo bo) {
        Action update = BeanUtil.toBean(bo, Action.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除优惠券批次
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
