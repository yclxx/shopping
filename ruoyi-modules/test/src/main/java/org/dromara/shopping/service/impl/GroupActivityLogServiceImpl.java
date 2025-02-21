package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.GroupActivityLog;
import org.dromara.shopping.domain.bo.GroupActivityLogBo;
import org.dromara.shopping.domain.vo.GroupActivityLogVo;
import org.dromara.shopping.mapper.GroupActivityLogMapper;
import org.dromara.shopping.service.IGroupActivityLogService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 拼团活动记录Service业务层处理
 *
 * @author yzg
 * @date 2024-10-10
 */
@RequiredArgsConstructor
@Service
public class GroupActivityLogServiceImpl implements IGroupActivityLogService {

    private final GroupActivityLogMapper baseMapper;

    /**
     * 查询拼团活动记录
     */
    @Override
    public GroupActivityLogVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询拼团活动记录列表
     */
    @Override
    public TableDataInfo<GroupActivityLogVo> queryPageList(GroupActivityLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<GroupActivityLog> lqw = buildQueryWrapper(bo);
        Page<GroupActivityLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询拼团活动记录列表
     */
    @Override
    public List<GroupActivityLogVo> queryList(GroupActivityLogBo bo) {
        LambdaQueryWrapper<GroupActivityLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<GroupActivityLog> buildQueryWrapper(GroupActivityLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<GroupActivityLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getActivityId() != null, GroupActivityLog::getActivityId, bo.getActivityId());
        lqw.like(StringUtils.isNotBlank(bo.getActivityName()), GroupActivityLog::getActivityName, bo.getActivityName());
        lqw.eq(bo.getProductId() != null, GroupActivityLog::getProductId, bo.getProductId());
        lqw.like(StringUtils.isNotBlank(bo.getProductName()), GroupActivityLog::getProductName, bo.getProductName());
        lqw.eq(bo.getSellingPrice() != null, GroupActivityLog::getSellingPrice, bo.getSellingPrice());
        lqw.eq(bo.getGroupPrice() != null, GroupActivityLog::getGroupPrice, bo.getGroupPrice());
        lqw.eq(bo.getGroupCount() != null, GroupActivityLog::getGroupCount, bo.getGroupCount());
        lqw.eq(bo.getAttendCount() != null, GroupActivityLog::getAttendCount, bo.getAttendCount());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupStatus()), GroupActivityLog::getGroupStatus, bo.getGroupStatus());
        lqw.eq(bo.getGroupTime() != null, GroupActivityLog::getGroupTime, bo.getGroupTime());
        return lqw;
    }

    /**
     * 新增拼团活动记录
     */
    @Override
    public Boolean insertByBo(GroupActivityLogBo bo) {
        GroupActivityLog add = BeanUtil.toBean(bo, GroupActivityLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改拼团活动记录
     */
    @Override
    public Boolean updateByBo(GroupActivityLogBo bo) {
        GroupActivityLog update = BeanUtil.toBean(bo, GroupActivityLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(GroupActivityLog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除拼团活动记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
