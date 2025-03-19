package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.SellerProduct;
import org.dromara.shopping.domain.bo.SellerProductBo;
import org.dromara.shopping.domain.vo.SellerProductVo;
import org.dromara.shopping.mapper.SellerProductMapper;
import org.dromara.shopping.service.ISellerProductService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品商铺关联Service业务层处理
 *
 * @author yzg
 * @date 2024-10-21
 */
@RequiredArgsConstructor
@Service
public class SellerProductServiceImpl implements ISellerProductService {

    private final SellerProductMapper baseMapper;

    /**
     * 查询商品商铺关联
     */
    @Override
    public SellerProductVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询商品商铺关联列表
     */
    @Override
    public TableDataInfo<SellerProductVo> queryPageList(SellerProductBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SellerProduct> lqw = buildQueryWrapper(bo);
        Page<SellerProductVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品商铺关联列表
     */
    @Override
    public List<SellerProductVo> queryList(SellerProductBo bo) {
        LambdaQueryWrapper<SellerProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<SellerProduct> buildQueryWrapper(SellerProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<SellerProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getSellerId() != null, SellerProduct::getSellerId, bo.getSellerId());
        lqw.eq(bo.getProductId() != null, SellerProduct::getProductId, bo.getProductId());
        lqw.eq(bo.getSort() != null, SellerProduct::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增商品商铺关联
     */
    @Override
    public Boolean insertByBo(SellerProductBo bo) {
        SellerProduct add = BeanUtil.toBean(bo, SellerProduct.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改商品商铺关联
     */
    @Override
    public Boolean updateByBo(SellerProductBo bo) {
        SellerProduct update = BeanUtil.toBean(bo, SellerProduct.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(SellerProduct entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品商铺关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
