package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.CartBo;
import org.dromara.shopping.domain.vo.CartVo;

import java.util.Collection;
import java.util.List;

/**
 * 购物车Service接口
 *
 * @author yzg
 * @date 2023-10-16
 */
public interface ICartService {

    /**
     * 查询购物车
     */
    CartVo queryById(Long id);

    /**
     * 查询购物车列表
     */
    TableDataInfo<CartVo> queryPageList(CartBo bo, PageQuery pageQuery);

    /**
     * 查询购物车列表
     */
    List<CartVo> queryList(CartBo bo);

    /**
     * 修改购物车
     */
    Boolean insertByBo(CartBo bo);

    /**
     * 修改购物车
     */
    Boolean updateByBo(CartBo bo);

    /**
     * 校验并批量删除购物车信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
