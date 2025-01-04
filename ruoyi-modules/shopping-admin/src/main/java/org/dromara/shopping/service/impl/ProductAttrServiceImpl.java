package org.dromara.shopping.service.impl;

import org.dromara.common.core.utils.MapstructUtils;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.mybatis.core.page.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.dromara.shopping.base.domain.bo.ProductAttrBo;
import org.dromara.shopping.base.domain.vo.ProductAttrVo;
import org.dromara.shopping.base.domain.ProductAttr;
import org.dromara.shopping.base.mapper.ProductAttrMapper;
import org.dromara.shopping.service.IProductAttrService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品属性Service业务层处理
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@RequiredArgsConstructor
@Service
public class ProductAttrServiceImpl implements IProductAttrService {

    private final ProductAttrMapper baseMapper;

    /**
     * 查询商品属性
     *
     * @param id 主键
     * @return 商品属性
     */
    @Override
    public ProductAttrVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询商品属性列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品属性分页列表
     */
    @Override
    public TableDataInfo<ProductAttrVo> queryPageList(ProductAttrBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductAttr> lqw = buildQueryWrapper(bo);
        Page<ProductAttrVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品属性列表
     *
     * @param bo 查询条件
     * @return 商品属性列表
     */
    @Override
    public List<ProductAttrVo> queryList(ProductAttrBo bo) {
        LambdaQueryWrapper<ProductAttr> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductAttr> buildQueryWrapper(ProductAttrBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductAttr> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductAttr::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getAttrName()), ProductAttr::getAttrName, bo.getAttrName());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrValues()), ProductAttr::getAttrValues, bo.getAttrValues());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ProductAttr::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商品属性
     *
     * @param bo 商品属性
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ProductAttrBo bo) {
        ProductAttr add = MapstructUtils.convert(bo, ProductAttr.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品属性
     *
     * @param bo 商品属性
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ProductAttrBo bo) {
        ProductAttr update = MapstructUtils.convert(bo, ProductAttr.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductAttr entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品属性信息
     *
     * @param ids     待删除的主键集合
     * @param isValid 是否进行有效性校验
     * @return 是否删除成功
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteByIds(ids) > 0;
    }
}
