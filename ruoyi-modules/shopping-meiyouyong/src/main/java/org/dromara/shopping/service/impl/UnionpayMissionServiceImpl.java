package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.UnionpayMission;
import org.dromara.shopping.domain.bo.UnionpayMissionBo;
import org.dromara.shopping.domain.vo.UnionpayMissionVo;
import org.dromara.shopping.mapper.UnionpayMissionMapper;
import org.dromara.shopping.service.IUnionpayMissionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 银联任务配置Service业务层处理
 *
 * @author yzg
 * @date 2024-02-21
 */
@RequiredArgsConstructor
@Service
public class UnionpayMissionServiceImpl implements IUnionpayMissionService {

    private final UnionpayMissionMapper baseMapper;

    /**
     * 查询银联任务配置
     */
    @Override
    public UnionpayMissionVo queryById(Long upMissionId){
        return baseMapper.selectVoById(upMissionId);
    }

    /**
     * 查询银联任务配置列表
     */
    @Override
    public TableDataInfo<UnionpayMissionVo> queryPageList(UnionpayMissionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<UnionpayMission> lqw = buildQueryWrapper(bo);
        Page<UnionpayMissionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<UnionpayMissionVo> dataInfo = TableDataInfo.build(result);
        return dataInfo;
    }

    /**
     * 查询银联任务配置列表
     */
    @Override
    public List<UnionpayMissionVo> queryList(UnionpayMissionBo bo) {
        LambdaQueryWrapper<UnionpayMission> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UnionpayMission> buildQueryWrapper(UnionpayMissionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UnionpayMission> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUpMissionGroupId() != null, UnionpayMission::getUpMissionGroupId, bo.getUpMissionGroupId());
        lqw.like(StringUtils.isNotBlank(bo.getUpMissionName()), UnionpayMission::getUpMissionName, bo.getUpMissionName());
        lqw.eq(bo.getProductId() != null, UnionpayMission::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), UnionpayMission::getStatus, bo.getStatus());
        lqw.eq(bo.getStartDate() != null, UnionpayMission::getStartDate, bo.getStartDate());
        lqw.eq(bo.getEndDate() != null, UnionpayMission::getEndDate, bo.getEndDate());
        lqw.eq(bo.getPlatformKey() != null, UnionpayMission::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getUserCountDay() != null, UnionpayMission::getUserCountDay, bo.getUserCountDay());
        lqw.eq(bo.getUserCountWeek() != null, UnionpayMission::getUserCountWeek, bo.getUserCountWeek());
        lqw.eq(bo.getUserCountMonth() != null, UnionpayMission::getUserCountMonth, bo.getUserCountMonth());
        lqw.eq(bo.getUserCountActivity() != null, UnionpayMission::getUserCountActivity, bo.getUserCountActivity());
        return lqw;
    }

    /**
     * 新增银联任务配置
     */
    @Override
    public Boolean insertByBo(UnionpayMissionBo bo) {
        UnionpayMission add = BeanUtil.toBean(bo, UnionpayMission.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUpMissionId(add.getUpMissionId());
        }
        return flag;
    }

    /**
     * 修改银联任务配置
     */
    @Override
    public Boolean updateByBo(UnionpayMissionBo bo) {
        UnionpayMission update = BeanUtil.toBean(bo, UnionpayMission.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(UnionpayMission entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除银联任务配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
