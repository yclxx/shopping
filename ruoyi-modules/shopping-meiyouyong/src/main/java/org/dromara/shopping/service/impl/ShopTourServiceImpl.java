package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.excel.convert.ExcelBigNumberConvert;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.*;
import org.dromara.shopping.domain.bo.ShopTourBo;
import org.dromara.shopping.domain.vo.*;
import org.dromara.shopping.mapper.*;
import org.dromara.shopping.service.IShopTourService;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 巡检商户Service业务层处理
 *
 * @author yzg
 * @date 2024-01-28
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class ShopTourServiceImpl implements IShopTourService {

    private final ShopTourMapper baseMapper;
    private final ShopMapper shopMapper;
    private final VerifierMapper verifierMapper;
    private final ShopTourRewardMapper shopTourRewardMapper;
    private final ShopMerchantMapper shopMerchantMapper;
    private final ShopTourLogMapper shopTourLogMapper;
    private final CommercialTenantMapper commercialTenantMapper;
    private final ShopTourLsMerchantMapper shopTourLsMerchantMapper;
    private final ShopTourActivityMapper shopTourActivityMapper;
    private final BusinessDistrictShopMapper businessDistrictShopMapper;
    private final BusinessDistrictMapper businessDistrictMapper;

    /**
     * 查询巡检商户
     */
    @Override
    public ShopTourVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询巡检商户列表
     */
    @Override
    public TableDataInfo<ShopTourVo> queryPageList(ShopTourBo bo, PageQuery pageQuery) {
        if (ObjectUtil.isNotEmpty(bo.getParams())) {
            List<ShopTourLogVo> shopTourLogVos = setLogQeuryParams(bo);
            if (ObjectUtil.isNotEmpty(shopTourLogVos)) {
                List<Long> tourIds = setTourTime(bo, shopTourLogVos);
                if (ObjectUtil.isNotEmpty(tourIds)) {
                    bo.setTourIds(tourIds);
                } else {
                    return TableDataInfo.build(new ArrayList<>());
                }
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        }
        if (StringUtils.isNotEmpty(bo.getShopName())) {
            List<ShopVo> shopVos = shopMapper.selectVoList(new LambdaQueryWrapper<Shop>().like(Shop::getShopName, bo.getShopName()));
            if (ObjectUtil.isNotEmpty(shopVos)) {
                bo.setShopsIds(shopVos.stream().map(ShopVo::getShopId).collect(Collectors.toList()));
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        }
        LambdaQueryWrapper<ShopTour> lqw = buildQueryWrapper(bo);
        Page<ShopTourVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<ShopTourVo> dataInfo = TableDataInfo.build(result);
        for (ShopTourVo row : dataInfo.getRows()) {
            ShopVo shopVo = shopMapper.selectVoById(row.getShopId());
            List<ShopTourLogVo> shopTourLogVos = shopTourLogMapper.selectVoList(new LambdaQueryWrapper<ShopTourLog>().eq(ShopTourLog::getTourId, row.getId()).eq(ShopTourLog::getVerifierId, row.getVerifierId()).eq(ShopTourLog::getOperType, "2").orderByDesc(ShopTourLog::getCreateTime));
            List<ShopTourLogVo> recentShopTourLogVos = shopTourLogMapper.selectVoList(new LambdaQueryWrapper<ShopTourLog>().eq(ShopTourLog::getTourId, row.getId()).eq(ShopTourLog::getOperType, "2").orderByDesc(ShopTourLog::getCreateTime));
            ShopTourActivityVo shopTourActivityVo = shopTourActivityMapper.selectVoById(row.getTourActivityId());
            if (ObjectUtil.isNotEmpty(shopVo)) {
                row.setShopName(shopVo.getShopName());
            }
            if (ObjectUtil.isNotEmpty(shopTourLogVos)) {
                row.setShopTourLogVo(shopTourLogVos.get(0));
            }
            if (ObjectUtil.isNotEmpty(shopTourActivityVo)) {
                row.setShopTourActivityVo(shopTourActivityVo);
            }
            if (ObjectUtil.isNotEmpty(recentShopTourLogVos)) {
                row.setRecentShopTourLogVo(recentShopTourLogVos.get(0));
            }
        }
        return dataInfo;
    }

    /**
     * 查询巡检商户列表
     */
    @Override
    public List<ShopTourVo> queryList(ShopTourBo bo) {
        LambdaQueryWrapper<ShopTour> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShopTour> buildQueryWrapper(ShopTourBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShopTour> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getVerifierId() != null, ShopTour::getVerifierId, bo.getVerifierId());
        lqw.eq(bo.getTourActivityId() != null, ShopTour::getTourActivityId, bo.getTourActivityId());
        lqw.eq(bo.getRewardAmount() != null, ShopTour::getRewardAmount, bo.getRewardAmount());
        lqw.eq(StringUtils.isNotBlank(bo.getIsReserve()), ShopTour::getIsReserve, bo.getIsReserve());
        lqw.eq(StringUtils.isNotBlank(bo.getShopStatus()), ShopTour::getShopStatus, bo.getShopStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ShopTour::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getCheckRemark()), ShopTour::getCheckRemark, bo.getCheckRemark());
        lqw.eq(StringUtils.isNotBlank(bo.getVerifierImage()), ShopTour::getVerifierImage, bo.getVerifierImage());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsImage()), ShopTour::getGoodsImage, bo.getGoodsImage());
        lqw.eq(StringUtils.isNotBlank(bo.getShopImage()), ShopTour::getShopImage, bo.getShopImage());
        lqw.eq(StringUtils.isNotBlank(bo.getTourRemark()), ShopTour::getTourRemark, bo.getTourRemark());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantNo()), ShopTour::getMerchantNo, bo.getMerchantNo());
        lqw.eq(StringUtils.isNotBlank(bo.getIsActivity()), ShopTour::getIsActivity, bo.getIsActivity());
        lqw.eq(StringUtils.isNotBlank(bo.getIsClose()), ShopTour::getIsClose, bo.getIsClose());
        lqw.in(ObjectUtil.isNotEmpty(bo.getShopsIds()), ShopTour::getShopId, bo.getShopsIds());
        lqw.in(ObjectUtil.isNotEmpty(bo.getTourIds()), ShopTour::getId, bo.getTourIds());
        return lqw;
    }

    /**
     * 新增巡检商户
     */
    @Override
    public Boolean insertByBo(ShopTourBo bo) {
        ShopTour add = BeanUtil.toBean(bo, ShopTour.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改巡检商户
     */
    @Override
    public Boolean updateByBo(ShopTourBo bo) {
        ShopTour update = BeanUtil.toBean(bo, ShopTour.class);
        validEntityBeforeSave(update);
        boolean b = baseMapper.updateById(update) > 0;
        if (b) {
            if (StringUtils.isNotEmpty(bo.getTourType()) && bo.getTourType().equals("6")) {
                ShopTourVo shopTourVo = baseMapper.selectVoById(bo.getId());
                if (ObjectUtil.isNotEmpty(shopTourVo)) {
                    ShopTourLog tourLog = new ShopTourLog();
                    tourLog.setTourId(shopTourVo.getId());
                    tourLog.setVerifierId(shopTourVo.getVerifierId());
                    tourLog.setOperType("6");
                    tourLog.setShopId(shopTourVo.getShopId());
                    tourLog.setCheckFailReason(bo.getCheckRemark());
                    shopTourLogMapper.insert(tourLog);
                }
            }
        }
        return b;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ShopTour entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除巡检商户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 添加巡检商户
     */
    @Override
    public void changeTourShop(ShopTourBo bo) {
        if (ObjectUtil.isEmpty(bo.getShopIds())) {
            return;
        }
        for (Long shopId : bo.getShopIds()) {
            List<ShopTourVo> shopTourVos = baseMapper.selectVoList(new LambdaQueryWrapper<ShopTour>().eq(ShopTour::getShopId, shopId).eq(ShopTour::getTourActivityId, bo.getTourActivityId()));
            if (ObjectUtil.isNotEmpty(shopTourVos)) {
                continue;
            } else {
                ShopTour shopTour = new ShopTour();
                shopTour.setShopId(shopId);
                shopTour.setTourActivityId(bo.getTourActivityId());
                if (ObjectUtil.isNotEmpty(bo.getRewardAmount())) {
                    shopTour.setRewardAmount(bo.getRewardAmount());
                }
                baseMapper.insert(shopTour);
            }
        }
    }

    /**
     * 巡检审核通过
     */
    @Override
    public void tourCheckPass(ShopTourBo bo) {
        ShopTourBo shopTourBo = new ShopTourBo();
        shopTourBo.setId(bo.getId());
        shopTourBo.setStatus("3");
        Boolean aBoolean = updateByBo(shopTourBo);
        if (aBoolean) {
            ShopTourLog tourLog = new ShopTourLog();
            tourLog.setTourId(bo.getId());
            tourLog.setVerifierId(bo.getVerifierId());
            tourLog.setOperType("5");
            tourLog.setShopId(bo.getShopId());
            shopTourLogMapper.insert(tourLog);

            LambdaQueryWrapper<ShopTourLsMerchant> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(ShopTourLsMerchant::getTourId, bo.getId());
            wrapper.eq(ShopTourLsMerchant::getVerifierId, bo.getVerifierId());
            wrapper.in(ShopTourLsMerchant::getIsUpdate, "1", "2");
            List<ShopTourLsMerchantVo> lsMerchantVos = shopTourLsMerchantMapper.selectVoList(wrapper);
            if (ObjectUtil.isNotEmpty(lsMerchantVos)) {
                for (ShopTourLsMerchantVo lsMerchantVo : lsMerchantVos) {
                    if (lsMerchantVo.getIsUpdate().equals("1")) {
                        //修改商户号
                        List<ShopMerchantVo> merchantVos = shopMerchantMapper.selectVoList(new LambdaQueryWrapper<ShopMerchant>().eq(ShopMerchant::getShopId, lsMerchantVo.getShopId()).eq(ShopMerchant::getMerchantNo, lsMerchantVo.getOldMerchantNo()).eq(ShopMerchant::getMerchantType, lsMerchantVo.getMerchantType()));
                        if (ObjectUtil.isNotEmpty(merchantVos)) {
                            for (ShopMerchantVo merchantVo : merchantVos) {
                                ShopMerchant shopMerchant = new ShopMerchant();
                                shopMerchant.setId(merchantVo.getId());
                                shopMerchant.setMerchantNo(lsMerchantVo.getMerchantNo());
                                shopMerchant.setPaymentMethod(lsMerchantVo.getPaymentMethod());
                                shopMerchant.setAcquirer(lsMerchantVo.getAcquirer());
                                shopMerchant.setTerminalNo(lsMerchantVo.getTerminalNo());
                                shopMerchant.setMerchantImg(lsMerchantVo.getMerchantImg());
                                shopMerchant.setYcMerchant(lsMerchantVo.getYcMerchant());
                                shopMerchantMapper.updateById(shopMerchant);
                            }
                        }
                    } else if (lsMerchantVo.getIsUpdate().equals("2")) {
                        //新增商户号
                        ShopMerchant shopMerchant = new ShopMerchant();
                        shopMerchant.setShopId(lsMerchantVo.getShopId());
                        shopMerchant.setMerchantType(lsMerchantVo.getMerchantType());
                        shopMerchant.setMerchantNo(lsMerchantVo.getMerchantNo());
                        shopMerchant.setPaymentMethod(lsMerchantVo.getPaymentMethod());
                        shopMerchant.setAcquirer(lsMerchantVo.getAcquirer());
                        shopMerchant.setTerminalNo(lsMerchantVo.getTerminalNo());
                        shopMerchant.setMerchantImg(lsMerchantVo.getMerchantImg());
                        shopMerchant.setYcMerchant(lsMerchantVo.getYcMerchant());
                        shopMerchantMapper.insert(shopMerchant);
                    }
                }
            }
            List<ShopTourLogVo> shopTourLogVos = shopTourLogMapper.selectVoList(new LambdaQueryWrapper<ShopTourLog>().eq(ShopTourLog::getTourId, bo.getId()).eq(ShopTourLog::getVerifierId, bo.getVerifierId()).eq(ShopTourLog::getOperType, "2").orderByDesc(ShopTourLog::getCreateTime));
            if (ObjectUtil.isNotEmpty(shopTourLogVos)) {
                ShopTourLogVo tourLogVo = shopTourLogVos.get(0);
                Shop shop = new Shop();
                shop.setShopId(tourLogVo.getShopId());
                if (tourLogVo.getShopStatus().equals("2")) {
                    shop.setStatus("1");
                }
                if (StringUtils.isNotEmpty(tourLogVo.getShopName())) {
                    shop.setShopName(tourLogVo.getShopName());
                }
                if (StringUtils.isNotEmpty(tourLogVo.getAddress())) {
                    shop.setAddress(tourLogVo.getAddress());
                    getAddressCode(tourLogVo, shop);
                }
                shopMapper.updateById(shop);

                if (StringUtils.isNotEmpty(tourLogVo.getAdminMobile())) {
                    ShopVo shopVo = shopMapper.selectVoById(tourLogVo.getShopId());
                    if (ObjectUtil.isNotEmpty(shopVo)) {
                        if (ObjectUtil.isNotEmpty(shopVo.getCommercialTenantId())) {
                            CommercialTenantVo commercialTenantVo = commercialTenantMapper.selectVoById(shopVo.getCommercialTenantId());
                            if (ObjectUtil.isNotEmpty(commercialTenantVo)) {
                                CommercialTenant commercialTenant = new CommercialTenant();
                                commercialTenant.setCommercialTenantId(shopVo.getCommercialTenantId());
                                commercialTenant.setAdminMobile(tourLogVo.getAdminMobile());
                                commercialTenantMapper.updateById(commercialTenant);
                            }
                        }
                    }
                }
            }
            ShopTourRewardVo rewardVo = shopTourRewardMapper.selectVoOne(new LambdaQueryWrapper<ShopTourReward>().eq(ShopTourReward::getVerifierId, bo.getVerifierId()).last("limit 1"));
            if (ObjectUtil.isNotEmpty(rewardVo)) {
                rewardVo.setAmount(rewardVo.getAmount() + bo.getRewardAmount());
                rewardVo.setCount(rewardVo.getCount() + 1);
                shopTourRewardMapper.updateById(BeanUtil.toBean(rewardVo, ShopTourReward.class));
            } else {
                ShopTourReward reward = new ShopTourReward();
                reward.setVerifierId(bo.getVerifierId());
                reward.setAmount(bo.getRewardAmount());
                reward.setCount(1L);
                shopTourRewardMapper.insert(reward);
            }
        }
    }

    @Override
    public ShopVo queryByShopId(Long shopId) {
        ShopVo shopVo = shopMapper.selectVoById(shopId);
        if (ObjectUtil.isNotEmpty(shopVo)) {
            if (ObjectUtil.isNotEmpty(shopVo.getCommercialTenantId())) {
                CommercialTenantVo commercialTenantVo = commercialTenantMapper.selectVoById(shopVo.getCommercialTenantId());
                if (ObjectUtil.isNotEmpty(commercialTenantVo)) {
                    shopVo.setAdminMobile(commercialTenantVo.getAdminMobile());
                }
            }
        }
        return shopVo;
    }

    /**
     * 巡检统计
     */
    @Override
    public Map<String, Object> tourStatistics(ShopTourBo bo) {
        if (ObjectUtil.isNotEmpty(bo.getParams())) {
            List<ShopTourLogVo> shopTourLogVos = setLogQeuryParams(bo);
            if (ObjectUtil.isNotEmpty(shopTourLogVos)) {
                List<Long> tourIds = setTourTime(bo, shopTourLogVos);
                if (ObjectUtil.isNotEmpty(tourIds)) {
                    bo.setTourIds(tourIds);
                } else {
                    return getStringObjectMap();
                }
            } else {
                return getStringObjectMap();
            }
        }
        if (StringUtils.isNotEmpty(bo.getShopName())) {
            List<ShopVo> shopVos = shopMapper.selectVoList(new LambdaQueryWrapper<Shop>().like(Shop::getShopName, bo.getShopName()));
            if (ObjectUtil.isNotEmpty(shopVos)) {
                bo.setShopsIds(shopVos.stream().map(ShopVo::getShopId).collect(Collectors.toList()));
            } else {
                return getStringObjectMap();
            }
        }
        bo.setStatus(null);
        Long allCount = baseMapper.selectCount(buildQueryWrapper(bo));
        //未预约数量
        bo.setStatus("0");
        Long noReserveCount = baseMapper.selectCount(buildQueryWrapper(bo));
        //预约数量
        bo.setStatus("1");
        Long reserveCount = baseMapper.selectCount(buildQueryWrapper(bo));
        // 待审核
        bo.setStatus("2");
        Long dshTourCount = baseMapper.selectCount(buildQueryWrapper(bo));
        //已巡检数量
        bo.setStatus("3");
        Long alTourCount = baseMapper.selectCount(buildQueryWrapper(bo));
        //审核未通过数量
        bo.setStatus("4");
        Long tourFailCount = baseMapper.selectCount(buildQueryWrapper(bo));
        Map<String, Object> map = new HashMap<>();
        map.put("allCount", allCount);
        map.put("noReserveCount", noReserveCount);
        map.put("reserveCount", reserveCount);
        map.put("dshTourCount", dshTourCount);
        map.put("alTourCount", alTourCount);
        map.put("tourFailCount", tourFailCount);
        return map;
    }

    private List<ShopTourLogVo> setLogQeuryParams(ShopTourBo bo) {
        LambdaQueryWrapper<ShopTourLog> lqw = new LambdaQueryWrapper<>();
        lqw.eq(ShopTourLog::getOperType, "2");
        lqw.and(null != bo.getParams().get("beginNoTourTime") && null != bo.getParams().get("endNoTourTime"), lq -> lq.lt(ShopTourLog::getCreateTime, bo.getParams().get("beginNoTourTime")).or().gt(ShopTourLog::getCreateTime, bo.getParams().get("endNoTourTime")));
        lqw.between(bo.getParams().get("beginTourTime") != null && bo.getParams().get("endTourTime") != null, ShopTourLog::getCreateTime, bo.getParams().get("beginTourTime"), bo.getParams().get("endTourTime"));
        return shopTourLogMapper.selectVoList(lqw);
    }

    @NotNull
    private Map<String, Object> getStringObjectMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("allCount", 0);
        map.put("noReserveCount", 0);
        map.put("reserveCount", 0);
        map.put("dshTourCount", 0);
        map.put("alTourCount", 0);
        map.put("tourFailCount", 0);
        return map;
    }

    private List<Long> setTourTime(ShopTourBo bo, List<ShopTourLogVo> shopTourLogVos) {
        List<Long> tourIds = new ArrayList<>();
        ArrayList<ShopTourLogVo> collect = shopTourLogVos.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ShopTourLogVo::getTourId))), ArrayList::new));
        for (ShopTourLogVo next : collect) {
            List<ShopTourLogVo> tourLogVos = shopTourLogMapper.selectVoList(new LambdaQueryWrapper<ShopTourLog>().eq(ShopTourLog::getTourId, next.getTourId()).eq(ShopTourLog::getOperType, "2").orderByDesc(ShopTourLog::getCreateTime));
            if (ObjectUtil.isNotEmpty(tourLogVos)) {
                ShopTourLogVo tourLogVo = tourLogVos.get(0);
                if (null != bo.getParams().get("beginNoTourTime") && null != bo.getParams().get("endNoTourTime")) {
                    tourIds.add(tourLogVo.getTourId());
                } else {
                    if (null != bo.getParams().get("beginTourTime") && null != bo.getParams().get("endTourTime")) {
                        tourIds.add(tourLogVo.getTourId());
                    }
                }
            }
        }
        return tourIds;
    }

    @Override
    @Async
    public void exportTourLog(ShopTourBo bo) {
        try {
            // 创建临时文件
            File file = new File("巡检记录" + DateUtil.format(new Date(), DatePattern.PURE_TIME_PATTERN) + ".xlsx");
            try {
                ExcelWriterBuilder builder = EasyExcel.write(file, ShopTourExportVo.class).autoCloseStream(false)
                    // 自动适配
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    // 大数值自动转换 防止失真
                    .registerConverter(new ExcelBigNumberConvert());
                WriteSheet sheet = builder.sheet("数据明细").build();
                ExcelWriter build = builder.build();
                //查询支行长下发明细
                int pageIndex = 1;
                int pageSize = 50;

                PageQuery pageQuery = new PageQuery();
                pageQuery.setOrderByColumn("id");
                pageQuery.setIsAsc("desc");
                try {
                    while (true) {
                        pageQuery.setPageNum(pageIndex);
                        pageQuery.setPageSize(pageSize);
                        TableDataInfo<ShopTourVo> shopTourVoTableDataInfo = queryPageList(bo, pageQuery);
                        List<ShopTourExportVo> shopTourExportVos = queryExportTourLogList(shopTourVoTableDataInfo.getRows());
                        build.write(shopTourExportVos, sheet);
                        int sum = pageIndex * pageSize;
                        if (sum >= shopTourVoTableDataInfo.getTotal()) {
                            break;
                        }
                        pageIndex++;
                    }
                } finally {
                    build.finish();
                }
                toExcelEmail(bo.getSendEmail(), file);
            } finally {
                // 删除临时文件
                file.delete();
            }
        } catch (Exception e) {
            log.error("下载数据异常", e);
        }
    }

    private List<ShopTourExportVo> queryExportTourLogList(List<ShopTourVo> rows) {
        List<ShopTourExportVo> list = new ArrayList<>();
        for (ShopTourVo row : rows) {
            ShopTourExportVo exportVo = new ShopTourExportVo();
            BeanUtil.copyProperties(row, exportVo);
            exportVo.setRewardAmount(new BigDecimal(row.getRewardAmount()).multiply(new BigDecimal("0.01")));
            exportVo.setStatus(row.getStatus());
            ShopVo shopVo = shopMapper.selectVoById(row.getShopId());
            if (ObjectUtil.isNotEmpty(shopVo)) {
                exportVo.setShopName(shopVo.getShopName());
                exportVo.setAddress(shopVo.getAddress());
                CommercialTenantVo commercialTenantVo = commercialTenantMapper.selectVoById(shopVo.getCommercialTenantId());
                if (ObjectUtil.isNotEmpty(commercialTenantVo)) {
                    exportVo.setCommercialTenantName(commercialTenantVo.getCommercialTenantName());
                }
                BusinessDistrictShopVo businessDistrictShopVo = businessDistrictShopMapper.selectVoOne(new LambdaQueryWrapper<BusinessDistrictShop>().eq(BusinessDistrictShop::getShopId, shopVo.getShopId()).last("limit 1"));
                if (ObjectUtil.isNotEmpty(businessDistrictShopVo)) {
                    BusinessDistrictVo districtVo = businessDistrictMapper.selectVoById(businessDistrictShopVo.getBusinessDistrictId());
                    if (ObjectUtil.isNotEmpty(districtVo)) {
                        exportVo.setBusinessDistrictName(districtVo.getBusinessDistrictName());
                    }
                }
            }
            VerifierVo verifierVo = verifierMapper.selectVoById(row.getVerifierId());
            if (ObjectUtil.isNotEmpty(verifierVo)) {
                exportVo.setUserName(verifierVo.getUsername());
                exportVo.setMobile(verifierVo.getMobile());
            }
            if ("2".equals(row.getStatus()) || "3".equals(row.getStatus()) || "4".equals(row.getStatus())) {
                List<ShopTourLogVo> shopTourLogVos = shopTourLogMapper.selectVoList(new LambdaQueryWrapper<ShopTourLog>().eq(ShopTourLog::getTourId, row.getId()).eq(ShopTourLog::getVerifierId, row.getVerifierId()).eq(ShopTourLog::getOperType, "2").orderByDesc(ShopTourLog::getCreateTime));
                if (ObjectUtil.isNotEmpty(shopTourLogVos)) {
                    ShopTourLogVo tourLogVo = shopTourLogVos.get(0);
                    exportVo.setTourTime(tourLogVo.getCreateTime());
                }
            }
            ShopTourActivityVo shopTourActivityVo = shopTourActivityMapper.selectVoById(row.getTourActivityId());
            if (ObjectUtil.isNotEmpty(shopTourActivityVo)) {
                exportVo.setTourActivityName(shopTourActivityVo.getTourActivityName());
            }
//            try {
//                if (StringUtils.isNotBlank(exportVo.getVerifierImage())) {
//                    exportVo.setVerifierImageUrl(new URL(exportVo.getVerifierImage()));
//                }
//                if (StringUtils.isNotBlank(exportVo.getGoodsImage())) {
//                    exportVo.setGoodsImageUrl(new URL(exportVo.getGoodsImage()));
//                }
//                if (StringUtils.isNotBlank(exportVo.getShopImage())) {
//                    exportVo.setShopImageUrl(new URL(exportVo.getShopImage()));
//                }
//            } catch (MalformedURLException e) {
//                log.error("导出图片异常", e);
//            }
            list.add(exportVo);
        }
        return list;
    }

    public static void main(String[] args) {
        try {
            String path = "C:\\Users\\25487\\Desktop\\巡检记录\\";
            String fileName = "台州10月.xlsx";
            String exFileName = "巡检记录" + fileName;
            File file = new File(path + fileName);
            FileInputStream inputStream = new FileInputStream(file);
            List<ShopTourLocalhostImportVo> shopTourExportVos = ExcelUtil.importExcel(inputStream, ShopTourLocalhostImportVo.class);
            List<ShopTourLocalhostExportVo> exportVos = new ArrayList<>(shopTourExportVos.size());
            for (ShopTourLocalhostImportVo shopTourExportVo : shopTourExportVos) {
                ShopTourLocalhostExportVo bean = BeanUtil.toBean(shopTourExportVo, ShopTourLocalhostExportVo.class);
                try {
                    if (StringUtils.isNotBlank(bean.getVerifierImage())) {
                        bean.setVerifierImageUrl(new URL(bean.getVerifierImage()));
                    }
                    if (StringUtils.isNotBlank(bean.getGoodsImage())) {
                        bean.setGoodsImageUrl(new URL(bean.getGoodsImage()));
                    }
                    if (StringUtils.isNotBlank(bean.getShopImage())) {
                        bean.setShopImageUrl(new URL(bean.getShopImage()));
                    }
                } catch (MalformedURLException e) {
                    log.error("导出图片异常", e);
                }
                exportVos.add(bean);
            }
            ExcelUtil.exportExcel(exportVos, "数据明细", ShopTourLocalhostExportVo.class, Files.newOutputStream(new File(path + exFileName).toPath()));
            log.info("转换完成");
        } catch (Exception e) {
            log.error("导入数据异常", e);
        }
    }

    private long queryListCount(ShopTourBo bo) {
        if (ObjectUtil.isNotEmpty(bo.getParams())) {
            LambdaQueryWrapper<ShopTourLog> lqw = new LambdaQueryWrapper<>();
            lqw.eq(ShopTourLog::getOperType, "2");
            lqw.and(lq -> lq.lt(ShopTourLog::getCreateTime, bo.getParams().get("beginNoTourTime")).or().gt(ShopTourLog::getCreateTime, bo.getParams().get("endNoTourTime")));
            List<ShopTourLogVo> shopTourLogVos = shopTourLogMapper.selectVoList(lqw);
            if (ObjectUtil.isNotEmpty(shopTourLogVos)) {
                List<Long> tourIds = new ArrayList<>();
                ArrayList<ShopTourLogVo> collect = shopTourLogVos.stream().collect(Collectors.collectingAndThen(Collectors.toCollection(() -> new TreeSet<>(Comparator.comparing(ShopTourLogVo::getTourId))), ArrayList::new));
                Iterator<ShopTourLogVo> iterator = collect.iterator();
                while (iterator.hasNext()) {
                    ShopTourLogVo next = iterator.next();
                    List<ShopTourLogVo> tourLogVos = shopTourLogMapper.selectVoList(new LambdaQueryWrapper<ShopTourLog>().eq(ShopTourLog::getTourId, next.getTourId()).eq(ShopTourLog::getOperType, "2").orderByDesc(ShopTourLog::getCreateTime));
                    if (ObjectUtil.isNotEmpty(tourLogVos)) {
                        ShopTourLogVo tourLogVo = tourLogVos.get(0);
                        tourIds.add(tourLogVo.getTourId());
                    }
                }
                if (ObjectUtil.isNotEmpty(tourIds)) {
                    bo.setTourIds(tourIds);
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }
        if (StringUtils.isNotEmpty(bo.getShopName())) {
            List<ShopVo> shopVos = shopMapper.selectVoList(new LambdaQueryWrapper<Shop>().like(Shop::getShopName, bo.getShopName()));
            if (ObjectUtil.isNotEmpty(shopVos)) {
                bo.setShopsIds(shopVos.stream().map(ShopVo::getShopId).collect(Collectors.toList()));
            } else {
                return 0;
            }
        }
        LambdaQueryWrapper<ShopTour> lqw = buildQueryWrapper(bo);
        return baseMapper.selectCount(lqw);
    }

    private void toExcelEmail(String sendEmail, File file) {
        //String path =  "商户巡检记录_" + System.currentTimeMillis() + ".xlsx";
        //发送邮箱
        String subject = "数据明细表";
        String content = "商户巡检数据明细请点击附件获取";
    }

    private void getAddressCode(ShopTourLogVo vo, Shop shop) {
        String key;
        if (StringUtils.isBlank(vo.getAddress())) {
            return;
        }
        key = "updateTourShop:" + vo.getAddress();
    }
}
