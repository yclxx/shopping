package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SettlementOrderBo;
import org.dromara.shopping.domain.vo.QuerySettlementOrderBackVo;
import org.dromara.shopping.domain.vo.SettlementOrderVo;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.List;

/**
 * 结算订单Service接口
 *
 * @author yzg
 * @date 2024-08-21
 */
public interface ISettlementOrderService {

    /**
     * 查询结算订单
     */
    SettlementOrderVo queryById(Long id);

    /**
     * 查询结算订单列表
     */
    TableDataInfo<SettlementOrderVo> queryPageList(SettlementOrderBo bo, PageQuery pageQuery);

    TableDataInfo<QuerySettlementOrderBackVo> selectSettlementOrderBack(PageQuery pageQuery, Collection<Long> settlementIds);

    BigDecimal sumSettlementOrderBack(List<Long> settlementIds);

    /**
     * 查询结算订单列表
     */
    List<SettlementOrderVo> queryList(SettlementOrderBo bo);

    /**
     * 修改结算订单
     */
    Boolean insertByBo(SettlementOrderBo bo);

    /**
     * 校验并批量删除结算订单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

}
