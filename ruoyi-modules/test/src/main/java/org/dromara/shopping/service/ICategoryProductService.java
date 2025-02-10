package org.dromara.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CategoryProduct;
import org.dromara.shopping.domain.bo.CategoryProductBo;
import org.dromara.shopping.domain.vo.CategoryProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 栏目商品关联Service接口
 *
 * @author yzgnet
 * @date 2023-03-21
 */
public interface ICategoryProductService {

    /**
     * 查询栏目商品关联
     */
    CategoryProductVo queryById(Long id);

    Long queryByCategoryAndProduct(Long categoryId,Long productId);

    /**
     * 查询栏目商品关联列表
     */
    TableDataInfo<CategoryProductVo> queryPageList(CategoryProductBo bo, PageQuery pageQuery);

    /**
     * 查询栏目商品关联列表
     */
    List<CategoryProductVo> queryList(CategoryProductBo bo);

    /**
     * 修改栏目商品关联
     */
    Boolean insertByBo(CategoryProductBo bo);

    /**
     * 修改栏目商品关联
     */
    Boolean updateByBo(CategoryProductBo bo);

    /**
     * 校验并批量删除栏目商品关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Boolean remove(LambdaQueryWrapper<CategoryProduct> queryWrapper);

    Boolean addProductByCategory(CategoryProductBo bo);

    Integer delProductByCategory(CategoryProductBo bo);
}
