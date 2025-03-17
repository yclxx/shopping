package org.dromara.shopping.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CategoryPlatformProduct;
import org.dromara.shopping.domain.bo.CategoryPlatformProductBo;
import org.dromara.shopping.domain.vo.CategoryPlatformProductVo;

import java.util.Collection;
import java.util.List;

/**
 * 多平台栏目商品关联Service接口
 *
 * @author yzg
 * @date 2024-02-28
 */
public interface ICategoryPlatformProductService {

    /**
     * 查询多平台栏目商品关联
     */
    CategoryPlatformProductVo queryById(Long id);

    /**
     * 查询多平台栏目商品关联列表
     */
    TableDataInfo<CategoryPlatformProductVo> queryPageList(CategoryPlatformProductBo bo, PageQuery pageQuery);

    /**
     * 查询多平台栏目商品关联列表
     */
    List<CategoryPlatformProductVo> queryList(CategoryPlatformProductBo bo);

    /**
     * 修改多平台栏目商品关联
     */
    Boolean insertByBo(CategoryPlatformProductBo bo);

    /**
     * 修改多平台栏目商品关联
     */
    Boolean updateByBo(CategoryPlatformProductBo bo);

    /**
     * 校验并批量删除多平台栏目商品关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);

    Boolean remove(LambdaQueryWrapper<CategoryPlatformProduct> queryWrapper);

    Boolean addProductByCategoryPlatform(CategoryPlatformProductBo bo);

    Integer delProductByCategoryPlatform(CategoryPlatformProductBo bo);
}
