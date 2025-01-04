package org.dromara.shopping.service;

import org.dromara.shopping.base.domain.vo.ProductVo;
import org.dromara.shopping.base.domain.bo.ProductBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品信息Service接口
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
public interface IProductService {

    /**
     * 查询商品信息
     *
     * @param productId 主键
     * @return 商品信息
     */
    ProductVo queryById(Long productId);

    /**
     * 分页查询商品信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品信息分页列表
     */
    TableDataInfo<ProductVo> queryPageList(ProductBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品信息列表
     *
     * @param bo 查询条件
     * @return 商品信息列表
     */
    List<ProductVo> queryList(ProductBo bo);

    /**
     * 新增商品信息
     *
     * @param bo 商品信息
     * @return 是否新增成功
     */
    Boolean insertByBo(ProductBo bo);

    /**
     * 修改商品信息
     *
     * @param bo 商品信息
     * @return 是否修改成功
     */
    Boolean updateByBo(ProductBo bo);

    /**
     * 校验并批量删除商品信息信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
