package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductAdjustPrice;
import org.dromara.shopping.domain.bo.ProductAdjustPriceBo;
import org.dromara.shopping.domain.vo.ProductAdjustPriceVo;
import org.dromara.shopping.mapper.ProductAdjustPriceMapper;
import org.dromara.shopping.service.IProductAdjustPriceService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品调价Service业务层处理
 *
 * @author yzg
 * @date 2024-05-29
 */
@RequiredArgsConstructor
@Service
public class ProductAdjustPriceServiceImpl implements IProductAdjustPriceService {

    private final ProductAdjustPriceMapper baseMapper;

    /**
     * 查询商品调价
     */
    @Override
    public ProductAdjustPriceVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商品调价列表
     */
    @Override
    public TableDataInfo<ProductAdjustPriceVo> queryPageList(ProductAdjustPriceBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductAdjustPrice> lqw = buildQueryWrapper(bo);
        Page<ProductAdjustPriceVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品调价列表
     */
    @Override
    public List<ProductAdjustPriceVo> queryList(ProductAdjustPriceBo bo) {
        LambdaQueryWrapper<ProductAdjustPrice> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductAdjustPrice> buildQueryWrapper(ProductAdjustPriceBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductAdjustPrice> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductAdjustPrice::getProductId, bo.getProductId());
        lqw.eq(bo.getPlatformKey() != null, ProductAdjustPrice::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getSellAmount() != null, ProductAdjustPrice::getSellAmount, bo.getSellAmount());
        lqw.eq(bo.getPriceRatio() != null, ProductAdjustPrice::getPriceRatio, bo.getPriceRatio());
        lqw.eq(bo.getAdjustPrice() != null, ProductAdjustPrice::getAdjustPrice, bo.getAdjustPrice());
        return lqw;
    }

    /**
     * 新增商品调价
     */
    @Override
    public Boolean insertByBo(ProductAdjustPriceBo bo) {
        ProductAdjustPrice add = BeanUtil.toBean(bo, ProductAdjustPrice.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品调价
     */
    @Override
    public Boolean updateByBo(ProductAdjustPriceBo bo) {
        ProductAdjustPrice update = BeanUtil.toBean(bo, ProductAdjustPrice.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductAdjustPrice entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品调价
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
