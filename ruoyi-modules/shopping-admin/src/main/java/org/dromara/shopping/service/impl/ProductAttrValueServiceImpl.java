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
import org.dromara.shopping.base.domain.bo.ProductAttrValueBo;
import org.dromara.shopping.base.domain.vo.ProductAttrValueVo;
import org.dromara.shopping.base.domain.ProductAttrValue;
import org.dromara.shopping.base.mapper.ProductAttrValueMapper;
import org.dromara.shopping.service.IProductAttrValueService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品属性值Service业务层处理
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@RequiredArgsConstructor
@Service
public class ProductAttrValueServiceImpl implements IProductAttrValueService {

    private final ProductAttrValueMapper baseMapper;

    /**
     * 查询商品属性值
     *
     * @param id 主键
     * @return 商品属性值
     */
    @Override
    public ProductAttrValueVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 分页查询商品属性值列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品属性值分页列表
     */
    @Override
    public TableDataInfo<ProductAttrValueVo> queryPageList(ProductAttrValueBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductAttrValue> lqw = buildQueryWrapper(bo);
        Page<ProductAttrValueVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品属性值列表
     *
     * @param bo 查询条件
     * @return 商品属性值列表
     */
    @Override
    public List<ProductAttrValueVo> queryList(ProductAttrValueBo bo) {
        LambdaQueryWrapper<ProductAttrValue> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductAttrValue> buildQueryWrapper(ProductAttrValueBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductAttrValue> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductAttrValue::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getSku()), ProductAttrValue::getSku, bo.getSku());
        lqw.eq(StringUtils.isNotBlank(bo.getAttrValue()), ProductAttrValue::getAttrValue, bo.getAttrValue());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ProductAttrValue::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商品属性值
     *
     * @param bo 商品属性值
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ProductAttrValueBo bo) {
        ProductAttrValue add = MapstructUtils.convert(bo, ProductAttrValue.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品属性值
     *
     * @param bo 商品属性值
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ProductAttrValueBo bo) {
        ProductAttrValue update = MapstructUtils.convert(bo, ProductAttrValue.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductAttrValue entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品属性值信息
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
