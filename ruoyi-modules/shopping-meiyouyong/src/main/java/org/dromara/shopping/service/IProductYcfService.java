package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.ProductYcfBo;
import org.dromara.shopping.domain.vo.ProductYcfVo;

import java.util.Collection;
import java.util.List;

/**
 * 要出发产品Service接口
 *
 * @author yzg
 * @date 2024-06-19
 */
public interface IProductYcfService {

    /**
     * 查询要出发产品
     */
    ProductYcfVo queryById(Long id);

    /**
     * 三方id查询要出发产品
     */
    ProductYcfVo queryByItemId(String itemId);

    /**
     * 查询要出发产品列表
     */
    TableDataInfo<ProductYcfVo> queryPageList(ProductYcfBo bo, PageQuery pageQuery);

    /**
     * 查询要出发产品列表
     */
    List<ProductYcfVo> queryList(ProductYcfBo bo);

    /**
     * 修改要出发产品
     */
    Boolean insertByBo(ProductYcfBo bo);

    /**
     * 修改要出发产品
     */
    Boolean updateByBo(ProductYcfBo bo);

    /**
     * 校验并批量删除要出发产品信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
