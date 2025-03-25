package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.UnionpayMissionGroup;
import org.dromara.shopping.domain.bo.UnionpayMissionGroupBo;
import org.dromara.shopping.domain.vo.UnionpayMissionGroupVo;
import org.dromara.shopping.mapper.UnionpayMissionGroupMapper;
import org.dromara.shopping.service.IUnionpayMissionGroupService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 银联任务组Service业务层处理
 *
 * @author yzg
 * @date 2024-02-21
 */
@RequiredArgsConstructor
@Service
public class UnionpayMissionGroupServiceImpl implements IUnionpayMissionGroupService {

    private final UnionpayMissionGroupMapper baseMapper;

    /**
     * 查询银联任务组
     */
    @Override
    public UnionpayMissionGroupVo queryById(Long upMissionGroupId){
        return baseMapper.selectVoById(upMissionGroupId);
    }

    /**
     * 查询银联任务组列表
     */
    @Override
    public TableDataInfo<UnionpayMissionGroupVo> queryPageList(UnionpayMissionGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UnionpayMissionGroup> lqw = buildQueryWrapper(bo);
        Page<UnionpayMissionGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询银联任务组列表
     */
    @Override
    public List<UnionpayMissionGroupVo> queryList(UnionpayMissionGroupBo bo) {
        LambdaQueryWrapper<UnionpayMissionGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UnionpayMissionGroup> buildQueryWrapper(UnionpayMissionGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UnionpayMissionGroup> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getUpMissionGroupName()), UnionpayMissionGroup::getUpMissionGroupName, bo.getUpMissionGroupName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), UnionpayMissionGroup::getStatus, bo.getStatus());
        lqw.eq(bo.getStartDate() != null, UnionpayMissionGroup::getStartDate, bo.getStartDate());
        lqw.eq(bo.getEndDate() != null, UnionpayMissionGroup::getEndDate, bo.getEndDate());
        lqw.eq(StringUtils.isNotBlank(bo.getUpMissionGroupUpid()), UnionpayMissionGroup::getUpMissionGroupUpid, bo.getUpMissionGroupUpid());
        lqw.eq(bo.getPlatformKey() != null, UnionpayMissionGroup::getPlatformKey, bo.getPlatformKey());
        return lqw;
    }

    /**
     * 新增银联任务组
     */
    @Override
    public Boolean insertByBo(UnionpayMissionGroupBo bo) {
        UnionpayMissionGroup add = BeanUtil.toBean(bo, UnionpayMissionGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUpMissionGroupId(add.getUpMissionGroupId());
        }
        return flag;
    }

    /**
     * 修改银联任务组
     */
    @Override
    public Boolean updateByBo(UnionpayMissionGroupBo bo) {
        UnionpayMissionGroup update = BeanUtil.toBean(bo, UnionpayMissionGroup.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(UnionpayMissionGroup entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除银联任务组
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
