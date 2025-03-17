package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.SettlementOrderBackBo;
import org.dromara.shopping.domain.vo.SettlementOrderBackVo;

import java.util.Collection;
import java.util.List;

/**
 * 结算订单退款冲正Service接口
 *
 * @author yzg
 * @date 2024-09-09
 */
public interface ISettlementOrderBackService {

    /**
     * 查询结算订单退款冲正
     */
    SettlementOrderBackVo queryById(Long id);

    /**
     * 查询结算订单退款冲正列表
     */
    TableDataInfo<SettlementOrderBackVo> queryPageList(SettlementOrderBackBo bo, PageQuery pageQuery);

    /**
     * 查询结算订单退款冲正列表
     */
    List<SettlementOrderBackVo> queryList(SettlementOrderBackBo bo);

    /**
     * 修改结算订单退款冲正
     */
    Boolean insertByBo(SettlementOrderBackBo bo);

    /**
     * 修改结算订单退款冲正
     */
    Boolean updateByBo(SettlementOrderBackBo bo);

    /**
     * 校验并批量删除结算订单退款冲正信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
