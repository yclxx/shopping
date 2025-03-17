package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.InviteUserLog;
import org.dromara.shopping.domain.bo.InviteUserLogBo;
import org.dromara.shopping.domain.vo.InviteUserLogVo;
import org.dromara.shopping.mapper.InviteUserLogMapper;
import org.dromara.shopping.service.IInviteUserLogService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 邀请记录Service业务层处理
 *
 * @author yzg
 * @date 2023-08-08
 */
@RequiredArgsConstructor
@Service
public class InviteUserLogServiceImpl implements IInviteUserLogService {

    private final InviteUserLogMapper baseMapper;

    /**
     * 查询邀请记录
     */
    @Override
    public InviteUserLogVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询邀请记录列表
     */
    @Override
    public TableDataInfo<InviteUserLogVo> queryPageList(InviteUserLogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<InviteUserLog> lqw = buildQueryWrapper(bo);
        Page<InviteUserLogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询邀请记录列表
     */
    @Override
    public List<InviteUserLogVo> queryList(InviteUserLogBo bo) {
        LambdaQueryWrapper<InviteUserLog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<InviteUserLog> buildQueryWrapper(InviteUserLogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<InviteUserLog> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUserId() != null, InviteUserLog::getUserId, bo.getUserId());
        lqw.eq(bo.getInviteUserId() != null, InviteUserLog::getInviteUserId, bo.getInviteUserId());
        lqw.like(StringUtils.isNotBlank(bo.getInviteCityName()), InviteUserLog::getInviteCityName, bo.getInviteCityName());
        lqw.eq(bo.getNumber() != null, InviteUserLog::getNumber, bo.getNumber());
        lqw.eq(bo.getMissionId() != null, InviteUserLog::getMissionId, bo.getMissionId());
        lqw.eq(bo.getPlatformKey() != null, InviteUserLog::getPlatformKey, bo.getPlatformKey());
        return lqw;
    }

    /**
     * 新增邀请记录
     */
    @Override
    public Boolean insertByBo(InviteUserLogBo bo) {
        InviteUserLog add = BeanUtil.toBean(bo, InviteUserLog.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改邀请记录
     */
    @Override
    public Boolean updateByBo(InviteUserLogBo bo) {
        InviteUserLog update = BeanUtil.toBean(bo, InviteUserLog.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(InviteUserLog entity) {
    }

    /**
     * 批量删除邀请记录
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
