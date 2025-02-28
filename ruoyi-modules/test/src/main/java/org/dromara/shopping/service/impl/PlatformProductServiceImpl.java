package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.IdUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.shopping.domain.*;
import org.dromara.shopping.domain.bo.PlatformProductBo;
import org.dromara.shopping.domain.vo.*;
import org.dromara.shopping.mapper.*;
import org.dromara.shopping.service.IPlatformProductService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 平台商品配置Service业务层处理
 *
 * @author yzg
 * @date 2024-05-23
 */
@RequiredArgsConstructor
@Service
public class PlatformProductServiceImpl implements IPlatformProductService {

    private final PlatformProductMapper baseMapper;
    private final ProductMapper productMapper;
    private final PlatformMapper platformMapper;
    private final ProductAdjustPriceMapper productAdjustPriceMapper;
    private final CategoryProductMapper categoryProductMapper;
    private final ProductAdjustRatioMapper productAdjustRatioMapper;

    /**
     * 查询平台商品配置
     */
    @Override
    public PlatformProductVo queryById(Long platformProductId) {
        return baseMapper.selectVoById(platformProductId);
    }

    /**
     * 查询平台商品配置列表
     */
    @Override
    public TableDataInfo<PlatformProductVo> queryPageList(PlatformProductBo bo, PageQuery pageQuery) {
        if (StringUtils.isNotEmpty(bo.getProfitRateBegin()) && StringUtils.isNotEmpty(bo.getProfitRateEnd())) {
            bo.setProfitRateBegin(new BigDecimal(bo.getProfitRateBegin()).multiply(new BigDecimal("0.01")).toString());
            bo.setProfitRateEnd(new BigDecimal(bo.getProfitRateEnd()).multiply(new BigDecimal("0.01")).toString());
        }
        Page<PlatformProductVo> result = baseMapper.selectVoPage(pageQuery.build(), buildQueryWrapper(bo));
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台商品配置列表
     */
    @Override
    public List<PlatformProductVo> queryList(PlatformProductBo bo) {
        LambdaQueryWrapper<PlatformProduct> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformProduct> buildQueryWrapper(PlatformProductBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformProduct> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformProduct::getPlatformKey, bo.getPlatformKey());
        lqw.like(StringUtils.isNotBlank(bo.getPlatformName()), PlatformProduct::getPlatformName, bo.getPlatformName());
        lqw.eq(bo.getProductId() != null, PlatformProduct::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), PlatformProduct::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增平台商品配置
     */
    @Override
    public Boolean insertByBo(PlatformProductBo bo) {
        PlatformProduct add = BeanUtil.toBean(bo, PlatformProduct.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setPlatformProductId(add.getPlatformProductId());
        }
        return flag;
    }

    /**
     * 修改平台商品配置
     */
    @Override
    public Boolean updateByBo(PlatformProductBo bo) {
        PlatformProduct update = BeanUtil.toBean(bo, PlatformProduct.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformProduct entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除平台商品配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 同步平台商品
     */
    @Override
    @Async
    public void synchronizationPlatformProduct() {
        List<ProductVo> productVos = productMapper.selectVoList(new LambdaQueryWrapper<Product>().eq(Product::getStatus, "0").isNotNull(Product::getPlatformKey));
        if (ObjectUtils.isNotEmpty(productVos)) {
            for (ProductVo productVo : productVos) {
                List<PlatformProductVo> platformProductVos = baseMapper.selectVoList(new LambdaQueryWrapper<PlatformProduct>().eq(PlatformProduct::getProductId, productVo.getProductId()).eq(PlatformProduct::getPlatformKey, productVo.getPlatformKey()));
                if (ObjectUtils.isNotEmpty(platformProductVos)) {
                    continue;
                }
                PlatformProduct platformProduct = new PlatformProduct();
                platformProduct.setProductId(productVo.getProductId());
                platformProduct.setPlatformKey(productVo.getPlatformKey());
                platformProduct.setStatus("0");
                platformProduct.setPlatformName(platformMapper.selectVoById(productVo.getPlatformKey()).getPlatformName());
                baseMapper.insert(platformProduct);
            }
        }
    }

    /**
     * 配置商品
     */
    @Override
    public void setProduct(List<Long> platformProductIds) {
        if (ObjectUtils.isNotEmpty(platformProductIds)) {
            for (Long platformProductId : platformProductIds) {
                PlatformProduct platformProduct = new PlatformProduct();
                platformProduct.setPlatformProductId(platformProductId);
                platformProduct.setStatus("1");
                baseMapper.updateById(platformProduct);
            }
            PlatformProductVo platformProductVo = baseMapper.selectVoById(platformProductIds.get(0));
            List<PlatformProductVo> platformProductSetVos = baseMapper.selectVoList(new LambdaQueryWrapper<PlatformProduct>().eq(PlatformProduct::getPlatformKey, platformProductVo.getPlatformKey()).eq(PlatformProduct::getStatus, "1"));
            List<PlatformProductVo> platformProductUnsetVos = baseMapper.selectVoList(new LambdaQueryWrapper<PlatformProduct>().eq(PlatformProduct::getPlatformKey, platformProductVo.getPlatformKey()).eq(PlatformProduct::getStatus, "0"));
            RedisUtils.setCacheObject(platformProductVo.getPlatformKey() + "alSetProduct", platformProductSetVos);
            RedisUtils.setCacheObject(platformProductVo.getPlatformKey() + "noSetProduct", platformProductUnsetVos);
        }
    }

    /**
     * 取消配置商品
     */
    @Override
    public void unsetProduct(List<Long> platformProductIds) {
        if (ObjectUtils.isNotEmpty(platformProductIds)) {
            for (Long platformProductId : platformProductIds) {
                PlatformProduct platformProduct = new PlatformProduct();
                platformProduct.setPlatformProductId(platformProductId);
                platformProduct.setStatus("0");
                baseMapper.updateById(platformProduct);
            }
            PlatformProductVo platformProductVo = baseMapper.selectVoById(platformProductIds.get(0));
            List<PlatformProductVo> platformProductSetVos = baseMapper.selectVoList(new LambdaQueryWrapper<PlatformProduct>().eq(PlatformProduct::getPlatformKey, platformProductVo.getPlatformKey()).eq(PlatformProduct::getStatus, "1"));
            List<PlatformProductVo> platformProductUnsetVos = baseMapper.selectVoList(new LambdaQueryWrapper<PlatformProduct>().eq(PlatformProduct::getPlatformKey, platformProductVo.getPlatformKey()).eq(PlatformProduct::getStatus, "0"));
            RedisUtils.setCacheObject(platformProductVo.getPlatformKey() + "alSetProduct", platformProductSetVos);
            RedisUtils.setCacheObject(platformProductVo.getPlatformKey() + "noSetProduct", platformProductUnsetVos);
        }
    }

    /**
     * 商品调价
     */
    @Override
    @Async
    public void productAdjustPrice(PlatformProductBo bo) {
        if (ObjectUtils.isEmpty(bo.getPlatformKey())) {
            throw new ServiceException("平台异常，请重试");
        }
        String adjustBatch = IdUtil.getSnowflakeNextIdStr();
        if (bo.getAdjustPriceType().equals("1")) {
            ProductAdjustRatio productAdjustRatio = new ProductAdjustRatio();
            productAdjustRatio.setPlatformKey(bo.getPlatformKey());
            productAdjustRatio.setAdjustType("1");
            productAdjustRatio.setAdjustRatio(bo.getSellAmountScale());

            ProductAdjustRatioVo productAdjustRatioVo = productAdjustRatioMapper.selectVoOne(new LambdaQueryWrapper<ProductAdjustRatio>().eq(ProductAdjustRatio::getPlatformKey, bo.getPlatformKey()).eq(ProductAdjustRatio::getAdjustType, "1").last("limit 1"));
            if (ObjectUtils.isNotEmpty(productAdjustRatioVo)) {
                productAdjustRatio.setRatioId(productAdjustRatioVo.getRatioId());
                productAdjustRatioMapper.updateById(productAdjustRatio);
            } else {
                productAdjustRatioMapper.insert(productAdjustRatio);
            }

            productAdjustRatioMapper.delete(new LambdaQueryWrapper<ProductAdjustRatio>().eq(ProductAdjustRatio::getPlatformKey, bo.getPlatformKey()).eq(ProductAdjustRatio::getAdjustType, "2"));

            //平台统一调价
            List<ProductVo> productVos = productMapper.selectVoList(new LambdaQueryWrapper<Product>().eq(Product::getPlatformKey, bo.getPlatformKey()).eq(Product::getStatus, "0"));
            if (ObjectUtils.isNotEmpty(productVos)) {
                BigDecimal sellAmountScale = bo.getSellAmountScale().divide(new BigDecimal("100"));
                for (ProductVo productVo : productVos) {
                    ProductAdjustPrice productAdjustPrice = new ProductAdjustPrice();
                    productAdjustPrice.setSellAmount(productVo.getSellAmount());
                    productAdjustPrice.setPriceRatio(bo.getSellAmountScale());
                    productAdjustPrice.setAdjustPrice(productVo.getSellAmount().multiply(sellAmountScale).setScale(2, RoundingMode.HALF_UP));
                    productAdjustPrice.setAdjustBatch(adjustBatch);

                    ProductAdjustPriceVo productAdjustPriceVo = productAdjustPriceMapper.selectVoOne(new LambdaQueryWrapper<ProductAdjustPrice>().eq(ProductAdjustPrice::getProductId, productVo.getProductId()).eq(ProductAdjustPrice::getPlatformKey, bo.getPlatformKey()).last("limit 1"));
                    if (ObjectUtils.isNotEmpty(productAdjustPriceVo)) {
                        productAdjustPrice.setId(productAdjustPriceVo.getId());
                        productAdjustPriceMapper.updateById(productAdjustPrice);
                    } else {
                        productAdjustPrice.setProductId(productVo.getProductId());
                        productAdjustPrice.setPlatformKey(bo.getPlatformKey());
                        productAdjustPriceMapper.insert(productAdjustPrice);
                    }
                }
            }
        } else if (bo.getAdjustPriceType().equals("2")) {
            //分类商品调价
            if (ObjectUtils.isNotEmpty(bo.getAmountScaleList())) {
                for (String key : bo.getAmountScaleList().keySet()) {
                    BigDecimal amountScale = new BigDecimal(bo.getAmountScaleList().get(key).toString());
                    BigDecimal sellAmountScale = amountScale.divide(new BigDecimal("100"));

                    ProductAdjustRatio productAdjustRatio = new ProductAdjustRatio();
                    productAdjustRatio.setPlatformKey(bo.getPlatformKey());
                    productAdjustRatio.setAdjustType("2");
                    productAdjustRatio.setCategoryId(Long.valueOf(key));
                    productAdjustRatio.setAdjustRatio(amountScale);

                    ProductAdjustRatioVo productAdjustRatioVo = productAdjustRatioMapper.selectVoOne(new LambdaQueryWrapper<ProductAdjustRatio>().eq(ProductAdjustRatio::getPlatformKey, bo.getPlatformKey()).eq(ProductAdjustRatio::getAdjustType, "2").eq(ProductAdjustRatio::getCategoryId, Long.valueOf(key)).last("limit 1"));
                    if (ObjectUtils.isNotEmpty(productAdjustRatioVo)) {
                        productAdjustRatio.setRatioId(productAdjustRatioVo.getRatioId());
                        productAdjustRatioMapper.updateById(productAdjustRatio);
                    } else {
                        productAdjustRatioMapper.insert(productAdjustRatio);
                    }

                    List<CategoryProductVo> categoryProductVos = categoryProductMapper.selectVoList(new LambdaQueryWrapper<CategoryProduct>().eq(CategoryProduct::getCategoryId, key));
                    if (ObjectUtils.isEmpty(categoryProductVos)) {
                        continue;
                    }
                    for (CategoryProductVo categoryProductVo : categoryProductVos) {
                        ProductVo productVo = productMapper.selectVoById(categoryProductVo.getProductId());
                        if (ObjectUtils.isEmpty(productVo)) {
                            continue;
                        }
                        ProductAdjustPrice productAdjustPrice = new ProductAdjustPrice();
                        productAdjustPrice.setSellAmount(productVo.getSellAmount());
                        productAdjustPrice.setPriceRatio(amountScale);
                        productAdjustPrice.setAdjustPrice(productVo.getSellAmount().multiply(sellAmountScale).setScale(2, RoundingMode.HALF_UP));
                        productAdjustPrice.setAdjustBatch(adjustBatch);

                        ProductAdjustPriceVo adjustPriceVo = productAdjustPriceMapper.selectVoOne(new LambdaQueryWrapper<ProductAdjustPrice>().eq(ProductAdjustPrice::getProductId, productVo.getProductId()).eq(ProductAdjustPrice::getPlatformKey, bo.getPlatformKey()).eq(ProductAdjustPrice::getAdjustBatch, adjustBatch).last("limit 1"));
                        if (ObjectUtils.isNotEmpty(adjustPriceVo)) {
                            if (amountScale.compareTo(adjustPriceVo.getPriceRatio()) > 0) {
                                productAdjustPrice.setId(adjustPriceVo.getId());
                                productAdjustPriceMapper.updateById(productAdjustPrice);
                            }
                        } else {
                            ProductAdjustPriceVo productAdjustPriceVo = productAdjustPriceMapper.selectVoOne(new LambdaQueryWrapper<ProductAdjustPrice>().eq(ProductAdjustPrice::getProductId, productVo.getProductId()).eq(ProductAdjustPrice::getPlatformKey, bo.getPlatformKey()).last("limit 1"));
                            if (ObjectUtils.isNotEmpty(productAdjustPriceVo)) {
                                productAdjustPrice.setId(productAdjustPriceVo.getId());
                                productAdjustPriceMapper.updateById(productAdjustPrice);
                            } else {
                                productAdjustPrice.setProductId(productVo.getProductId());
                                productAdjustPrice.setPlatformKey(bo.getPlatformKey());
                                productAdjustPriceMapper.insert(productAdjustPrice);
                            }
                        }
                    }
                }
            }
            //未分类商品调价
            if (ObjectUtils.isNotEmpty(bo.getOtherAmountScale())) {
                ProductAdjustRatio productAdjustRatio = new ProductAdjustRatio();
                productAdjustRatio.setPlatformKey(bo.getPlatformKey());
                productAdjustRatio.setAdjustType("2");
                productAdjustRatio.setCategoryId(0L);
                productAdjustRatio.setAdjustRatio(bo.getOtherAmountScale());

                ProductAdjustRatioVo productAdjustRatioVo = productAdjustRatioMapper.selectVoOne(new LambdaQueryWrapper<ProductAdjustRatio>().eq(ProductAdjustRatio::getPlatformKey, bo.getPlatformKey()).eq(ProductAdjustRatio::getAdjustType, "2").eq(ProductAdjustRatio::getCategoryId, 0L).last("limit 1"));
                if (ObjectUtils.isNotEmpty(productAdjustRatioVo)) {
                    productAdjustRatio.setRatioId(productAdjustRatioVo.getRatioId());
                    productAdjustRatioMapper.updateById(productAdjustRatio);
                } else {
                    productAdjustRatioMapper.insert(productAdjustRatio);
                }

                List<ProductVo> productVos = productMapper.selectVoList(new LambdaQueryWrapper<Product>().eq(Product::getPlatformKey, bo.getPlatformKey()).eq(Product::getStatus, "0"));
                if (ObjectUtils.isNotEmpty(productVos)) {
                    productAdjustPriceEntity(productVos, bo, adjustBatch);
                }
            }

            productAdjustRatioMapper.delete(new LambdaQueryWrapper<ProductAdjustRatio>().eq(ProductAdjustRatio::getPlatformKey, bo.getPlatformKey()).eq(ProductAdjustRatio::getAdjustType, "1"));
        }
    }

    private void productAdjustPriceEntity(List<ProductVo> productVos, PlatformProductBo bo, String adjustBatch) {
        BigDecimal sellAmountScale = bo.getOtherAmountScale().divide(new BigDecimal("100"));
        for (ProductVo productVo : productVos) {
            ProductAdjustPrice productAdjustPrice = new ProductAdjustPrice();
            productAdjustPrice.setSellAmount(productVo.getSellAmount());
            productAdjustPrice.setPriceRatio(bo.getOtherAmountScale());
            productAdjustPrice.setAdjustPrice(productVo.getSellAmount().multiply(sellAmountScale).setScale(2, RoundingMode.HALF_UP));
            productAdjustPrice.setAdjustBatch(adjustBatch);

            ProductAdjustPriceVo productAdjustPriceVo = productAdjustPriceMapper.selectVoOne(new LambdaQueryWrapper<ProductAdjustPrice>().eq(ProductAdjustPrice::getProductId, productVo.getProductId()).eq(ProductAdjustPrice::getPlatformKey, bo.getPlatformKey()).last("limit 1"));
            if (ObjectUtils.isNotEmpty(productAdjustPriceVo)) {
                productAdjustPrice.setId(productAdjustPriceVo.getId());
                productAdjustPriceMapper.updateById(productAdjustPrice);
            } else {
                productAdjustPrice.setProductId(productVo.getProductId());
                productAdjustPrice.setPlatformKey(bo.getPlatformKey());
                productAdjustPriceMapper.insert(productAdjustPrice);
            }
        }
    }
}
