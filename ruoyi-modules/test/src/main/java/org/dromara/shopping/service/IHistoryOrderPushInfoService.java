package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.HistoryOrderPushInfoBo;
import org.dromara.shopping.domain.vo.HistoryOrderPushInfoVo;

import java.util.Collection;
import java.util.List;

/**
 * 历史订单取码记录Service接口
 *
 * @author yzg
 * @date 2023-08-01
 */
public interface IHistoryOrderPushInfoService {

    /**
     * 查询历史订单取码记录
     */
    HistoryOrderPushInfoVo queryById(Long id);

    /**
     * 查询历史订单取码记录列表
     */
    TableDataInfo<HistoryOrderPushInfoVo> queryPageList(HistoryOrderPushInfoBo bo, PageQuery pageQuery);

    /**
     * 查询历史订单取码记录列表
     */
    List<HistoryOrderPushInfoVo> queryList(HistoryOrderPushInfoBo bo);

    /**
     * 修改历史订单取码记录
     */
    Boolean insertByBo(HistoryOrderPushInfoBo bo);

    /**
     * 修改历史订单取码记录
     */
    Boolean updateByBo(HistoryOrderPushInfoBo bo);

    /**
     * 校验并批量删除历史订单取码记录信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
