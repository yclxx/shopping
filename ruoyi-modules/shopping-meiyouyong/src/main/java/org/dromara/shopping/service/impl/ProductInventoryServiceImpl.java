package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductInventory;
import org.dromara.shopping.domain.bo.ProductInventoryBo;
import org.dromara.shopping.domain.vo.ProductInventoryVo;
import org.dromara.shopping.mapper.ProductInventoryMapper;
import org.dromara.shopping.service.IProductInventoryService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品库存信息Service业务层处理
 *
 * @author yzg
 * @date 2024-05-10
 */
@RequiredArgsConstructor
@Service
public class ProductInventoryServiceImpl implements IProductInventoryService {

    private final ProductInventoryMapper baseMapper;

    /**
     * 查询商品库存信息
     */
    @Override
    public ProductInventoryVo queryById(Long inventoryId){
        return baseMapper.selectVoById(inventoryId);
    }

    /**
     * 查询商品库存信息列表
     */
    @Override
    public TableDataInfo<ProductInventoryVo> queryPageList(ProductInventoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductInventory> lqw = buildQueryWrapper(bo);
        Page<ProductInventoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品库存信息列表
     */
    @Override
    public List<ProductInventoryVo> queryList(ProductInventoryBo bo) {
        LambdaQueryWrapper<ProductInventory> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductInventory> buildQueryWrapper(ProductInventoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductInventory> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getProductId() != null, ProductInventory::getProductId, bo.getProductId());
        lqw.eq(bo.getYesterdayCount() != null, ProductInventory::getYesterdayCount, bo.getYesterdayCount());
        return lqw;
    }

    /**
     * 新增商品库存信息
     */
    @Override
    public Boolean insertByBo(ProductInventoryBo bo) {
        ProductInventory add = BeanUtil.toBean(bo, ProductInventory.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setInventoryId(add.getInventoryId());
        }
        return flag;
    }

    /**
     * 修改商品库存信息
     */
    @Override
    public Boolean updateByBo(ProductInventoryBo bo) {
        ProductInventory update = BeanUtil.toBean(bo, ProductInventory.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductInventory entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品库存信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
