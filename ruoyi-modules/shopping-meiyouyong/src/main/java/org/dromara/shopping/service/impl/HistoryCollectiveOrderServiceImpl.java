package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.HistoryCollectiveOrder;
import org.dromara.shopping.domain.bo.HistoryCollectiveOrderBo;
import org.dromara.shopping.domain.vo.HistoryCollectiveOrderVo;
import org.dromara.shopping.mapper.HistoryCollectiveOrderMapper;
import org.dromara.shopping.service.IHistoryCollectiveOrderService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 历史大订单Service业务层处理
 *
 * @author yzg
 * @date 2023-11-08
 */
@RequiredArgsConstructor
@Service
public class HistoryCollectiveOrderServiceImpl implements IHistoryCollectiveOrderService {

    private final HistoryCollectiveOrderMapper baseMapper;

    /**
     * 查询历史大订单
     */
    @Override
    public HistoryCollectiveOrderVo queryById(Long collectiveNumber){
        return baseMapper.selectVoById(collectiveNumber);
    }

    /**
     * 查询历史大订单列表
     */
    @Override
    public TableDataInfo<HistoryCollectiveOrderVo> queryPageList(HistoryCollectiveOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<HistoryCollectiveOrder> lqw = buildQueryWrapper(bo);
        Page<HistoryCollectiveOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询历史大订单列表
     */
    @Override
    public List<HistoryCollectiveOrderVo> queryList(HistoryCollectiveOrderBo bo) {
        LambdaQueryWrapper<HistoryCollectiveOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HistoryCollectiveOrder> buildQueryWrapper(HistoryCollectiveOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HistoryCollectiveOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, HistoryCollectiveOrder::getUserId, bo.getUserId());
        lqw.eq(bo.getTotalAmount() != null, HistoryCollectiveOrder::getTotalAmount, bo.getTotalAmount());
        lqw.eq(bo.getReducedPrice() != null, HistoryCollectiveOrder::getReducedPrice, bo.getReducedPrice());
        lqw.eq(bo.getWantAmount() != null, HistoryCollectiveOrder::getWantAmount, bo.getWantAmount());
        lqw.eq(bo.getOutAmount() != null, HistoryCollectiveOrder::getOutAmount, bo.getOutAmount());
        lqw.eq(bo.getCount() != null, HistoryCollectiveOrder::getCount, bo.getCount());
        lqw.eq(bo.getCouponId() != null, HistoryCollectiveOrder::getCouponId, bo.getCouponId());
        lqw.eq(bo.getPayTime() != null, HistoryCollectiveOrder::getPayTime, bo.getPayTime());
        lqw.eq(bo.getExpireDate() != null, HistoryCollectiveOrder::getExpireDate, bo.getExpireDate());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HistoryCollectiveOrder::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getCancelStatus()), HistoryCollectiveOrder::getCancelStatus, bo.getCancelStatus());
        lqw.eq(bo.getCancelAmount() != null, HistoryCollectiveOrder::getCancelAmount, bo.getCancelAmount());
        lqw.like(StringUtils.isNotBlank(bo.getOrderCityName()), HistoryCollectiveOrder::getOrderCityName, bo.getOrderCityName());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderCityCode()), HistoryCollectiveOrder::getOrderCityCode, bo.getOrderCityCode());
        lqw.eq(bo.getPlatformKey() != null, HistoryCollectiveOrder::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getPayMerchant() != null, HistoryCollectiveOrder::getPayMerchant, bo.getPayMerchant());
        lqw.eq(bo.getSysDeptId() != null, HistoryCollectiveOrder::getSysDeptId, bo.getSysDeptId());
        lqw.eq(bo.getSysUserId() != null, HistoryCollectiveOrder::getSysUserId, bo.getSysUserId());
        return lqw;
    }

    /**
     * 新增历史大订单
     */
    @Override
    public Boolean insertByBo(HistoryCollectiveOrderBo bo) {
        HistoryCollectiveOrder add = BeanUtil.toBean(bo, HistoryCollectiveOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCollectiveNumber(add.getCollectiveNumber());
        }
        return flag;
    }

    /**
     * 修改历史大订单
     */
    @Override
    public Boolean updateByBo(HistoryCollectiveOrderBo bo) {
        HistoryCollectiveOrder update = BeanUtil.toBean(bo, HistoryCollectiveOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HistoryCollectiveOrder entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除历史大订单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
