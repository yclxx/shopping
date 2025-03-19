package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.TimeInterval;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.redis.utils.CacheUtils;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.shopping.constant.ZlyyhConstants;
import org.dromara.shopping.domain.*;
import org.dromara.shopping.domain.bo.*;
import org.dromara.shopping.domain.vo.*;
import org.dromara.shopping.mapper.*;
import org.dromara.shopping.service.IBusinessDistrictShopService;
import org.dromara.shopping.service.IShopMerchantService;
import org.dromara.shopping.service.IShopProductService;
import org.dromara.shopping.service.IShopService;
import org.redisson.api.GeoEntry;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 门店Service业务层处理
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements IShopService {

    private final ShopMapper baseMapper;
    private final TagsShopMapper tagsShopMapper;
    private final CommercialTenantMapper commercialTenantMapper;
    private final IShopMerchantService shopMerchantService;
    private final CommercialTenantProductMapper commercialTenantProductMapper;
    private final IShopProductService shopProductService;
    private final IBusinessDistrictShopService businessDistrictShopService;
    private final ShopTourMapper shopTourMapper;

    /**
     * 项目启动初始化门店缓存
     */
    @Override
    public void loadingShopCache() {
        String key = "loadingShopCache";
        String loading = RedisUtils.getCacheObject(key);
        if (StringUtils.isNotBlank(loading)) {
            log.info("初始化门店缓存间隔时间需大于3小时");
            return;
        }
        RedisUtils.setCacheObject(key, DateUtil.now(), Duration.ofHours(3));
        TimeInterval time = DateUtil.timer();
        // 先删除缓存
        RedisUtils.deleteKeys(ZlyyhConstants.SHOP_GEO_CACHE_KEY + "*");
        ShopBo bo = new ShopBo();
        bo.setStatus("0");

        PageQuery pageQuery = new PageQuery();
        pageQuery.setPageSize(500);
        int pageNum = 1;
        Map<Long, CommercialTenantVo> commercialTenantVoMap = new HashMap<>();
        while (true) {
            pageQuery.setPageNum(pageNum);
            TableDataInfo<ShopVo> shopVoTableDataInfo = this.queryPageList(bo, pageQuery);
            List<ShopVo> shopVos = shopVoTableDataInfo.getRows();
            Map<String, List<GeoEntry>> geoEntryMap = new HashMap<>();
            for (ShopVo shopVo : shopVos) {
                if (null == shopVo.getCommercialTenantId() || shopVo.getLatitude().signum() < 1 || shopVo.getLongitude().signum() < 1 || StringUtils.isBlank(shopVo.getCitycode())) {
                    continue;
                }
                CommercialTenantVo commercialTenantVo = commercialTenantVoMap.get(shopVo.getCommercialTenantId());
                if (null == commercialTenantVo) {
                    // 查询缓存
                    if (null == commercialTenantVo) {
                        commercialTenantVo = commercialTenantMapper.selectVoById(shopVo.getCommercialTenantId());
                    }
                    commercialTenantVoMap.put(shopVo.getCommercialTenantId(), commercialTenantVo);
                }
                if (null == commercialTenantVo || !"0".equals(commercialTenantVo.getStatus()) || "1".equals(commercialTenantVo.getIndexShow())) {
                    continue;
                }
                String member = shopVo.getCommercialTenantId() + "_" + shopVo.getShopId();
                List<GeoEntry> geoEntries = geoEntryMap.computeIfAbsent(shopVo.getCitycode(), k -> new ArrayList<>());
                geoEntries.add(new GeoEntry(shopVo.getLongitude().doubleValue(), shopVo.getLatitude().doubleValue(), member));
            }
//            // 缓存门店信息
//            for (Map.Entry<String, List<GeoEntry>> geoEntry : geoEntryMap.entrySet()) {
//                RedisUtils.geoAdd(ZlyyhConstants.SHOP_GEO_CACHE_KEY + ":" + geoEntry.getKey(), geoEntry.getValue());
//            }
            int count = pageQuery.getPageNum() * pageQuery.getPageSize();
            if (count >= shopVoTableDataInfo.getTotal()) {
                break;
            }
            pageNum++;
        }
        log.info("门店初始化缓存完成，耗时：{}毫秒", time.interval());
    }

    /**
     * 查询门店
     */
    @Override
    public ShopVo queryById(Long shopId) {
        ShopVo shopVo = baseMapper.selectVoById(shopId);
        BusinessDistrictShopBo businessDistrictShopBo = new BusinessDistrictShopBo();
        businessDistrictShopBo.setShopId(shopId);
        List<BusinessDistrictShopVo> businessDistrictShopVos = businessDistrictShopService.queryList(businessDistrictShopBo);
        if (ObjectUtil.isNotEmpty(businessDistrictShopVos)) {
            StringBuilder businessDistrictShopId = new StringBuilder();
            for (BusinessDistrictShopVo businessDistrictShopVo : businessDistrictShopVos) {
                businessDistrictShopId.append(businessDistrictShopVo.getBusinessDistrictId()).append(",");
            }
            if (StringUtils.isNotBlank(businessDistrictShopId)) {
                shopVo.setBusinessDistrictId(businessDistrictShopId.substring(0, businessDistrictShopId.length() - 1));
            }
        }
        return shopVo;
    }

    /**
     * 查询门店列表
     */
    public TableDataInfo<ShopVo> queryPageLists(ShopBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Shop> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommercialTenantId() != null, Shop::getCommercialTenantId, bo.getCommercialTenantId());
        if (StringUtils.isNotBlank(bo.getShopName())) {
            lqw.and(lq ->
                lq.like(Shop::getShopName, bo.getShopName()).or().like(Shop::getAddress, bo.getShopName())
                    .or().like(Shop::getFormattedAddress, bo.getShopName())
            );
        }
        if (StringUtils.isNotBlank(bo.getProvince())) {
            lqw.and(lq ->
                lq.like(Shop::getProvince, bo.getProvince()).or().like(Shop::getCity, bo.getProvince())
                    .or().like(Shop::getDistrict, bo.getProvince())
                    .or().like(Shop::getProcode, bo.getProvince())
                    .or().like(Shop::getCitycode, bo.getProvince())
                    .or().like(Shop::getAdcode, bo.getProvince())
            );
        }
        if (ObjectUtil.isNotEmpty(bo.getSort()) && bo.getSort().equals(0L)) {
            if (ObjectUtil.isNotEmpty(bo.getProductId())) {
                String sql = "SELECT shop_id FROM t_shop_product WHERE product_id =  " + bo.getProductId();
                lqw.apply("shop_id IN (" + sql + ")");
            }
        }
        if (ObjectUtil.isNotEmpty(bo.getSort()) && bo.getSort().equals(1L)) {
            if (ObjectUtil.isNotEmpty(bo.getProductId())) {
                String sql = "SELECT shop_id FROM t_shop_product WHERE product_id =  " + bo.getProductId();
                lqw.apply("shop_id NOT IN (" + sql + ")");
            }
        }
        if (ObjectUtil.isNotEmpty(bo.getSort()) && bo.getSort().equals(3L)) {
            if (StringUtils.isNotEmpty(bo.getBusinessDistrictId())) {
                String sql = "SELECT shop_id FROM t_business_district_shop WHERE business_district_id = " + bo.getBusinessDistrictId();
                lqw.apply("shop_id IN (" + sql + ")");
            } else {
                String sql = "SELECT shop_id FROM t_business_district_shop WHERE business_district_id = " + bo.getBusinessDistrictId();
                lqw.apply("shop_id NOT IN (" + sql + ")");
            }
        } else if (ObjectUtil.isNotEmpty(bo.getSort()) && bo.getSort().equals(4L)) {
            if (StringUtils.isNotEmpty(bo.getBusinessDistrictId())) {
                String sql = "SELECT shop_id FROM t_business_district_shop WHERE business_district_id = " + bo.getBusinessDistrictId();
                lqw.apply("shop_id NOT IN (" + sql + ")");
            }
        }

        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Shop::getStatus, bo.getStatus());
        lqw.eq(bo.getPlatformKey() != null, Shop::getPlatformKey, bo.getPlatformKey());
        Page<ShopVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询门店列表
     */
    @Override
    public TableDataInfo<ShopVo> queryPageList(ShopBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Shop> lqw = buildQueryWrapper(bo);
        Page<ShopVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询门店列表
     */
    @Override
    public List<ShopVo> queryList(ShopBo bo) {
        LambdaQueryWrapper<Shop> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Shop> buildQueryWrapper(ShopBo bo) {
        LambdaQueryWrapper<Shop> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getCommercialTenantId() != null, Shop::getCommercialTenantId, bo.getCommercialTenantId());
        if (StringUtils.isNotBlank(bo.getShopName())) {
            lqw.and(lq ->
                lq.like(Shop::getShopName, bo.getShopName()).or().like(Shop::getAddress, bo.getShopName())
                    .or().like(Shop::getFormattedAddress, bo.getShopName())
            );
        }
        if (StringUtils.isNotBlank(bo.getProvince())) {
            lqw.and(lq ->
                lq.like(Shop::getProvince, bo.getProvince()).or().like(Shop::getCity, bo.getProvince())
                    .or().like(Shop::getDistrict, bo.getProvince())
                    .or().like(Shop::getProcode, bo.getProvince())
                    .or().like(Shop::getCitycode, bo.getProvince())
                    .or().like(Shop::getAdcode, bo.getProvince())
            );
        }
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Shop::getStatus, bo.getStatus());
        lqw.eq(bo.getPlatformKey() != null, Shop::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getExamineVerifier()), Shop::getExamineVerifier, bo.getExamineVerifier());
        return lqw;
    }

    @Override
    public List<ShopVo> queryList(List<String> ids) {
        LambdaQueryWrapper<Shop> lqw = Wrappers.lambdaQuery();
        lqw.in(Shop::getShopId, ids);
        return baseMapper.selectVoList(lqw);
    }

    /**
     * 新增门店
     */
    @Override
    public Boolean insertByBo(ShopBo bo, boolean supper) {
        // 校验门店名称是否已存在
        if (!supper && baseMapper.selectCount(new LambdaQueryWrapper<Shop>().eq(Shop::getShopName, bo.getShopName()).eq(Shop::getAddress, bo.getAddress())) > 0) {
            throw new ServiceException("门店已存在");
        }
        getAddressCode(bo);
        Shop add = BeanUtil.toBean(bo, Shop.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setShopId(add.getShopId());
            setTagsShop(bo.getTagsList(), add.getShopId());
            if (ObjectUtil.isNotEmpty(bo.getProductId())) {
                ShopProductBo shopProductBo = new ShopProductBo();
                shopProductBo.setShopId(add.getShopId());
                shopProductBo.setProductId(bo.getProductId());
                shopProductService.insertByBo(shopProductBo);
            }
//            changeGeoCache(add.getShopId());
        }
        return flag;
    }

    /**
     * 新增门店
     */
    @Override
    public Boolean insertShop(ShopBo bo, boolean supper) {
        // 校验门店名称是否已存在
        if (!supper && baseMapper.selectCount(new LambdaQueryWrapper<Shop>().eq(Shop::getShopName, bo.getShopName()).eq(Shop::getAddress, bo.getAddress())) > 0) {
            throw new ServiceException("门店已存在");
        }
        Shop add = BeanUtil.toBean(bo, Shop.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setShopId(add.getShopId());
            processBusiness(bo.getShopId(), bo.getBusinessDistrictId(), false);
            setTagsShop(bo.getTagsList(), add.getShopId());
        }
        return flag;
    }

    /**
     * 修改门店
     */
    @Override
    public Boolean updateByBo(ShopBo bo, boolean supper) {
        // 校验修改后的门店是否已存在
        if (!supper && baseMapper.selectCount(new LambdaQueryWrapper<Shop>().eq(Shop::getShopName, bo.getShopName()).eq(Shop::getAddress, bo.getAddress()).ne(Shop::getShopId, bo.getShopId())) > 0) {
            throw new ServiceException("门店名称已存在");
        }
        getAddressCode(bo);
        Shop update = BeanUtil.toBean(bo, Shop.class);
        boolean flag = baseMapper.updateById(update) > 0;
//        changeGeoCache(update.getShopId());
        if (flag) {
            processBusiness(bo.getShopId(), bo.getBusinessDistrictId(), true);
            setTagsShop(bo.getTagsList(), bo.getShopId());
        }
        return flag;
    }

    /**
     * 批量删除门店
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        for (Long id : ids) {
            //删除门店同时删除门店商品的信息
            delShopProduct(id);
            //删除与商圈相关信息
            delShopBusinessDistrictId(id);
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    private void processBusiness(Long shopId, String businessDistrictId, boolean update) {
        if (null == shopId) {
            return;
        }
        if (update) {
            businessDistrictShopService.remove(new LambdaQueryWrapper<BusinessDistrictShop>().eq(BusinessDistrictShop::getShopId, shopId));
        }
        if (ObjectUtil.isNotEmpty(businessDistrictId)) {
            String[] split = businessDistrictId.split(",");
            for (String s : split) {
                BusinessDistrictShopBo businessDistrictShopBo = new BusinessDistrictShopBo();
                businessDistrictShopBo.setShopId(shopId);
                businessDistrictShopBo.setBusinessDistrictId(Long.parseLong(s));
                businessDistrictShopService.insertByBo(businessDistrictShopBo);
            }
        }
    }

    private void getMerchant(ShopImportBo shopImportBo, Long shopId, boolean update) {
        if (StringUtils.isNotEmpty(shopImportBo.getWeChatMerchantNo())) {
            if (update) {
                delMerchant(shopId, "0");
            }
            saveMerchant(shopImportBo.getWeChatMerchantNo(), "0", shopId, shopImportBo.getTerminalNo());
        }
        if (StringUtils.isNotEmpty(shopImportBo.getYsfMerchantNo())) {
            if (update) {
                delMerchant(shopId, "1");
            }
            saveMerchant(shopImportBo.getYsfMerchantNo(), "1", shopId, shopImportBo.getTerminalNo());
        }
        if (StringUtils.isNotEmpty(shopImportBo.getZfbMerchantNo())) {
            if (update) {
                delMerchant(shopId, "2");
            }
            saveMerchant(shopImportBo.getZfbMerchantNo(), "2", shopId, shopImportBo.getTerminalNo());
        }
    }

    private void delMerchant(Long shopId, String merchantType) {
        List<ShopMerchantVo> merchantWechatVos = shopMerchantService.queryByShopId(shopId, merchantType);
        if (ObjectUtil.isNotEmpty(merchantWechatVos)) {
            List<Long> wechatIds = merchantWechatVos.stream().map(ShopMerchantVo::getId).collect(Collectors.toList());
            shopMerchantService.deleteWithValidByIds(wechatIds, true);
        }
    }

    private void delShopProduct(Long shopId) {
        shopProductService.deleteWithValidByShopId(shopId);
    }

    private void delShopBusinessDistrictId(Long shopId) {
        businessDistrictShopService.deleteWithValidByShopId(shopId);
    }

    private void saveMerchant(String str, String merchantType, Long shopId, String terminalNo) {
        str = str.replaceAll("\\n", ",");
        str = str.replaceAll(" ", ",");
        str = str.replaceAll("，", ",");
        String[] split = str.split(",");
        for (String merchantNo : split) {
            ShopMerchantBo merchantBo = new ShopMerchantBo();
            merchantBo.setShopId(shopId);
            merchantBo.setMerchantType(merchantType);
            merchantBo.setMerchantNo(merchantNo);
            merchantBo.setTerminalNo(terminalNo);
            shopMerchantService.insertByBo(merchantBo);
        }
    }

    private CommercialTenant queryByName(String commercialTenantName, Long platformKey) {
        LambdaQueryWrapper<CommercialTenant> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(CommercialTenant::getCommercialTenantName, commercialTenantName);
        queryWrapper.eq(CommercialTenant::getPlatformKey, platformKey);
        return commercialTenantMapper.selectOne(queryWrapper);
    }

    private void getAddressCode(ShopBo bo) {
        String key;
        //根据经纬度查询地址信息
        if (ObjectUtil.isNotEmpty(bo.getLongitude()) && ObjectUtil.isNotEmpty(bo.getLatitude()) && bo.getLatitude().compareTo(BigDecimal.ZERO) > 0 && bo.getLongitude().compareTo(BigDecimal.ZERO) > 0) {
            key = "importShop:" + bo.getLongitude() + "," + bo.getLatitude();
        } else {
            //如果没传经纬度则按照地址来获取相关信息
            if (StringUtils.isBlank(bo.getAddress())) {
                return;
            }
            key = "importShop:" + bo.getAddress();
        }
    }

    @Override
    public ShopVo queryByNameAndAddress(String shopName, String address, Long platformKey) {
        LambdaQueryWrapper<Shop> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Shop::getShopName, shopName);
        queryWrapper.eq(Shop::getAddress, address);
        queryWrapper.eq(Shop::getPlatformKey, platformKey);
        return baseMapper.selectVoOne(queryWrapper);
    }

    @Override
    public List<ShopVo> queryByCommercialTenantId(Long commercialTenantId) {
        LambdaQueryWrapper<Shop> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Shop::getCommercialTenantId, commercialTenantId);
        return baseMapper.selectVoList(queryWrapper);
    }

    @Override
    public int deleteByCommercialTenantId(Long commercialTenantId) {
        LambdaQueryWrapper<Shop> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Shop::getCommercialTenantId, commercialTenantId);
        return baseMapper.delete(queryWrapper);
    }

    @Override
    public int deleteBySupplierShopId(Long supplierShopId) {
        LambdaQueryWrapper<Shop> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Shop::getSupplierShopId, supplierShopId);
        return baseMapper.delete(queryWrapper);
    }

    @Override
    public ShopVo queryByNameAndCommercialTenantId(String name, Long commercialTenantId) {
        LambdaQueryWrapper<Shop> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Shop::getCommercialTenantId, commercialTenantId);
        queryWrapper.eq(Shop::getShopName, name);
        queryWrapper.last("limit 1");
        return baseMapper.selectVoOne(queryWrapper);
    }

    @Override
    public ShopVo queryByNameAndSupplierId(String name, String supplierShopId) {
        LambdaQueryWrapper<Shop> queryWrapper = Wrappers.lambdaQuery();
        queryWrapper.eq(Shop::getShopName, name);
        queryWrapper.eq(Shop::getSupplierShopId, supplierShopId);
        queryWrapper.last("limit 1");
        return baseMapper.selectVoOne(queryWrapper);
    }

    @Override
    public TableDataInfo<ShopVo> getPageList(ShopBo bo, PageQuery pageQuery) {
        if (ObjectUtil.isNotEmpty(bo.getTourActivityId())) {
            List<ShopTourVo> shopTourVos = shopTourMapper.selectVoList(new LambdaQueryWrapper<ShopTour>().eq(ShopTour::getTourActivityId, bo.getTourActivityId()));
            if (ObjectUtil.isNotEmpty(shopTourVos)) {
                List<Long> noShopIds = shopTourVos.stream().map(ShopTourVo::getShopId).collect(Collectors.toList());
                bo.setNoShopIds(noShopIds);
            }
        }
        if (StringUtils.isNotEmpty(bo.getMerchantNo())) {
            ShopMerchantVo shopMerchantVo = shopMerchantService.queryByMerNo(bo.getMerchantNo());
            if (ObjectUtil.isNotEmpty(shopMerchantVo)) {
                List<Long> list = new ArrayList<>();
                list.add(shopMerchantVo.getShopId());
                bo.setShopIds(list);
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        }
        List<Long> tenantIds1 = null;
        List<Long> tenantIds2 = null;
        if (StringUtils.isNotEmpty(bo.getCommercialTenantName())) {
            LambdaQueryWrapper<CommercialTenant> wrapper = new LambdaQueryWrapper<>();
            wrapper.and(lq ->
                lq.like(CommercialTenant::getCommercialTenantName, bo.getCommercialTenantName())
                    .or().like(CommercialTenant::getCommercialTenantTitle, bo.getCommercialTenantName())
            );
            List<CommercialTenantVo> tenantVos = commercialTenantMapper.selectVoList(wrapper);
            if (ObjectUtil.isNotEmpty(tenantVos)) {
                tenantIds1 = tenantVos.stream().map(CommercialTenantVo::getCommercialTenantId).collect(Collectors.toList());
                //bo.setCommercialTenantIds(commercialTenantIds);
            }
        }
        if (ObjectUtil.isNotEmpty(bo.getVerifierId())) {
            List<CommercialTenantVo> tenantVos = commercialTenantMapper.selectVoList(new LambdaQueryWrapper<CommercialTenant>().eq(CommercialTenant::getVerifierId, bo.getVerifierId()));
            if (ObjectUtil.isNotEmpty(tenantVos)) {
                tenantIds2 = tenantVos.stream().map(CommercialTenantVo::getCommercialTenantId).collect(Collectors.toList());
                //bo.setCommercialTenantIds(commercialTenantIds);
            }
        }
        if (StringUtils.isNotEmpty(bo.getCommercialTenantName()) && ObjectUtil.isNotEmpty(bo.getVerifierId())) {
            List<Long> ids = tenantIds1.stream().filter(tenantIds2::contains).collect(Collectors.toList());
            if (ObjectUtil.isNotEmpty(ids)) {
                bo.setCommercialTenantIds(ids);
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        } else if (StringUtils.isNotEmpty(bo.getCommercialTenantName()) && ObjectUtil.isEmpty(bo.getVerifierId())) {
            if (ObjectUtil.isNotEmpty(tenantIds1)) {
                bo.setCommercialTenantIds(tenantIds1);
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        } else if (StringUtils.isEmpty(bo.getCommercialTenantName()) && ObjectUtil.isNotEmpty(bo.getVerifierId())) {
            if (ObjectUtil.isNotEmpty(tenantIds2)) {
                bo.setCommercialTenantIds(tenantIds2);
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        }

        return TableDataInfo.build();
    }

    private void setTagsShop(List<Long> tagsList, Long shopId) {
        if (ObjectUtil.isNotEmpty(tagsList)) {
            List<TagsShop> tagsShops = new ArrayList<>();
            tagsList.forEach(o -> {
                TagsShop tagsShop = new TagsShop();
                tagsShop.setShopId(shopId);
                tagsShop.setTagsId(o);
                tagsShops.add(tagsShop);
            });
            tagsShopMapper.insertBatch(tagsShops);
        }
    }

//    private void changeGeoCache(Long shopId) {
//        Shop shop = baseMapper.selectById(shopId);
//        if (null == shop.getCommercialTenantId() || shop.getLatitude().signum() < 1 || shop.getLongitude().signum() < 1) {
//            return;
//        }
//        String member = shop.getCommercialTenantId() + "_" + shop.getShopId();
//        if (!"0".equals(shop.getStatus())) {
//            RedisUtils.geoDel(ZlyyhConstants.SHOP_GEO_CACHE_KEY, member);
//        } else {
//            RedisUtils.geoAdd(ZlyyhConstants.SHOP_GEO_CACHE_KEY, new GeoEntry(shop.getLongitude().doubleValue(), shop.getLatitude().doubleValue(), member));
//        }
//    }

//    private void delGeoCache(Collection<Long> ids) {
//        List<ShopVo> shopVos = baseMapper.selectVoList(new LambdaQueryWrapper<Shop>().in(Shop::getShopId, ids));
//        List<String> members = new ArrayList<>(shopVos.size());
//        for (ShopVo shopVo : shopVos) {
//            if (null == shopVo.getCommercialTenantId()) {
//                return;
//            }
//            String member = shopVo.getCommercialTenantId() + "_" + shopVo.getShopId();
//            members.add(member);
//        }
//        RedisUtils.geoDel(ZlyyhConstants.SHOP_GEO_CACHE_KEY, members);
//    }
}
