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
import org.dromara.shopping.base.domain.bo.ProductBo;
import org.dromara.shopping.base.domain.vo.ProductVo;
import org.dromara.shopping.base.domain.Product;
import org.dromara.shopping.base.mapper.ProductMapper;
import org.dromara.shopping.service.IProductService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 商品信息Service业务层处理
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements IProductService {

    private final ProductMapper baseMapper;

    /**
     * 查询商品信息
     *
     * @param productId 主键
     * @return 商品信息
     */
    @Override
    public ProductVo queryById(Long productId){
        return baseMapper.selectVoById(productId);
    }

    /**
     * 分页查询商品信息列表
     *
     * @param bo        查询条件
     * @param pageQuery 分页参数
     * @return 商品信息分页列表
     */
    @Override
    public TableDataInfo<ProductVo> queryPageList(ProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Product> lqw = buildQueryWrapper(bo);
        Page<ProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询符合条件的商品信息列表
     *
     * @param bo 查询条件
     * @return 商品信息列表
     */
    @Override
    public List<ProductVo> queryList(ProductBo bo) {
        LambdaQueryWrapper<Product> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Product> buildQueryWrapper(ProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Product> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, Product::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), Product::getProductName, bo.getProductName());
        lqw.eq(StringUtils.isNotBlank(bo.getProductType()), Product::getProductType, bo.getProductType());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Product::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getShowIndex()), Product::getShowIndex, bo.getShowIndex());
        return lqw;
    }

    /**
     * 新增商品信息
     *
     * @param bo 商品信息
     * @return 是否新增成功
     */
    @Override
    public Boolean insertByBo(ProductBo bo) {
        Product add = MapstructUtils.convert(bo, Product.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setProductId(add.getProductId());
        }
        return flag;
    }

    /**
     * 修改商品信息
     *
     * @param bo 商品信息
     * @return 是否修改成功
     */
    @Override
    public Boolean updateByBo(ProductBo bo) {
        Product update = MapstructUtils.convert(bo, Product.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Product entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 校验并批量删除商品信息信息
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
