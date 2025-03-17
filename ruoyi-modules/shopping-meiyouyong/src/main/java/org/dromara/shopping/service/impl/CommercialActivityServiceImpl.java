package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialActivity;
import org.dromara.shopping.domain.bo.CommercialActivityBo;
import org.dromara.shopping.domain.vo.CommercialActivityVo;
import org.dromara.shopping.mapper.CommercialActivityMapper;
import org.dromara.shopping.service.ICommercialActivityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户活动Service业务层处理
 *
 * @author yzg
 * @date 2024-03-26
 */
@RequiredArgsConstructor
@Service
public class CommercialActivityServiceImpl implements ICommercialActivityService {

    private final CommercialActivityMapper baseMapper;

    /**
     * 查询商户活动
     */
    @Override
    public CommercialActivityVo queryById(Long activityId){
        return baseMapper.selectVoById(activityId);
    }

    /**
     * 查询商户活动列表
     */
    @Override
    public TableDataInfo<CommercialActivityVo> queryPageList(CommercialActivityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialActivity> lqw = buildQueryWrapper(bo);
        Page<CommercialActivityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户活动列表
     */
    @Override
    public List<CommercialActivityVo> queryList(CommercialActivityBo bo) {
        LambdaQueryWrapper<CommercialActivity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialActivity> buildQueryWrapper(CommercialActivityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialActivity> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getActivityName()), CommercialActivity::getActivityName, bo.getActivityName());
        lqw.eq(null != bo.getBankId(), CommercialActivity::getBankId, bo.getBankId());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityArea()), CommercialActivity::getActivityArea, bo.getActivityArea());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityImg()), CommercialActivity::getActivityImg, bo.getActivityImg());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityPlatform()), CommercialActivity::getActivityPlatform, bo.getActivityPlatform());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityType()), CommercialActivity::getActivityType, bo.getActivityType());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityStatus()), CommercialActivity::getActivityStatus, bo.getActivityStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CommercialActivity::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商户活动
     */
    @Override
    public Boolean insertByBo(CommercialActivityBo bo) {
        CommercialActivity add = BeanUtil.toBean(bo, CommercialActivity.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActivityId(add.getActivityId());
        }
        return flag;
    }

    /**
     * 修改商户活动
     */
    @Override
    public Boolean updateByBo(CommercialActivityBo bo) {
        CommercialActivity update = BeanUtil.toBean(bo, CommercialActivity.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommercialActivity entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户活动
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
