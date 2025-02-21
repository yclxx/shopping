package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.shopping.domain.HistoryOrder;
import org.dromara.shopping.domain.SupplierUser;
import org.dromara.shopping.domain.bo.HistoryOrderBo;
import org.dromara.shopping.domain.vo.HistoryOrderVo;
import org.dromara.shopping.domain.vo.MerchantVo;
import org.dromara.shopping.domain.vo.SupplierUserVo;
import org.dromara.shopping.mapper.HistoryOrderMapper;
import org.dromara.shopping.mapper.SupplierUserMapper;
import org.dromara.shopping.service.IHistoryOrderService;
import org.dromara.shopping.service.IMerchantService;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 历史订单Service业务层处理
 *
 * @author yzg
 * @date 2023-08-01
 */
@RequiredArgsConstructor
@Service
@Slf4j
public class HistoryOrderServiceImpl implements IHistoryOrderService {

    private final HistoryOrderMapper baseMapper;
    private final SupplierUserMapper supplierUserMapper;
    private final IMerchantService merchantService;

    /**
     * 查询历史订单
     */
    @Override
    public HistoryOrderVo queryById(Long number) {
        return baseMapper.selectVoById(number);
    }

    /**
     * 查询历史订单列表
     */
    @Override
    public TableDataInfo<HistoryOrderVo> queryPageList(HistoryOrderBo bo, PageQuery pageQuery) {
        Instant start1 = Instant.now();
        Long userId = LoginHelper.getUserId();
        LambdaQueryWrapper<SupplierUser> supplierUserWrapper = Wrappers.lambdaQuery();
        supplierUserWrapper.eq(SupplierUser::getUserId, userId);
        supplierUserWrapper.last("LIMIT 1");
        SupplierUserVo supplierUserVo = supplierUserMapper.selectVoOne(supplierUserWrapper);
        if (ObjectUtil.isNotEmpty(supplierUserVo)) {
            bo.setSupplier(supplierUserVo.getSupplierId());
        }
        LambdaQueryWrapper<HistoryOrder> lqw = buildQueryWrapper(bo);
        if (null != bo.getUserId()) {
            lqw.eq(HistoryOrder::getUserId, bo.getUserId());
        }
        Instant start2 = Instant.now();
        log.info("数据处理时间:{}毫秒", Duration.between(start1, start2).toMillis());
        Page<HistoryOrderVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        Instant start3 = Instant.now();
        log.info("数据处理时间:{}毫秒", Duration.between(start2, start3).toMillis());
        TableDataInfo<HistoryOrderVo> dataInfo = TableDataInfo.build(result);
        Map<Long, MerchantVo> merchantVoMap = new HashMap<>(dataInfo.getRows().size());
        for (HistoryOrderVo row : dataInfo.getRows()) {
            if (null != row.getPayMerchant()) {
                MerchantVo merchantVo = merchantVoMap.get(row.getPayMerchant());
                if (null == merchantVo) {
                    merchantVo = merchantService.queryById(row.getPayMerchant());
                    merchantVoMap.put(row.getPayMerchant(), merchantVo);
                }
                row.setMerchantVo(merchantVo);
            }
        }
        Instant start4 = Instant.now();
        log.info("数据处理时间:{}毫秒", Duration.between(start3, start4).toMillis());
        return dataInfo;
    }

    /**
     * 查询历史订单列表
     */
    @Override
    public List<HistoryOrderVo> queryList(HistoryOrderBo bo) {
        LambdaQueryWrapper<HistoryOrder> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<HistoryOrder> buildQueryWrapper(HistoryOrderBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<HistoryOrder> lqw = Wrappers.lambdaQuery();
        if (null != bo.getNumber()) {
            lqw.and(lq -> lq.eq(HistoryOrder::getNumber, bo.getNumber()).or().eq(HistoryOrder::getParentNumber, bo.getNumber()).or().eq(HistoryOrder::getCollectiveNumber, bo.getNumber()));
        }
        if (StringUtils.isNumeric(bo.getProductName())) {
            bo.setProductId(Long.parseLong(bo.getProductName()));
            bo.setProductName("");
        }
        lqw.eq(bo.getProductId() != null, HistoryOrder::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), HistoryOrder::getProductName, bo.getProductName());
        lqw.eq(StringUtils.isNotBlank(bo.getPickupMethod()), HistoryOrder::getPickupMethod, bo.getPickupMethod());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderType()), HistoryOrder::getOrderType, bo.getOrderType());
        lqw.eq(bo.getTotalAmount() != null, HistoryOrder::getTotalAmount, bo.getTotalAmount());
        lqw.eq(bo.getReducedPrice() != null, HistoryOrder::getReducedPrice, bo.getReducedPrice());
        lqw.eq(bo.getWantAmount() != null, HistoryOrder::getWantAmount, bo.getWantAmount());
        lqw.eq(bo.getOutAmount() != null, HistoryOrder::getOutAmount, bo.getOutAmount());
        lqw.eq(bo.getPayTime() != null, HistoryOrder::getPayTime, bo.getPayTime());
        lqw.eq(bo.getExpireDate() != null, HistoryOrder::getExpireDate, bo.getExpireDate());
        lqw.eq(bo.getCount() != null, HistoryOrder::getCount, bo.getCount());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), HistoryOrder::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getAccount()), HistoryOrder::getAccount, bo.getAccount());
        lqw.eq(StringUtils.isNotBlank(bo.getVerificationStatus()), HistoryOrder::getVerificationStatus, bo.getVerificationStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getSendStatus()), HistoryOrder::getSendStatus, bo.getSendStatus());
        lqw.eq(bo.getSendTime() != null, HistoryOrder::getSendTime, bo.getSendTime());
        lqw.eq(StringUtils.isNotBlank(bo.getCancelStatus()), HistoryOrder::getCancelStatus, bo.getCancelStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getExternalProductId()), HistoryOrder::getExternalProductId, bo.getExternalProductId());
        lqw.eq(bo.getExternalProductSendValue() != null, HistoryOrder::getExternalProductSendValue, bo.getExternalProductSendValue());
        lqw.eq(StringUtils.isNotBlank(bo.getExternalOrderNumber()), HistoryOrder::getExternalOrderNumber, bo.getExternalOrderNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getPushNumber()), HistoryOrder::getPushNumber, bo.getPushNumber());
        lqw.eq(StringUtils.isNotBlank(bo.getFailReason()), HistoryOrder::getFailReason, bo.getFailReason());
        lqw.like(StringUtils.isNotBlank(bo.getOrderCityName()), HistoryOrder::getOrderCityName, bo.getOrderCityName());
        lqw.eq(StringUtils.isNotBlank(bo.getOrderCityCode()), HistoryOrder::getOrderCityCode, bo.getOrderCityCode());
        lqw.eq(bo.getPlatformKey() != null, HistoryOrder::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getPayMerchant() != null, HistoryOrder::getPayMerchant, bo.getPayMerchant());
        lqw.eq(bo.getParentNumber() != null, HistoryOrder::getParentNumber, bo.getParentNumber());
        lqw.eq(bo.getSupplier() != null, HistoryOrder::getSupplier, bo.getSupplier());
        lqw.between(params.get("beginCreateTime") != null && params.get("endCreateTime") != null,
            HistoryOrder::getCreateTime, params.get("beginCreateTime"), params.get("endCreateTime"));
        return lqw;
    }

    /**
     * 新增历史订单
     */
    @Override
    public Boolean insertByBo(HistoryOrderBo bo) {
        HistoryOrder add = BeanUtil.toBean(bo, HistoryOrder.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setNumber(add.getNumber());
        }
        return flag;
    }

    /**
     * 修改历史订单
     */
    @Override
    public Boolean updateByBo(HistoryOrderBo bo) {
        HistoryOrder update = BeanUtil.toBean(bo, HistoryOrder.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(HistoryOrder entity) {
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除历史订单
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
