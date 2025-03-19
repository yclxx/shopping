package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.HistoryOrder;
import org.dromara.shopping.domain.Order;
import org.dromara.shopping.domain.Settlement;
import org.dromara.shopping.domain.SettlementOrder;
import org.dromara.shopping.domain.bo.SettlementBo;
import org.dromara.shopping.domain.vo.HistoryOrderVo;
import org.dromara.shopping.domain.vo.OrderVo;
import org.dromara.shopping.domain.vo.SettlementOrderVo;
import org.dromara.shopping.domain.vo.SettlementVo;
import org.dromara.shopping.mapper.HistoryOrderMapper;
import org.dromara.shopping.mapper.OrderMapper;
import org.dromara.shopping.mapper.SettlementMapper;
import org.dromara.shopping.mapper.SettlementOrderMapper;
import org.dromara.shopping.service.ISettlementService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 结算记录Service业务层处理
 *
 * @author yzg
 * @date 2024-08-21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SettlementServiceImpl implements ISettlementService {

    private final SettlementMapper baseMapper;
    private final SettlementOrderMapper settlementOrderMapper;
    private final OrderMapper orderMapper;
    private final HistoryOrderMapper historyOrderMapper;

    /**
     * 查询结算记录
     */
    @Override
    public SettlementVo queryById(Long settlementId) {
        return baseMapper.selectVoById(settlementId);
    }

    /**
     * 查询结算记录列表
     */
    @Override
    public TableDataInfo<SettlementVo> queryPageList(SettlementBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Settlement> lqw = buildQueryWrapper(bo);
        Page<SettlementVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询结算记录列表
     */
    @Override
    public List<SettlementVo> queryList(SettlementBo bo) {
        LambdaQueryWrapper<Settlement> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 查询结算记录列表
     */
    @Override
    public List<Long> queryIdList(SettlementBo bo) {
        LambdaQueryWrapper<Settlement> lqw = buildQueryWrapper(bo);
        lqw.select(Settlement::getSettlementId);
        return baseMapper.selectObjs(lqw);
    }

    private LambdaQueryWrapper<Settlement> buildQueryWrapper(SettlementBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Settlement> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getReconciliationName()), Settlement::getReconciliationName, bo.getReconciliationName());
        lqw.eq(null != bo.getPlatformKey(), Settlement::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityId()), Settlement::getActivityId, bo.getActivityId());
        lqw.between(params.get("beginSettlementTime") != null && params.get("endSettlementTime") != null,
            Settlement::getSettlementTime, params.get("beginSettlementTime"), params.get("endSettlementTime"));
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Settlement::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSettlementType()), Settlement::getSettlementType, bo.getSettlementType());
        return lqw;
    }

    /**
     * 新增结算记录
     */
    @Override
    public Boolean insertByBo(SettlementBo bo) {
        Settlement add = BeanUtil.toBean(bo, Settlement.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setSettlementId(add.getSettlementId());
        }
        return flag;
    }

    /**
     * 修改结算记录
     */
    @Override
    public Boolean updateByBo(SettlementBo bo) {
        Settlement update = BeanUtil.toBean(bo, Settlement.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Settlement entity) {
        // 校验日期
        if (null != entity.getStartTime() && null != entity.getEndTime() && entity.getStartTime().after(entity.getEndTime())) {
            throw new ServiceException("开始时间不能大于结束数据");
        }
    }

    /**
     * 批量删除结算记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 处理订单数据
     *
     * @param settlementId 结算记录id
     */
    @Async
    @Transactional
    @Override
    public void handleSettlementOrder(Long settlementId) {
        SettlementVo settlementVo = baseMapper.selectVoById(settlementId);
        if (null == settlementVo || null == settlementVo.getStartTime() || null == settlementVo.getEndTime()) {
            return;
        }
        if (!"0".equals(settlementVo.getStatus()) && !"1".equals(settlementVo.getStatus()) && !"2".equals(settlementVo.getStatus())) {
            return;
        }
        try {
            boolean queryOrder = true;
            boolean queryHistoryOrder = true;
            BigDecimal settlementAmount = new BigDecimal("0");
            // 分页参数
            PageQuery pageQuery = new PageQuery();
            pageQuery.setOrderByColumn("number");
            pageQuery.setIsAsc("asc");
            pageQuery.setPageSize(500);
            if (queryOrder) {
                int pageNum = 1;
                LambdaQueryWrapper<Order> lqw = Wrappers.lambdaQuery();
                lqw.select(Order::getNumber, Order::getOutAmount);
                lqw.eq(Order::getPlatformKey, settlementVo.getPlatformKey());
                lqw.eq(Order::getStatus, "2");
                lqw.eq(Order::getPickupMethod, "1");
                lqw.gt(Order::getOutAmount, "0");
                lqw.between(Order::getCreateTime, settlementVo.getStartTime(), settlementVo.getEndTime());
                while (true) {
                    pageQuery.setPageNum(pageNum);
                    Page<OrderVo> result = orderMapper.selectVoPage(pageQuery.build(), lqw);
                    List<OrderVo> rows = result.getRecords();
                    for (OrderVo row : rows) {
                        // 订单号
                        settlementAmount = settlementAmount.add(row.getOutAmount());
                        // 新增结算订单
                        insertSettlementOrder(settlementId, row.getNumber());
                    }
                    if (((long) pageNum * pageQuery.getPageSize()) >= result.getTotal()) {
                        break;
                    }
                    pageNum++;
                }
            }
            if (queryHistoryOrder) {
                int pageNum = 1;
                LambdaQueryWrapper<HistoryOrder> lqw = Wrappers.lambdaQuery();
                lqw.select(HistoryOrder::getNumber, HistoryOrder::getOutAmount);
                lqw.eq(HistoryOrder::getPlatformKey, settlementVo.getPlatformKey());
                lqw.eq(HistoryOrder::getStatus, "2");
                lqw.eq(HistoryOrder::getPickupMethod, "1");
                lqw.gt(HistoryOrder::getOutAmount, "0");
                lqw.between(HistoryOrder::getCreateTime, settlementVo.getStartTime(), settlementVo.getEndTime());
                while (true) {
                    pageQuery.setPageNum(pageNum);
                    Page<HistoryOrderVo> result = historyOrderMapper.selectVoPage(pageQuery.build(), lqw);
                    List<HistoryOrderVo> rows = result.getRecords();
                    for (HistoryOrderVo row : rows) {
                        // 订单号
                        settlementAmount = settlementAmount.add(row.getOutAmount());
                        // 新增结算订单
                        insertSettlementOrder(settlementId, row.getNumber());
                    }
                    if (((long) pageNum * pageQuery.getPageSize()) >= result.getTotal()) {
                        break;
                    }
                    pageNum++;
                }
            }
            // 修改结算记录
            Settlement settlement = new Settlement();
            settlement.setSettlementId(settlementId);
            settlement.setSettlementAmount(settlementAmount);
            settlement.setServiceAmount(settlementAmount.multiply(settlementVo.getServiceRate()).multiply(new BigDecimal("0.01")));
            settlement.setStatus("2");
            baseMapper.updateById(settlement);
        } catch (Exception e) {
            log.error("结算记录：{},处理订单数据异常", settlementId, e);
            // 修改结算记录
            Settlement settlement = new Settlement();
            settlement.setSettlementId(settlementId);
            settlement.setStatus("0");
            baseMapper.updateById(settlement);
        }
    }

    private void insertSettlementOrder(Long settlementId, Long number) {
        // 新增结算订单表
        SettlementOrder settlementOrder = new SettlementOrder();
        settlementOrder.setSettlementId(settlementId);
        settlementOrder.setNumber(number);
        // 查询是否存在，存在则跳过，不存在则新增
        SettlementOrderVo settlementOrderVo = settlementOrderMapper.selectVoOne(new LambdaQueryWrapper<SettlementOrder>().eq(SettlementOrder::getNumber, number).last("order by id desc limit 1"));
        if (null != settlementOrderVo) {
            SettlementVo settlementVo = baseMapper.selectVoById(settlementOrderVo.getSettlementId());
            if (null != settlementVo && !"5".equals(settlementVo.getStatus()) && !"6".equals(settlementVo.getStatus())) {
                return;
            }
        }
        settlementOrderMapper.insert(settlementOrder);
    }
}
