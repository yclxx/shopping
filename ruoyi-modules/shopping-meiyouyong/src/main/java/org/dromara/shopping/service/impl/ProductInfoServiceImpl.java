package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.ProductInfo;
import org.dromara.shopping.domain.bo.ProductInfoBo;
import org.dromara.shopping.domain.vo.ProductInfoVo;
import org.dromara.shopping.mapper.ProductInfoMapper;
import org.dromara.shopping.service.IProductInfoService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商品拓展Service业务层处理
 *
 * @author yzg
 * @date 2023-05-15
 */
@RequiredArgsConstructor
@Service
public class ProductInfoServiceImpl implements IProductInfoService {

    private final ProductInfoMapper baseMapper;

    /**
     * 查询商品拓展
     */
    @Override
    public ProductInfoVo queryById(Long productId){
        return baseMapper.selectVoById(productId);
    }

    /**
     * 查询商品扩展
     * @param itemId
     * @return
     */
    @Override
    public ProductInfoVo queryByItemId(String itemId) {
        LambdaQueryWrapper<ProductInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(ProductInfo::getItemId,itemId);
        lqw.last("order by product_id desc limit 1");
        return baseMapper.selectVoOne(lqw);
    }

    /**
     * 查询商品拓展列表
     */
    @Override
    public TableDataInfo<ProductInfoVo> queryPageList(ProductInfoBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ProductInfo> lqw = buildQueryWrapper(bo);
        Page<ProductInfoVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商品拓展列表
     */
    @Override
    public List<ProductInfoVo> queryList(ProductInfoBo bo) {
        LambdaQueryWrapper<ProductInfo> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ProductInfo> buildQueryWrapper(ProductInfoBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ProductInfo> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getTitle()), ProductInfo::getTitle, bo.getTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getMainPicture()), ProductInfo::getMainPicture, bo.getMainPicture());
        lqw.eq(StringUtils.isNotBlank(bo.getSaleStartTime()), ProductInfo::getSaleStartTime, bo.getSaleStartTime());
        lqw.eq(StringUtils.isNotBlank(bo.getSaleEndTime()), ProductInfo::getSaleEndTime, bo.getSaleEndTime());
        lqw.eq(StringUtils.isNotBlank(bo.getItemId()), ProductInfo::getItemId, bo.getItemId());
        lqw.eq(StringUtils.isNotBlank(bo.getDiscount()), ProductInfo::getDiscount, bo.getDiscount());
        lqw.eq(bo.getStock() != null, ProductInfo::getStock, bo.getStock());
        lqw.eq(bo.getTotalSales() != null, ProductInfo::getTotalSales, bo.getTotalSales());
        lqw.eq(bo.getApplyShopCount() != null, ProductInfo::getApplyShopCount, bo.getApplyShopCount());
        lqw.eq(bo.getUseTimes() != null, ProductInfo::getUseTimes, bo.getUseTimes());
        lqw.eq(StringUtils.isNotBlank(bo.getCommissionRate()), ProductInfo::getCommissionRate, bo.getCommissionRate());
        lqw.eq(bo.getActivityPriceCent() != null, ProductInfo::getActivityPriceCent, bo.getActivityPriceCent());
        lqw.eq(bo.getOriginalPriceCent() != null, ProductInfo::getOriginalPriceCent, bo.getOriginalPriceCent());
        lqw.eq(StringUtils.isNotBlank(bo.getItemContentGroup()), ProductInfo::getItemContentGroup, bo.getItemContentGroup());
        lqw.eq(StringUtils.isNotBlank(bo.getItemContentImage()), ProductInfo::getItemContentImage, bo.getItemContentImage());
        lqw.eq(StringUtils.isNotBlank(bo.getItemBuyNote()), ProductInfo::getItemBuyNote, bo.getItemBuyNote());
        lqw.eq(StringUtils.isNotBlank(bo.getReserveDesc()), ProductInfo::getReserveDesc, bo.getReserveDesc());
        lqw.eq(StringUtils.isNotBlank(bo.getShopInfo()), ProductInfo::getShopInfo, bo.getShopInfo());
        lqw.eq(StringUtils.isNotBlank(bo.getUseNote()), ProductInfo::getUseNote, bo.getUseNote());
        lqw.eq(StringUtils.isNotBlank(bo.getTicketTimeRule()), ProductInfo::getTicketTimeRule, bo.getTicketTimeRule());
        lqw.eq(bo.getUserNumLimited() != null, ProductInfo::getUserNumLimited, bo.getUserNumLimited());
        lqw.eq(StringUtils.isNotBlank(bo.getPeriod()), ProductInfo::getPeriod, bo.getPeriod());
        lqw.eq(bo.getBuyLimit() != null, ProductInfo::getBuyLimit, bo.getBuyLimit());
        lqw.like(StringUtils.isNotBlank(bo.getBrandName()), ProductInfo::getBrandName, bo.getBrandName());
        return lqw;
    }

    /**
     * 新增商品拓展
     */
    @Override
    public Boolean insertByBo(ProductInfoBo bo) {
        ProductInfo add = BeanUtil.toBean(bo, ProductInfo.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setProductId(add.getProductId());
        }
        return flag;
    }

    /**
     * 修改商品拓展
     */
    @Override
    public Boolean updateByBo(ProductInfoBo bo) {
        ProductInfo update = BeanUtil.toBean(bo, ProductInfo.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ProductInfo entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商品拓展
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
