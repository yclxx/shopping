package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.HistoryOrderBo;
import org.dromara.shopping.domain.vo.HistoryOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 历史订单Service接口
 *
 * @author yzg
 * @date 2023-08-01
 */
public interface IHistoryOrderService {

    /**
     * 查询历史订单
     */
    HistoryOrderVo queryById(Long number);

    /**
     * 查询历史订单列表
     */
    TableDataInfo<HistoryOrderVo> queryPageList(HistoryOrderBo bo, PageQuery pageQuery);

    /**
     * 查询历史订单列表
     */
    List<HistoryOrderVo> queryList(HistoryOrderBo bo);

    /**
     * 修改历史订单
     */
    Boolean insertByBo(HistoryOrderBo bo);

    /**
     * 修改历史订单
     */
    Boolean updateByBo(HistoryOrderBo bo);

    /**
     * 校验并批量删除历史订单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
