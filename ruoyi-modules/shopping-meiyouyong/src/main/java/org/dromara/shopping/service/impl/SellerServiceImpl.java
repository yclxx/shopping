package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Seller;
import org.dromara.shopping.domain.SellerProduct;
import org.dromara.shopping.domain.bo.SellerBo;
import org.dromara.shopping.domain.vo.SellerVo;
import org.dromara.shopping.mapper.SellerMapper;
import org.dromara.shopping.mapper.SellerProductMapper;
import org.dromara.shopping.service.ISellerService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 店家配置Service业务层处理
 *
 * @author yzg
 * @date 2024-10-21
 */
@RequiredArgsConstructor
@Service
public class SellerServiceImpl implements ISellerService {

    private final SellerMapper baseMapper;
    private final SellerProductMapper sellerProductMapper;

    /**
     * 查询店家配置
     */
    @Override
    public SellerVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询店家配置列表
     */
    @Override
    public TableDataInfo<SellerVo> queryPageList(SellerBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Seller> lqw = buildQueryWrapper(bo);
        Page<SellerVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询店家配置列表
     */
    @Override
    public List<SellerVo> queryList(SellerBo bo) {
        LambdaQueryWrapper<Seller> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Seller> buildQueryWrapper(SellerBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Seller> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getSellerName()), Seller::getSellerName, bo.getSellerName());
        lqw.like(StringUtils.isNotBlank(bo.getShowName()), Seller::getShowName, bo.getShowName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Seller::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getShowContentType()), Seller::getShowContentType, bo.getShowContentType());
        lqw.eq(StringUtils.isNotBlank(bo.getShowContent()), Seller::getShowContent, bo.getShowContent());
        lqw.eq(StringUtils.isNotBlank(bo.getToType()), Seller::getToType, bo.getToType());
        lqw.eq(StringUtils.isNotBlank(bo.getAppId()), Seller::getAppId, bo.getAppId());
        lqw.eq(StringUtils.isNotBlank(bo.getUrl()), Seller::getUrl, bo.getUrl());
        lqw.eq(bo.getPlatformKey() != null, Seller::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getSysDeptId() != null, Seller::getSysDeptId, bo.getSysDeptId());
        lqw.eq(bo.getSysUserId() != null, Seller::getSysUserId, bo.getSysUserId());
        return lqw;
    }

    /**
     * 新增店家配置
     */
    @Override
    public Boolean insertByBo(SellerBo bo) {
        Seller add = BeanUtil.toBean(bo, Seller.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改店家配置
     */
    @Override
    public Boolean updateByBo(SellerBo bo) {
        Seller update = BeanUtil.toBean(bo, Seller.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Seller entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除店家配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    @Override
    public int updateProductSeller(List<Long> productIds, Long sellerId, Integer type) {
        if (ObjectUtil.isEmpty(productIds)) throw new ServiceException("商品信息无效");
        if (ObjectUtil.isEmpty(sellerId)) throw new ServiceException("此店家信息无效");

        if (type.equals(1)) {

            for (Long productId : productIds) {
                SellerProduct sellerProduct = new SellerProduct();
                sellerProduct.setProductId(productId);
                sellerProduct.setSellerId(sellerId);
                sellerProductMapper.insert(sellerProduct);
            }
        } else if (type.equals(0)) {
            for (Long productId : productIds) {
                LambdaQueryWrapper<SellerProduct> wrapper = Wrappers.lambdaQuery();
                wrapper.eq(SellerProduct::getSellerId, sellerId);
                wrapper.eq(SellerProduct::getProductId, productId);
                sellerProductMapper.delete(wrapper);
            }
        }
        return productIds.size();
    }
}
