package org.dromara.shopping.service;

import org.dromara.shopping.base.domain.vo.ProductImgVo;
import org.dromara.shopping.base.domain.bo.ProductImgBo;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 商品图片Service接口
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
public interface IProductImgService {

    /**
     * 查询商品图片
     *
     * @param id 主键
     * @return 商品图片
     */
    ProductImgVo queryById(Long id);

    /**
     * 分页查询商品图片列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品图片分页列表
     */
    TableDataInfo<ProductImgVo> queryPageList(ProductImgBo bo, PageQuery pageQuery);

    /**
     * 查询符合条件的商品图片列表
     *
     * @param bo 查询条件
     * @return 商品图片列表
     */
    List<ProductImgVo> queryList(ProductImgBo bo);

    /**
     * 新增商品图片
     *
     * @param bo 商品图片
     * @return 是否新增成功
     */
    Boolean insertByBo(ProductImgBo bo);

    /**
     * 修改商品图片
     *
     * @param bo 商品图片
     * @return 是否修改成功
     */
    Boolean updateByBo(ProductImgBo bo);

    /**
     * 校验并批量删除商品图片信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
