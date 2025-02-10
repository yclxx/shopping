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
import org.dromara.shopping.domain.CategoryPlatformProduct;
import org.dromara.shopping.domain.bo.CategoryPlatformProductBo;
import org.dromara.shopping.domain.vo.CategoryPlatformProductVo;
import org.dromara.shopping.mapper.CategoryPlatformProductMapper;
import org.dromara.shopping.service.ICategoryPlatformProductService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 多平台栏目商品关联Service业务层处理
 *
 * @author yzg
 * @date 2024-02-28
 */
@RequiredArgsConstructor
@Service
public class CategoryPlatformProductServiceImpl implements ICategoryPlatformProductService {

    private final CategoryPlatformProductMapper baseMapper;

    /**
     * 查询多平台栏目商品关联
     */
    @Override
    public CategoryPlatformProductVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询多平台栏目商品关联列表
     */
    @Override
    public TableDataInfo<CategoryPlatformProductVo> queryPageList(CategoryPlatformProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CategoryPlatformProduct> lqw = buildQueryWrapper(bo);
        Page<CategoryPlatformProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询多平台栏目商品关联列表
     */
    @Override
    public List<CategoryPlatformProductVo> queryList(CategoryPlatformProductBo bo) {
        LambdaQueryWrapper<CategoryPlatformProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CategoryPlatformProduct> buildQueryWrapper(CategoryPlatformProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CategoryPlatformProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCategoryPlatformId() != null, CategoryPlatformProduct::getCategoryPlatformId, bo.getCategoryPlatformId());
        lqw.eq(bo.getProductId() != null, CategoryPlatformProduct::getProductId, bo.getProductId());
        lqw.eq(bo.getSort() != null, CategoryPlatformProduct::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增多平台栏目商品关联
     */
    @Override
    public Boolean insertByBo(CategoryPlatformProductBo bo) {
        CategoryPlatformProduct add = BeanUtil.toBean(bo, CategoryPlatformProduct.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改多平台栏目商品关联
     */
    @Override
    public Boolean updateByBo(CategoryPlatformProductBo bo) {
        CategoryPlatformProduct update = BeanUtil.toBean(bo, CategoryPlatformProduct.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CategoryPlatformProduct entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除多平台栏目商品关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public Boolean remove(LambdaQueryWrapper<CategoryPlatformProduct> queryWrapper) {
        return SqlHelper.retBool(baseMapper.delete(queryWrapper));
    }

    @Override
    public Boolean addProductByCategoryPlatform(CategoryPlatformProductBo bo) {
        List<CategoryPlatformProduct> add = new ArrayList<>();
        if (ObjectUtil.isNotEmpty(bo.getCategoryPlatformId()) && ObjectUtil.isNotEmpty(bo.getProductIds())) {
            bo.getProductIds().forEach(o -> {
                CategoryPlatformProduct categoryPlatformProduct = new CategoryPlatformProduct();
                categoryPlatformProduct.setProductId(o);
                categoryPlatformProduct.setCategoryPlatformId(bo.getCategoryPlatformId());
                add.add(categoryPlatformProduct);
            });
            return baseMapper.insertBatch(add);
        }
        return false;
    }

    @Override
    public Integer delProductByCategoryPlatform(CategoryPlatformProductBo bo) {
        LambdaQueryWrapper<CategoryPlatformProduct> wrapper = Wrappers.lambdaQuery();
        if (ObjectUtil.isNotEmpty(bo.getProductIds()) && ObjectUtil.isNotEmpty(bo.getCategoryPlatformId())) {
            wrapper.eq(CategoryPlatformProduct::getCategoryPlatformId, bo.getCategoryPlatformId());
            wrapper.in(CategoryPlatformProduct::getProductId, bo.getProductIds());
            return baseMapper.delete(wrapper);
        }
        return 0;
    }
}
