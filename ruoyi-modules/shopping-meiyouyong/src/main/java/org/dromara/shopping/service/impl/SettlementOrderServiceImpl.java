package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Settlement;
import org.dromara.shopping.domain.SettlementOrder;
import org.dromara.shopping.domain.bo.SettlementOrderBo;
import org.dromara.shopping.domain.vo.QuerySettlementOrderBackVo;
import org.dromara.shopping.domain.vo.SettlementOrderVo;
import org.dromara.shopping.domain.vo.SettlementVo;
import org.dromara.shopping.mapper.SettlementMapper;
import org.dromara.shopping.mapper.SettlementOrderMapper;
import org.dromara.shopping.service.ISettlementOrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结算订单Service业务层处理
 *
 * @author yzg
 * @date 2024-08-21
 */
@RequiredArgsConstructor
@Service
public class SettlementOrderServiceImpl implements ISettlementOrderService {

    private final SettlementOrderMapper baseMapper;
    private final SettlementMapper settlementMapper;

    /**
     * 查询结算订单
     */
    @Override
    public SettlementOrderVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询结算订单列表
     */
    @Override
    public TableDataInfo<SettlementOrderVo> queryPageList(SettlementOrderBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SettlementOrder> lqw = buildQueryWrapper(bo);
        Page<SettlementOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public TableDataInfo<QuerySettlementOrderBackVo> selectSettlementOrderBack(PageQuery pageQuery, Collection<Long> settlementIds) {
        return TableDataInfo.build();
    }

    @Override
    public BigDecimal sumSettlementOrderBack(List<Long> settlementIds) {
        return BigDecimal.ZERO;
    }

    /**
     * 查询结算订单列表
     */
    @Override
    public List<SettlementOrderVo> queryList(SettlementOrderBo bo) {
        LambdaQueryWrapper<SettlementOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SettlementOrder> buildQueryWrapper(SettlementOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SettlementOrder> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSettlementId() != null, SettlementOrder::getSettlementId, bo.getSettlementId());
        lqw.eq(bo.getNumber() != null, SettlementOrder::getNumber, bo.getNumber());
        return lqw;
    }

    /**
     * 新增结算订单
     */
    @Transactional
    @Override
    public Boolean insertByBo(SettlementOrderBo bo) {
        SettlementVo settlementVo = settlementMapper.selectVoById(bo.getSettlementId());
        if (null == settlementVo) {
            throw new ServiceException("结算记录不存在。");
        }
        if (!"0".equals(settlementVo.getStatus()) && !"1".equals(settlementVo.getStatus()) && !"2".equals(settlementVo.getStatus())) {
            throw new ServiceException("结算记录状态为最终状态，不可新增数据。");
        }
        // 查询是否存在，存在则跳过，不存在则新增
        SettlementOrderVo settlementOrderVo = baseMapper.selectVoOne(new LambdaQueryWrapper<SettlementOrder>().eq(SettlementOrder::getNumber, bo.getNumber()).last("order by id desc limit 1"));
        if (null != settlementOrderVo) {
            SettlementVo st = settlementMapper.selectVoById(settlementOrderVo.getSettlementId());
            if (null != st && !"5".equals(st.getStatus()) && !"6".equals(st.getStatus())) {
                throw new ServiceException("不可重复结算");
            }
        }
        SettlementOrder add = BeanUtil.toBean(bo, SettlementOrder.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        // 更新结算记录中的金额
        Settlement settlement = new Settlement();
        settlement.setSettlementId(settlementVo.getSettlementId());
        settlement.setServiceAmount(settlement.getSettlementAmount().multiply(settlementVo.getServiceRate()).multiply(new BigDecimal("0.01")));
        settlementMapper.updateById(settlement);
        return flag;
    }

    /**
     * 批量删除结算订单
     */
    @Transactional
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        // 查询删除订单的金额
        Map<Long, BigDecimal> settlementIdAmount = new HashMap<>();
        for (Long id : ids) {
            SettlementOrderVo settlementOrderVo = baseMapper.selectVoById(id);
            if (null == settlementOrderVo) {
                continue;
            }
            BigDecimal bigDecimal = settlementIdAmount.get(settlementOrderVo.getSettlementId());
            if (null == bigDecimal) {
                bigDecimal = new BigDecimal("0");
            }
            settlementIdAmount.put(settlementOrderVo.getSettlementId(), bigDecimal);
        }
        for (Long settlementId : settlementIdAmount.keySet()) {
            SettlementVo settlementVo = settlementMapper.selectVoById(settlementId);
            if (null == settlementVo) {
                continue;
            }
            if (!"0".equals(settlementVo.getStatus()) && !"1".equals(settlementVo.getStatus()) && !"2".equals(settlementVo.getStatus())) {
                throw new ServiceException("结算记录状态为最终状态，数据不可更改。");
            }
            BigDecimal amount = settlementIdAmount.get(settlementId);
            if (null == amount || amount.signum() == 0) {
                continue;
            }
            Settlement settlement = new Settlement();
            settlement.setSettlementId(settlementId);
            settlement.setSettlementAmount(settlementVo.getSettlementAmount().subtract(amount));
            settlement.setServiceAmount(settlement.getSettlementAmount().multiply(settlementVo.getServiceRate()).multiply(new BigDecimal("0.01")));
            settlementMapper.updateById(settlement);
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
