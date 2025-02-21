package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.MissionUser;
import org.dromara.shopping.domain.bo.MissionUserBo;
import org.dromara.shopping.domain.vo.MissionUserVo;
import org.dromara.shopping.mapper.MissionUserMapper;
import org.dromara.shopping.service.IMissionUserService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 任务用户Service业务层处理
 *
 * @author yzg
 * @date 2023-05-10
 */
@RequiredArgsConstructor
@Service
public class MissionUserServiceImpl implements IMissionUserService {

    private final MissionUserMapper baseMapper;

    /**
     * 查询任务用户
     */
    @Override
    public MissionUserVo queryById(Long missionUserId) {
        return baseMapper.selectVoById(missionUserId);
    }

    /**
     * 查询任务用户列表
     */
    @Override
    public TableDataInfo<MissionUserVo> queryPageList(MissionUserBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<MissionUser> lqw = buildQueryWrapper(bo);
        Page<MissionUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询任务用户列表
     */
    @Override
    public List<MissionUserVo> queryList(MissionUserBo bo) {
        LambdaQueryWrapper<MissionUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<MissionUser> buildQueryWrapper(MissionUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<MissionUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMissionGroupId() != null, MissionUser::getMissionGroupId, bo.getMissionGroupId());
        lqw.eq(bo.getUserId() != null, MissionUser::getUserId, bo.getUserId());
        lqw.eq(bo.getPlatformKey() != null, MissionUser::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), MissionUser::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增任务用户
     */
    @Override
    public Boolean insertByBo(MissionUserBo bo) {
        MissionUser add = BeanUtil.toBean(bo, MissionUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMissionUserId(add.getMissionUserId());
        }
        return flag;
    }

    /**
     * 修改任务用户
     */
    @Override
    public Boolean updateByBo(MissionUserBo bo) {
        MissionUser update = BeanUtil.toBean(bo, MissionUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(MissionUser entity) {
        // 校验是否已经报名过了
        LambdaQueryWrapper<MissionUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(MissionUser::getMissionGroupId, entity.getMissionGroupId());
        lqw.eq(MissionUser::getUserId, entity.getUserId());
        lqw.eq(MissionUser::getPlatformKey, entity.getPlatformKey());

        MissionUserVo missionUserVo = baseMapper.selectVoOne(lqw);
        if (null != missionUserVo) {
            if (null == entity.getMissionUserId() || !missionUserVo.getMissionUserId().equals(entity.getMissionUserId())) {
                throw new ServiceException("该用户已报名，不可重复报名");
            }
        }
    }

    /**
     * 批量删除任务用户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
