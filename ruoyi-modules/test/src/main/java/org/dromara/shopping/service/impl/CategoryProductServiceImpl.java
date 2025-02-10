package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CategoryProduct;
import org.dromara.shopping.domain.bo.CategoryProductBo;
import org.dromara.shopping.domain.vo.CategoryProductVo;
import org.dromara.shopping.mapper.CategoryProductMapper;
import org.dromara.shopping.service.ICategoryProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 栏目商品关联Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@RequiredArgsConstructor
@Service
public class CategoryProductServiceImpl implements ICategoryProductService {

    private final CategoryProductMapper baseMapper;

    /**
     * 查询栏目商品关联
     */
    @Override
    public CategoryProductVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    @Override
    public Long queryByCategoryAndProduct(Long categoryId, Long productId) {
        LambdaQueryWrapper<CategoryProduct> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CategoryProduct::getCategoryId, categoryId);
        queryWrapper.eq(CategoryProduct::getProductId, productId);
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * 查询栏目商品关联列表
     */
    @Override
    public TableDataInfo<CategoryProductVo> queryPageList(CategoryProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CategoryProduct> lqw = buildQueryWrapper(bo);
        Page<CategoryProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询栏目商品关联列表
     */
    @Override
    public List<CategoryProductVo> queryList(CategoryProductBo bo) {
        LambdaQueryWrapper<CategoryProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CategoryProduct> buildQueryWrapper(CategoryProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CategoryProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCategoryId() != null, CategoryProduct::getCategoryId, bo.getCategoryId());
        lqw.eq(bo.getProductId() != null, CategoryProduct::getProductId, bo.getProductId());
        lqw.eq(bo.getSort() != null, CategoryProduct::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增栏目商品关联
     */
    @Override
    public Boolean insertByBo(CategoryProductBo bo) {
        CategoryProduct add = BeanUtil.toBean(bo, CategoryProduct.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改栏目商品关联
     */
    @Override
    public Boolean updateByBo(CategoryProductBo bo) {
        CategoryProduct update = BeanUtil.toBean(bo, CategoryProduct.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除栏目商品关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {

        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean remove(LambdaQueryWrapper<CategoryProduct> queryWrapper) {
        return SqlHelper.retBool(baseMapper.delete(queryWrapper));
    }

    @Override
    public Boolean addProductByCategory(CategoryProductBo bo) {
        List<CategoryProduct> add = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(bo.getCategoryId()) && ObjectUtil.isNotEmpty(bo.getProductIds())) {
            bo.getProductIds().forEach(o -> {
                CategoryProduct categoryProduct = new CategoryProduct();
                categoryProduct.setProductId(o);
                categoryProduct.setCategoryId(bo.getCategoryId());
                add.add(categoryProduct);
            });
            return baseMapper.insertBatch(add);
        }
        return false;
    }

    @Override
    public Integer delProductByCategory(CategoryProductBo bo) {
        LambdaQueryWrapper<CategoryProduct> wrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(bo.getProductIds()) && ObjectUtil.isNotEmpty(bo.getCategoryId())) {
            wrapper.eq(CategoryProduct::getCategoryId, bo.getCategoryId());
            wrapper.in(CategoryProduct::getProductId, bo.getProductIds());
            return baseMapper.delete(wrapper);
        }
        return 0;
    }
}
