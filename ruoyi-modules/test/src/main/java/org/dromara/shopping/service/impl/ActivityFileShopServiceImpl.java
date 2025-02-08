package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.excel.utils.ExcelUtil;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.common.redis.utils.RedisUtils;
import org.dromara.shopping.domain.ActivityFileShop;
import org.dromara.shopping.domain.MerchantType;
import org.dromara.shopping.domain.bo.ActivityFileShopBo;
import org.dromara.shopping.domain.bo.FileImportLogBo;
import org.dromara.shopping.domain.vo.ActivityFileShopVo;
import org.dromara.shopping.domain.vo.MerchantTypeVo;
import org.dromara.shopping.mapper.ActivityFileShopMapper;
import org.dromara.shopping.mapper.MerchantTypeMapper;
import org.dromara.shopping.service.IActivityFileShopService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 活动商户Service业务层处理
 *
 * @author yzg
 * @date 2024-01-03
 */
@RequiredArgsConstructor
@Service
public class ActivityFileShopServiceImpl implements IActivityFileShopService {

    private final ActivityFileShopMapper baseMapper;

    /**
     * 查询活动商户
     */
    @Override
    public ActivityFileShopVo queryById(Long activityShopId){
        return baseMapper.selectVoById(activityShopId);
    }

    /**
     * 查询活动商户列表
     */
    @Override
    public TableDataInfo<ActivityFileShopVo> queryPageList(ActivityFileShopBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<ActivityFileShop> lqw = buildQueryWrapper(bo);
        Page<ActivityFileShopVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询活动商户列表
     */
    @Override
    public List<ActivityFileShopVo> queryList(ActivityFileShopBo bo) {
        LambdaQueryWrapper<ActivityFileShop> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<ActivityFileShop> buildQueryWrapper(ActivityFileShopBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<ActivityFileShop> lqw = Wrappers.lambdaQuery();
        //lqw.like(StringUtils.isNotBlank(bo.getActivityShopName()), ActivityFileShop::getActivityShopName, bo.getActivityShopName());
        //lqw.eq(StringUtils.isNotBlank(bo.getAddress()), ActivityFileShop::getAddress, bo.getAddress());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), ActivityFileShop::getStatus, bo.getStatus());
        //lqw.eq(StringUtils.isNotBlank(bo.getFormattedAddress()), ActivityFileShop::getFormattedAddress, bo.getFormattedAddress());
        //lqw.eq(StringUtils.isNotBlank(bo.getProvince()), ActivityFileShop::getProvince, bo.getProvince());
        //lqw.eq(StringUtils.isNotBlank(bo.getCity()), ActivityFileShop::getCity, bo.getCity());
        //lqw.eq(StringUtils.isNotBlank(bo.getDistrict()), ActivityFileShop::getDistrict, bo.getDistrict());
        //lqw.eq(StringUtils.isNotBlank(bo.getProcode()), ActivityFileShop::getProcode, bo.getProcode());
        //lqw.eq(StringUtils.isNotBlank(bo.getCitycode()), ActivityFileShop::getCitycode, bo.getCitycode());
        //lqw.eq(StringUtils.isNotBlank(bo.getAdcode()), ActivityFileShop::getAdcode, bo.getAdcode());
        lqw.like(StringUtils.isNotBlank(bo.getFileName()), ActivityFileShop::getFileName, bo.getFileName());
        lqw.eq(StringUtils.isNotBlank(bo.getFileId()), ActivityFileShop::getFileId, bo.getFileId());
        lqw.eq(bo.getTypeId() != null, ActivityFileShop::getTypeId, bo.getTypeId());
        lqw.eq(StringUtils.isNotBlank(bo.getIndexUrl()), ActivityFileShop::getIndexUrl, bo.getIndexUrl());
        lqw.eq(bo.getLongitude() != null, ActivityFileShop::getLongitude, bo.getLongitude());
        lqw.eq(bo.getLatitude() != null, ActivityFileShop::getLatitude, bo.getLatitude());
        lqw.eq(bo.getSort() != null, ActivityFileShop::getSort, bo.getSort());
        if (StringUtils.isNotBlank(bo.getActivityShopName())) {
            lqw.and(lq ->
                lq.like(ActivityFileShop::getActivityShopName, bo.getActivityShopName()).or().like(ActivityFileShop::getAddress, bo.getActivityShopName())
                    .or().like(ActivityFileShop::getFormattedAddress, bo.getActivityShopName())
            );
        }
        if (StringUtils.isNotBlank(bo.getProvince())) {
            lqw.and(lq ->
                lq.like(ActivityFileShop::getProvince, bo.getProvince()).or().like(ActivityFileShop::getCity, bo.getProvince())
                    .or().like(ActivityFileShop::getDistrict, bo.getProvince())
                    .or().like(ActivityFileShop::getProcode, bo.getProvince())
                    .or().like(ActivityFileShop::getCitycode, bo.getProvince())
                    .or().like(ActivityFileShop::getAdcode, bo.getProvince())
            );
        }
        return lqw;
    }

    /**
     * 新增活动商户
     */
    @Override
    public Boolean insertByBo(ActivityFileShopBo bo) {
        ActivityFileShop add = BeanUtil.toBean(bo, ActivityFileShop.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActivityShopId(add.getActivityShopId());
        }
        return flag;
    }

    /**
     * 修改活动商户
     */
    @Override
    public Boolean updateByBo(ActivityFileShopBo bo) {
        ActivityFileShop update = BeanUtil.toBean(bo, ActivityFileShop.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(ActivityFileShop entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除活动商户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

}
