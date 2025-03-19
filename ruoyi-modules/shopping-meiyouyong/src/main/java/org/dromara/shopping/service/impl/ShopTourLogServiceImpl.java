package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Shop;
import org.dromara.shopping.domain.ShopTourLog;
import org.dromara.shopping.domain.bo.ShopTourLogBo;
import org.dromara.shopping.domain.vo.ShopTourLogVo;
import org.dromara.shopping.domain.vo.ShopVo;
import org.dromara.shopping.mapper.ShopMapper;
import org.dromara.shopping.mapper.ShopTourLogMapper;
import org.dromara.shopping.service.IShopTourLogService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 巡检记录Service业务层处理
 *
 * @author yzg
 * @date 2024-03-06
 */
@RequiredArgsConstructor
@Service
public class ShopTourLogServiceImpl implements IShopTourLogService {

    private final ShopTourLogMapper baseMapper;
    private final ShopMapper shopMapper;

    /**
     * 查询巡检记录
     */
    @Override
    public ShopTourLogVo queryById(Long tourLogId){
        return baseMapper.selectVoById(tourLogId);
    }

    /**
     * 查询巡检记录列表
     */
    @Override
    public TableDataInfo<ShopTourLogVo> queryPageList(ShopTourLogBo bo, PageQuery pageQuery) {
        if (StringUtils.isNotEmpty(bo.getShopName())) {
            List<ShopVo> shopVos = shopMapper.selectVoList(new LambdaQueryWrapper<Shop>().like(Shop::getShopName, bo.getShopName()));
            if (ObjectUtil.isNotEmpty(shopVos)) {
                bo.setShopsIds(shopVos.stream().map(ShopVo::getShopId).collect(Collectors.toList()));
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        }
        LambdaQueryWrapper<ShopTourLog> lqw = buildQueryWrapper(bo);
        Page<ShopTourLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<ShopTourLogVo> dataInfo = TableDataInfo.build(result);
        for (ShopTourLogVo row : dataInfo.getRows()) {
            ShopVo shopVo = shopMapper.selectVoById(row.getShopId());
            if (ObjectUtil.isNotEmpty(shopVo)) {
                row.setShopVo(shopVo);
            }
        }
        return dataInfo;
    }

    /**
     * 查询巡检记录列表
     */
    @Override
    public List<ShopTourLogVo> queryList(ShopTourLogBo bo) {
        LambdaQueryWrapper<ShopTourLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ShopTourLog> buildQueryWrapper(ShopTourLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ShopTourLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getTourId() != null, ShopTourLog::getTourId, bo.getTourId());
        lqw.eq(bo.getVerifierId() != null, ShopTourLog::getVerifierId, bo.getVerifierId());
        lqw.eq(StringUtils.isNotBlank(bo.getOperType()), ShopTourLog::getOperType, bo.getOperType());
        lqw.eq(bo.getShopId() != null, ShopTourLog::getShopId, bo.getShopId());
        //lqw.like(StringUtils.isNotBlank(bo.getShopName()), ShopTourLog::getShopName, bo.getShopName());
        lqw.eq(StringUtils.isNotBlank(bo.getAddress()), ShopTourLog::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getAdminMobile()), ShopTourLog::getAdminMobile, bo.getAdminMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getShopStatus()), ShopTourLog::getShopStatus, bo.getShopStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getVerifierImage()), ShopTourLog::getVerifierImage, bo.getVerifierImage());
        lqw.eq(StringUtils.isNotBlank(bo.getGoodsImage()), ShopTourLog::getGoodsImage, bo.getGoodsImage());
        lqw.eq(StringUtils.isNotBlank(bo.getShopImage()), ShopTourLog::getShopImage, bo.getShopImage());
        lqw.eq(StringUtils.isNotBlank(bo.getTourRemark()), ShopTourLog::getTourRemark, bo.getTourRemark());
        lqw.eq(StringUtils.isNotBlank(bo.getOldMerchantNo()), ShopTourLog::getOldMerchantNo, bo.getOldMerchantNo());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantType()), ShopTourLog::getMerchantType, bo.getMerchantType());
        lqw.eq(StringUtils.isNotBlank(bo.getMerchantNo()), ShopTourLog::getMerchantNo, bo.getMerchantNo());
        lqw.eq(StringUtils.isNotBlank(bo.getIsActivity()), ShopTourLog::getIsActivity, bo.getIsActivity());
        lqw.eq(StringUtils.isNotBlank(bo.getIsClose()), ShopTourLog::getIsClose, bo.getIsClose());
        lqw.eq(StringUtils.isNotBlank(bo.getCheckFailReason()), ShopTourLog::getCheckFailReason, bo.getCheckFailReason());
        lqw.in(ObjectUtil.isNotEmpty(bo.getShopsIds()), ShopTourLog::getShopId, bo.getShopsIds());
        return lqw;
    }

    /**
     * 新增巡检记录
     */
    @Override
    public Boolean insertByBo(ShopTourLogBo bo) {
        ShopTourLog add = BeanUtil.toBean(bo, ShopTourLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTourLogId(add.getTourLogId());
        }
        return flag;
    }

    /**
     * 修改巡检记录
     */
    @Override
    public Boolean updateByBo(ShopTourLogBo bo) {
        ShopTourLog update = BeanUtil.toBean(bo, ShopTourLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ShopTourLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除巡检记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
