package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.GroupActivity;
import org.dromara.shopping.domain.bo.GroupActivityBo;
import org.dromara.shopping.domain.vo.GroupActivityVo;
import org.dromara.shopping.mapper.GroupActivityMapper;
import org.dromara.shopping.service.IGroupActivityService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 拼团活动Service业务层处理
 *
 * @author yzg
 * @date 2024-09-26
 */
@RequiredArgsConstructor
@Service
public class GroupActivityServiceImpl implements IGroupActivityService {

    private final GroupActivityMapper baseMapper;

    /**
     * 查询拼团活动
     */
    @Override
    public GroupActivityVo queryById(Long activityId) {
        return baseMapper.selectVoById(activityId);
    }

    /**
     * 查询拼团活动列表
     */
    @Override
    public TableDataInfo<GroupActivityVo> queryPageList(GroupActivityBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GroupActivity> lqw = buildQueryWrapper(bo);
        Page<GroupActivityVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询拼团活动列表
     */
    @Override
    public List<GroupActivityVo> queryList(GroupActivityBo bo) {
        LambdaQueryWrapper<GroupActivity> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<GroupActivity> buildQueryWrapper(GroupActivityBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<GroupActivity> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getActivityName()), GroupActivity::getActivityName, bo.getActivityName());
        lqw.like(StringUtils.isNotBlank(bo.getActivityShortName()), GroupActivity::getActivityShortName, bo.getActivityShortName());
//        lqw.eq(bo.getActivityStartDate() != null, GroupActivity::getActivityStartDate, bo.getActivityStartDate());
//        lqw.eq(bo.getActivityEndDate() != null, GroupActivity::getActivityEndDate, bo.getActivityEndDate());
        lqw.between(params.get("beginStartDate") != null && params.get("endStartDate") != null,
            GroupActivity::getActivityStartDate, params.get("beginStartDate"), params.get("endStartDate"));
        lqw.between(params.get("beginEndDate") != null && params.get("endEndDate") != null,
            GroupActivity::getActivityEndDate, params.get("beginEndDate"), params.get("endEndDate"));
        lqw.eq(StringUtils.isNotBlank(bo.getActivityStatus()), GroupActivity::getActivityStatus, bo.getActivityStatus());
        lqw.eq(bo.getActivityCount() != null, GroupActivity::getActivityCount, bo.getActivityCount());
        lqw.eq(bo.getActivityPrice() != null, GroupActivity::getActivityPrice, bo.getActivityPrice());
        lqw.eq(StringUtils.isNotBlank(bo.getActivityRule()), GroupActivity::getActivityRule, bo.getActivityRule());
        lqw.eq(StringUtils.isNotBlank(bo.getShareTitle()), GroupActivity::getShareTitle, bo.getShareTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getShareImage()), GroupActivity::getShareImage, bo.getShareImage());
        lqw.eq(bo.getGroupValid() != null, GroupActivity::getGroupValid, bo.getGroupValid());
        lqw.eq(bo.getProductId() != null, GroupActivity::getProductId, bo.getProductId());
        lqw.eq(StringUtils.isNotBlank(bo.getIsGroup()), GroupActivity::getIsGroup, bo.getIsGroup());
        lqw.eq(StringUtils.isNotBlank(bo.getSharePoster()), GroupActivity::getSharePoster, bo.getSharePoster());
        lqw.eq(StringUtils.isNotBlank(bo.getSharePosterTitle()), GroupActivity::getSharePosterTitle, bo.getSharePosterTitle());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupTime()), GroupActivity::getGroupTime, bo.getGroupTime());
        lqw.eq(bo.getPlatformKey() != null, GroupActivity::getPlatformKey, bo.getPlatformKey());
        return lqw;
    }

    /**
     * 新增拼团活动
     */
    @Override
    public Boolean insertByBo(GroupActivityBo bo) {
        GroupActivity add = BeanUtil.toBean(bo, GroupActivity.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setActivityId(add.getActivityId());
        }
        return flag;
    }

    /**
     * 修改拼团活动
     */
    @Override
    public Boolean updateByBo(GroupActivityBo bo) {
        GroupActivity update = BeanUtil.toBean(bo, GroupActivity.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GroupActivity entity) {
    }

    /**
     * 批量删除拼团活动
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }

    /**
     * 改变活动状态
     */
    @Override
    public Boolean changeGroupActivity(GroupActivityBo bo) {
        //第一步，查询活动信息
        GroupActivity groupActivity = baseMapper.selectById(bo.getActivityId());
        if (ObjectUtils.isNull(groupActivity)) {
            throw new ServiceException("活动不存在");
        }
        //0-未发布，1-未开始，2-进行中，3-已结束，4-已关闭
        groupActivity.setActivityStatus(bo.getActivityStatus());
        int i = baseMapper.updateById(groupActivity);
        return i > 0;
    }

    /**
     * 拼团商品上下架处理
     */
    @Override
    public void groupStatus() {
        LambdaQueryWrapper<GroupActivity> wrapper = Wrappers.lambdaQuery();
        wrapper.eq(GroupActivity::getGroupTime, "1");
        wrapper.in(GroupActivity::getActivityStatus, "1", "2");
        List<GroupActivityVo> groupActivityVoList = baseMapper.selectVoList(wrapper);
        if (CollectionUtils.isNotEmpty(groupActivityVoList)) {
            for (GroupActivityVo groupActivityVo : groupActivityVoList) {
                if (ObjectUtils.isEmpty(groupActivityVo.getActivityStartDate()) || ObjectUtils.isNotEmpty(groupActivityVo.getActivityEndDate())) {
                    continue;
                }
                groupActivityVo.setActivityStatus("2");
                GroupActivityBo groupActivityBo = BeanUtil.toBean(groupActivityVo, GroupActivityBo.class);
                updateByBo(groupActivityBo);
            }
        }
    }
}
