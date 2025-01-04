package org.dromara.shopping.service;

import org.dromara.shopping.base.domain.vo.ProductAttrVo;
import org.dromara.shopping.base.domain.bo.ProductAttrBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品属性Service接口
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
public interface IProductAttrService {

    /**
     * 查询商品属性
     *
     * @param id 主键
     * @return 商品属性
     */
    ProductAttrVo queryById(Long id);

    /**
     * 分页查询商品属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品属性分页列表
     */
    TableDataInfo<ProductAttrVo> queryPageList(ProductAttrBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品属性列表
     *
     * @param bo 查询条件
     * @return 商品属性列表
     */
    List<ProductAttrVo> queryList(ProductAttrBo bo);

    /**
     * 新增商品属性
     *
     * @param bo 商品属性
     * @return 是否新增成功
     */
    Boolean insertByBo(ProductAttrBo bo);

    /**
     * 修改商品属性
     *
     * @param bo 商品属性
     * @return 是否修改成功
     */
    Boolean updateByBo(ProductAttrBo bo);

    /**
     * 校验并批量删除商品属性信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
