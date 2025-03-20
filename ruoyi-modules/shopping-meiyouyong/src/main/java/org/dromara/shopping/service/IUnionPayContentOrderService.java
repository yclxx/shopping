package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.UnionPayContentOrderBo;
import org.dromara.shopping.domain.vo.UnionPayContentOrderVo;

import java.util.Collection;
import java.util.List;

/**
 * 内容分销内容方订单Service接口
 *
 * @author yzg
 * @date 2023-09-16
 */
public interface IUnionPayContentOrderService {

    /**
     * 查询内容分销内容方订单
     */
    UnionPayContentOrderVo queryById(Long id);

    /**
     * 查询内容分销内容方订单列表
     */
    TableDataInfo<UnionPayContentOrderVo> queryPageList(UnionPayContentOrderBo bo, PageQuery pageQuery);

    /**
     * 查询内容分销内容方订单列表
     */
    List<UnionPayContentOrderVo> queryList(UnionPayContentOrderBo bo);

    /**
     * 修改内容分销内容方订单
     */
    Boolean insertByBo(UnionPayContentOrderBo bo);

    /**
     * 修改内容分销内容方订单
     */
    Boolean updateByBo(UnionPayContentOrderBo bo);

    /**
     * 校验并批量删除内容分销内容方订单信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
