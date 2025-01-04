package org.dromara.shopping.service;

import org.dromara.shopping.base.domain.vo.ProductAttrValueVo;
import org.dromara.shopping.base.domain.bo.ProductAttrValueBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品属性值Service接口
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
public interface IProductAttrValueService {

    /**
     * 查询商品属性值
     *
     * @param id 主键
     * @return 商品属性值
     */
    ProductAttrValueVo queryById(Long id);

    /**
     * 分页查询商品属性值列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品属性值分页列表
     */
    TableDataInfo<ProductAttrValueVo> queryPageList(ProductAttrValueBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品属性值列表
     *
     * @param bo 查询条件
     * @return 商品属性值列表
     */
    List<ProductAttrValueVo> queryList(ProductAttrValueBo bo);

    /**
     * 新增商品属性值
     *
     * @param bo 商品属性值
     * @return 是否新增成功
     */
    Boolean insertByBo(ProductAttrValueBo bo);

    /**
     * 修改商品属性值
     *
     * @param bo 商品属性值
     * @return 是否修改成功
     */
    Boolean updateByBo(ProductAttrValueBo bo);

    /**
     * 校验并批量删除商品属性值信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
