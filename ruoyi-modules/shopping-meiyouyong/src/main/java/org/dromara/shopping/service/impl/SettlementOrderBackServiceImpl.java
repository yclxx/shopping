package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Settlement;
import org.dromara.shopping.domain.SettlementOrderBack;
import org.dromara.shopping.domain.bo.OrderBackTransBo;
import org.dromara.shopping.domain.bo.SettlementOrderBackBo;
import org.dromara.shopping.domain.vo.SettlementOrderBackVo;
import org.dromara.shopping.domain.vo.SettlementVo;
import org.dromara.shopping.mapper.SettlementMapper;
import org.dromara.shopping.mapper.SettlementOrderBackMapper;
import org.dromara.shopping.service.ISettlementOrderBackService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 结算订单退款冲正Service业务层处理
 *
 * @author yzg
 * @date 2024-09-09
 */
@RequiredArgsConstructor
@Service
public class SettlementOrderBackServiceImpl implements ISettlementOrderBackService {

    private final SettlementOrderBackMapper baseMapper;
    private final SettlementMapper settlementMapper;

    /**
     * 查询结算订单退款冲正
     */
    @Override
    public SettlementOrderBackVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询结算订单退款冲正列表
     */
    @Override
    public TableDataInfo<SettlementOrderBackVo> queryPageList(SettlementOrderBackBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SettlementOrderBack> lqw = buildQueryWrapper(bo);
        Page<SettlementOrderBackVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询结算订单退款冲正列表
     */
    @Override
    public List<SettlementOrderBackVo> queryList(SettlementOrderBackBo bo) {
        LambdaQueryWrapper<SettlementOrderBack> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SettlementOrderBack> buildQueryWrapper(SettlementOrderBackBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SettlementOrderBack> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSettlementId() != null, SettlementOrderBack::getSettlementId, bo.getSettlementId());
        lqw.eq(bo.getBackSettlementId() != null, SettlementOrderBack::getBackSettlementId, bo.getBackSettlementId());
        lqw.eq(bo.getNumber() != null, SettlementOrderBack::getNumber, bo.getNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SettlementOrderBack::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增结算订单退款冲正
     */
    @Transactional
    @Override
    public Boolean insertByBo(SettlementOrderBackBo bo) {
        // 查询是否存在，存在则跳过，不存在则新增
        SettlementOrderBackVo settlementOrderBackVo = baseMapper.selectVoOne(new LambdaQueryWrapper<SettlementOrderBack>().eq(SettlementOrderBack::getNumber, bo.getNumber()).last("order by id desc limit 1"));
        if (null != settlementOrderBackVo) {
            SettlementVo st = settlementMapper.selectVoById(settlementOrderBackVo.getBackSettlementId());
            if (null != st && !"5".equals(st.getStatus()) && !"6".equals(st.getStatus())) {
                throw new ServiceException("不可重复结算");
            }
        }
        SettlementVo settlementVo = settlementMapper.selectVoById(bo.getBackSettlementId());
        if (null == settlementVo) {
            throw new ServiceException("结算记录不存在。");
        }
        if (!"0".equals(settlementVo.getStatus()) && !"1".equals(settlementVo.getStatus()) && !"2".equals(settlementVo.getStatus())) {
            throw new ServiceException("结算记录状态为最终状态，不可新增数据。");
        }
        SettlementOrderBack add = BeanUtil.toBean(bo, SettlementOrderBack.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        // 更新结算记录中的金额
        Settlement settlement = new Settlement();
        settlement.setSettlementId(settlementVo.getSettlementId());
        settlement.setSettlementAmount(settlementVo.getSettlementAmount().subtract(getRefundAmount(add.getNumber())));
        settlement.setServiceAmount(settlement.getSettlementAmount().multiply(settlementVo.getServiceRate()).multiply(new BigDecimal("0.01")));
        settlementMapper.updateById(settlement);

        return flag;
    }

    /**
     * 修改结算订单退款冲正
     */
    @Override
    public Boolean updateByBo(SettlementOrderBackBo bo) {
        SettlementOrderBack update = BeanUtil.toBean(bo, SettlementOrderBack.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除结算订单退款冲正
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        // 查询删除订单的金额
        Map<Long, BigDecimal> settlementIdAmount = new HashMap<>();
        for (Long id : ids) {
            SettlementOrderBackVo settlementOrderBackVo = baseMapper.selectVoById(id);
            if (null == settlementOrderBackVo) {
                continue;
            }
            // 查询订单
            BigDecimal bigDecimal = settlementIdAmount.get(settlementOrderBackVo.getBackSettlementId());
            if (null == bigDecimal) {
                bigDecimal = new BigDecimal("0");
            }
            bigDecimal = bigDecimal.add(getRefundAmount(settlementOrderBackVo.getNumber()));
            settlementIdAmount.put(settlementOrderBackVo.getBackSettlementId(), bigDecimal);
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
            settlement.setSettlementAmount(settlementVo.getSettlementAmount().add(amount));
            settlement.setServiceAmount(settlement.getSettlementAmount().multiply(settlementVo.getServiceRate()).multiply(new BigDecimal("0.01")));
            settlementMapper.updateById(settlement);
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    private BigDecimal getRefundAmount(Long number) {
        OrderBackTransBo backTransBo = new OrderBackTransBo();
        backTransBo.setNumber(number);
        BigDecimal bigDecimal = new BigDecimal("0");
        return bigDecimal;
    }
}
