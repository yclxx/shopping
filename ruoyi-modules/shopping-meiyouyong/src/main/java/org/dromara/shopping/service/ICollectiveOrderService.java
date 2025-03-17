package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CollectiveOrderBo;
import org.dromara.shopping.domain.vo.CollectiveOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 大订单Service接口
 *
 * @author yzg
 * @date 2023-10-16
 */
public interface ICollectiveOrderService {

    /**
     * 查询大订单
     */
    CollectiveOrderVo queryById(Long collectiveNumber);

    /**
     * 查询大订单列表
     */
    TableDataInfo<CollectiveOrderVo> queryPageList(CollectiveOrderBo bo, PageQuery pageQuery);

    /**
     * 查询大订单列表
     */
    List<CollectiveOrderVo> queryList(CollectiveOrderBo bo);

    /**
     * 修改大订单
     */
    Boolean insertByBo(CollectiveOrderBo bo);

    /**
     * 修改大订单
     */
    Boolean updateByBo(CollectiveOrderBo bo);

    /**
     * 校验并批量删除大订单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
