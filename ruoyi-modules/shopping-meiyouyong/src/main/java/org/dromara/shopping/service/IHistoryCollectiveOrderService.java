package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.HistoryCollectiveOrderBo;
import org.dromara.shopping.domain.vo.HistoryCollectiveOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 历史大订单Service接口
 *
 * @author yzg
 * @date 2023-11-08
 */
public interface IHistoryCollectiveOrderService {

    /**
     * 查询历史大订单
     */
    HistoryCollectiveOrderVo queryById(Long collectiveNumber);

    /**
     * 查询历史大订单列表
     */
    TableDataInfo<HistoryCollectiveOrderVo> queryPageList(HistoryCollectiveOrderBo bo, PageQuery pageQuery);

    /**
     * 查询历史大订单列表
     */
    List<HistoryCollectiveOrderVo> queryList(HistoryCollectiveOrderBo bo);

    /**
     * 修改历史大订单
     */
    Boolean insertByBo(HistoryCollectiveOrderBo bo);

    /**
     * 修改历史大订单
     */
    Boolean updateByBo(HistoryCollectiveOrderBo bo);

    /**
     * 校验并批量删除历史大订单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
