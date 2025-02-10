package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.CommercialActivityApply;
import org.dromara.shopping.domain.bo.CommercialActivityApplyBo;
import org.dromara.shopping.domain.vo.CommercialActivityApplyVo;
import org.dromara.shopping.mapper.CommercialActivityApplyMapper;
import org.dromara.shopping.service.ICommercialActivityApplyService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 商户活动报名Service业务层处理
 *
 * @author yzg
 * @date 2024-04-10
 */
@RequiredArgsConstructor
@Service
public class CommercialActivityApplyServiceImpl implements ICommercialActivityApplyService {

    private final CommercialActivityApplyMapper baseMapper;

    /**
     * 查询商户活动报名
     */
    @Override
    public CommercialActivityApplyVo queryById(Long applyId){
        return baseMapper.selectVoById(applyId);
    }

    /**
     * 查询商户活动报名列表
     */
    @Override
    public TableDataInfo<CommercialActivityApplyVo> queryPageList(CommercialActivityApplyBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<CommercialActivityApply> lqw = buildQueryWrapper(bo);
        Page<CommercialActivityApplyVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询商户活动报名列表
     */
    @Override
    public List<CommercialActivityApplyVo> queryList(CommercialActivityApplyBo bo) {
        LambdaQueryWrapper<CommercialActivityApply> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<CommercialActivityApply> buildQueryWrapper(CommercialActivityApplyBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<CommercialActivityApply> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getActivityId() != null, CommercialActivityApply::getActivityId, bo.getActivityId());
        lqw.like(StringUtils.isNotBlank(bo.getActivityName()), CommercialActivityApply::getActivityName, bo.getActivityName());
        lqw.eq(bo.getCommercialTenantId() != null, CommercialActivityApply::getCommercialTenantId, bo.getCommercialTenantId());
        lqw.like(StringUtils.isNotBlank(bo.getCommercialTenantName()), CommercialActivityApply::getCommercialTenantName, bo.getCommercialTenantName());
        lqw.eq(StringUtils.isNotBlank(bo.getCommercialTenantTitle()), CommercialActivityApply::getCommercialTenantTitle, bo.getCommercialTenantTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getMeansOfPayments()), CommercialActivityApply::getMeansOfPayments, bo.getMeansOfPayments());
        lqw.eq(bo.getMeansOfRatio() != null, CommercialActivityApply::getMeansOfRatio, bo.getMeansOfRatio());
        lqw.eq(StringUtils.isNotBlank(bo.getPublicity()), CommercialActivityApply::getPublicity, bo.getPublicity());
        lqw.eq(StringUtils.isNotBlank(bo.getApplyStatus()), CommercialActivityApply::getApplyStatus, bo.getApplyStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getRejectCause()), CommercialActivityApply::getRejectCause, bo.getRejectCause());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), CommercialActivityApply::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增商户活动报名
     */
    @Override
    public Boolean insertByBo(CommercialActivityApplyBo bo) {
        CommercialActivityApply add = BeanUtil.toBean(bo, CommercialActivityApply.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setApplyId(add.getApplyId());
        }
        return flag;
    }

    /**
     * 修改商户活动报名
     */
    @Override
    public Boolean updateByBo(CommercialActivityApplyBo bo) {
        CommercialActivityApply update = BeanUtil.toBean(bo, CommercialActivityApply.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(CommercialActivityApply entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除商户活动报名
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
