package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CollectiveOrder;
import org.dromara.shopping.domain.bo.CollectiveOrderBo;
import org.dromara.shopping.domain.vo.CollectiveOrderVo;
import org.dromara.shopping.mapper.CollectiveOrderMapper;
import org.dromara.shopping.service.ICollectiveOrderService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 大订单Service业务层处理
 *
 * @author yzg
 * @date 2023-10-16
 */
@RequiredArgsConstructor
@Service
public class CollectiveOrderServiceImpl implements ICollectiveOrderService {

    private final CollectiveOrderMapper baseMapper;

    /**
     * 查询大订单
     */
    @Override
    public CollectiveOrderVo queryById(Long collectiveNumber){
        return baseMapper.selectVoById(collectiveNumber);
    }

    /**
     * 查询大订单列表
     */
    @Override
    public TableDataInfo<CollectiveOrderVo> queryPageList(CollectiveOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CollectiveOrder> lqw = buildQueryWrapper(bo);
        Page<CollectiveOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询大订单列表
     */
    @Override
    public List<CollectiveOrderVo> queryList(CollectiveOrderBo bo) {
        LambdaQueryWrapper<CollectiveOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CollectiveOrder> buildQueryWrapper(CollectiveOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CollectiveOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, CollectiveOrder::getUserId, bo.getUserId());
        lqw.eq(bo.getTotalAmount() != null, CollectiveOrder::getTotalAmount, bo.getTotalAmount());
        lqw.eq(bo.getReducedPrice() != null, CollectiveOrder::getReducedPrice, bo.getReducedPrice());
        lqw.eq(bo.getWantAmount() != null, CollectiveOrder::getWantAmount, bo.getWantAmount());
        lqw.eq(bo.getOutAmount() != null, CollectiveOrder::getOutAmount, bo.getOutAmount());
        lqw.eq(bo.getCouponId() != null, CollectiveOrder::getCouponId, bo.getCouponId());
        lqw.eq(bo.getPayTime() != null, CollectiveOrder::getPayTime, bo.getPayTime());
        lqw.eq(bo.getExpireDate() != null, CollectiveOrder::getExpireDate, bo.getExpireDate());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CollectiveOrder::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getCancelStatus()), CollectiveOrder::getCancelStatus, bo.getCancelStatus());
        lqw.like(StringUtils.isNotBlank(bo.getOrderCityName()), CollectiveOrder::getOrderCityName, bo.getOrderCityName());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderCityCode()), CollectiveOrder::getOrderCityCode, bo.getOrderCityCode());
        lqw.eq(bo.getPlatformKey() != null, CollectiveOrder::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getPayMerchant() != null, CollectiveOrder::getPayMerchant, bo.getPayMerchant());
        lqw.eq(bo.getSysDeptId() != null, CollectiveOrder::getSysDeptId, bo.getSysDeptId());
        lqw.eq(bo.getSysUserId() != null, CollectiveOrder::getSysUserId, bo.getSysUserId());
        return lqw;
    }

    /**
     * 新增大订单
     */
    @Override
    public Boolean insertByBo(CollectiveOrderBo bo) {
        CollectiveOrder add = BeanUtil.toBean(bo, CollectiveOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCollectiveNumber(add.getCollectiveNumber());
        }
        return flag;
    }

    /**
     * 修改大订单
     */
    @Override
    public Boolean updateByBo(CollectiveOrderBo bo) {
        CollectiveOrder update = BeanUtil.toBean(bo, CollectiveOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CollectiveOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除大订单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
