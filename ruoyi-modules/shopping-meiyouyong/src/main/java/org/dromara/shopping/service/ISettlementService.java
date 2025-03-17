package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SettlementBo;
import org.dromara.shopping.domain.vo.SettlementVo;

import java.util.Collection;
import java.util.List;

/**
 * 结算记录Service接口
 *
 * @author yzg
 * @date 2024-08-21
 */
public interface ISettlementService {

    /**
     * 查询结算记录
     */
    SettlementVo queryById(Long settlementId);

    /**
     * 查询结算记录列表
     */
    TableDataInfo<SettlementVo> queryPageList(SettlementBo bo, PageQuery pageQuery);

    /**
     * 查询结算记录列表
     */
    List<SettlementVo> queryList(SettlementBo bo);

    /**
     * 查询结算记录列表
     */
    List<Long> queryIdList(SettlementBo bo);

    /**
     * 修改结算记录
     */
    Boolean insertByBo(SettlementBo bo);

    /**
     * 修改结算记录
     */
    Boolean updateByBo(SettlementBo bo);

    /**
     * 校验并批量删除结算记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    /**
     * 处理订单数据
     *
     * @param settlementId 结算记录id
     */
    void handleSettlementOrder(Long settlementId);
}
