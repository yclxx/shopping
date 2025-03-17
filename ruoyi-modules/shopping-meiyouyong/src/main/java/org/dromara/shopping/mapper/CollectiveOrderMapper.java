package org.dromara.shopping.mapper;

import org.apache.ibatis.annotations.Param;
import org.dromara.common.mybatis.core.mapper.BaseMapperPlus;
import org.dromara.shopping.domain.CollectiveOrder;
import org.dromara.shopping.domain.vo.CollectiveOrderVo;

/**
 * 大订单Mapper接口
 *
 * @author yzg
 * @date 2023-10-16
 */
public interface CollectiveOrderMapper extends BaseMapperPlus<CollectiveOrder, CollectiveOrderVo> {


    /**
     * 物理删除大订单信息
     */
    Long deleteByCollectiveNumber(@Param("collectiveNumber") String collectiveNumber);
}
