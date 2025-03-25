package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.UnionpayMissionProgress;
import org.dromara.shopping.domain.UnionpayMissionUser;
import org.dromara.shopping.domain.bo.UnionpayMissionProgressBo;
import org.dromara.shopping.domain.bo.UserBo;
import org.dromara.shopping.domain.vo.UnionpayMissionProgressVo;
import org.dromara.shopping.domain.vo.UnionpayMissionUserVo;
import org.dromara.shopping.domain.vo.UserVo;
import org.dromara.shopping.mapper.UnionpayMissionProgressMapper;
import org.dromara.shopping.mapper.UnionpayMissionUserMapper;
import org.dromara.shopping.service.IUnionpayMissionProgressService;
import org.dromara.shopping.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 银联任务进度Service业务层处理
 *
 * @author yzg
 * @date 2024-02-22
 */
@RequiredArgsConstructor
@Service
public class UnionpayMissionProgressServiceImpl implements IUnionpayMissionProgressService {

    private final UnionpayMissionProgressMapper baseMapper;
    private final IUserService userService;
    private final UnionpayMissionUserMapper unionpayMissionUserMapper;

    /**
     * 查询银联任务进度
     */
    @Override
    public UnionpayMissionProgressVo queryById(Long progressId){
        return baseMapper.selectVoById(progressId);
    }

    /**
     * 查询银联任务进度列表
     */
    @Override
    public TableDataInfo<UnionpayMissionProgressVo> queryPageList(UnionpayMissionProgressBo bo, PageQuery pageQuery) {
        if (ObjectUtil.isNotEmpty(bo.getUpMissionUserId())) {
            UserBo userBo = new UserBo();
            userBo.setMobile(bo.getUpMissionUserId().toString());
            List<UserVo> userVos = userService.queryList(userBo);
            if (ObjectUtil.isNotEmpty(userVos)) {
                UnionpayMissionUserVo missionUserVo = unionpayMissionUserMapper.selectVoOne(new LambdaQueryWrapper<UnionpayMissionUser>().eq(UnionpayMissionUser::getUserId, userVos.get(0).getUserId()).last("limit 1"));
                if (ObjectUtil.isNotEmpty(missionUserVo)) {
                    bo.setUpMissionUserId(missionUserVo.getUpMissionUserId());
                } else {
                    return TableDataInfo.build(new ArrayList<>());
                }
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        }
        LambdaQueryWrapper<UnionpayMissionProgress> lqw = buildQueryWrapper(bo);
        Page<UnionpayMissionProgressVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<UnionpayMissionProgressVo> dataInfo = TableDataInfo.build(result);
        for (UnionpayMissionProgressVo row : dataInfo.getRows()) {
            UnionpayMissionUserVo missionUserVo = unionpayMissionUserMapper.selectVoById(row.getUpMissionUserId());
            if (ObjectUtil.isNotEmpty(missionUserVo)) {
                //查询用户
                row.setUserVo(userService.queryById(missionUserVo.getUserId()));
            }
        }
        return dataInfo;
    }

    /**
     * 查询银联任务进度列表
     */
    @Override
    public List<UnionpayMissionProgressVo> queryList(UnionpayMissionProgressBo bo) {
        LambdaQueryWrapper<UnionpayMissionProgress> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UnionpayMissionProgress> buildQueryWrapper(UnionpayMissionProgressBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UnionpayMissionProgress> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUpMissionId() != null, UnionpayMissionProgress::getUpMissionId, bo.getUpMissionId());
        lqw.eq(bo.getUpMissionGroupId() != null, UnionpayMissionProgress::getUpMissionGroupId, bo.getUpMissionGroupId());
        lqw.eq(bo.getUpMissionUserId() != null, UnionpayMissionProgress::getUpMissionUserId, bo.getUpMissionUserId());
        lqw.eq(bo.getDayProgress() != null, UnionpayMissionProgress::getDayProgress, bo.getDayProgress());
        lqw.eq(bo.getWeekProgress() != null, UnionpayMissionProgress::getWeekProgress, bo.getWeekProgress());
        lqw.eq(bo.getMonthProgress() != null, UnionpayMissionProgress::getMonthProgress, bo.getMonthProgress());
        lqw.eq(bo.getActivityProgress() != null, UnionpayMissionProgress::getActivityProgress, bo.getActivityProgress());
        return lqw;
    }

    /**
     * 新增银联任务进度
     */
    @Override
    public Boolean insertByBo(UnionpayMissionProgressBo bo) {
        UnionpayMissionProgress add = BeanUtil.toBean(bo, UnionpayMissionProgress.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setProgressId(add.getProgressId());
        }
        return flag;
    }

    /**
     * 修改银联任务进度
     */
    @Override
    public Boolean updateByBo(UnionpayMissionProgressBo bo) {
        UnionpayMissionProgress update = BeanUtil.toBean(bo, UnionpayMissionProgress.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(UnionpayMissionProgress entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除银联任务进度
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
