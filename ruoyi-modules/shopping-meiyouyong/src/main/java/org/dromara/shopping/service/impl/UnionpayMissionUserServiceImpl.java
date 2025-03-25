package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.UnionpayMissionUser;
import org.dromara.shopping.domain.bo.UnionpayMissionUserBo;
import org.dromara.shopping.domain.bo.UserBo;
import org.dromara.shopping.domain.vo.UnionpayMissionUserVo;
import org.dromara.shopping.domain.vo.UserVo;
import org.dromara.shopping.mapper.UnionpayMissionUserMapper;
import org.dromara.shopping.service.IUnionpayMissionUserService;
import org.dromara.shopping.service.IUserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 银联任务用户Service业务层处理
 *
 * @author yzg
 * @date 2024-02-21
 */
@RequiredArgsConstructor
@Service
public class UnionpayMissionUserServiceImpl implements IUnionpayMissionUserService {

    private final UnionpayMissionUserMapper baseMapper;
    private final IUserService userService;

    /**
     * 查询银联任务用户
     */
    @Override
    public UnionpayMissionUserVo queryById(Long upMissionUserId){
        return baseMapper.selectVoById(upMissionUserId);
    }

    /**
     * 查询银联任务用户列表
     */
    @Override
    public TableDataInfo<UnionpayMissionUserVo> queryPageList(UnionpayMissionUserBo bo, PageQuery pageQuery) {
        if (ObjectUtil.isNotEmpty(bo.getUserId())) {
            UserBo userBo = new UserBo();
            userBo.setMobile(bo.getUserId().toString());
            bo.setUserId(null);
            List<UserVo> userVos = userService.queryList(userBo);
            if (ObjectUtil.isNotEmpty(userVos)) {
                bo.setUserIds(userVos.stream().map(UserVo::getUserId).collect(Collectors.toList()));
            } else {
                return TableDataInfo.build(new ArrayList<>());
            }
        }
        LambdaQueryWrapper<UnionpayMissionUser> lqw = buildQueryWrapper(bo);
        Page<UnionpayMissionUserVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        TableDataInfo<UnionpayMissionUserVo> dataInfo = TableDataInfo.build(result);
        for (UnionpayMissionUserVo row : dataInfo.getRows()) {
            //查询用户
            row.setUserVo(userService.queryById(row.getUserId()));
        }
        return dataInfo;
    }

    /**
     * 查询银联任务用户列表
     */
    @Override
    public List<UnionpayMissionUserVo> queryList(UnionpayMissionUserBo bo) {
        LambdaQueryWrapper<UnionpayMissionUser> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<UnionpayMissionUser> buildQueryWrapper(UnionpayMissionUserBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<UnionpayMissionUser> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getUpMissionGroupId() != null, UnionpayMissionUser::getUpMissionGroupId, bo.getUpMissionGroupId());
        lqw.eq(bo.getUserId() != null, UnionpayMissionUser::getUserId, bo.getUserId());
        lqw.eq(bo.getPlatformKey() != null, UnionpayMissionUser::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), UnionpayMissionUser::getStatus, bo.getStatus());
        lqw.in(ObjectUtil.isNotEmpty(bo.getUserIds()), UnionpayMissionUser::getUserId, bo.getUserIds());
        return lqw;
    }

    /**
     * 新增银联任务用户
     */
    @Override
    public Boolean insertByBo(UnionpayMissionUserBo bo) {
        UnionpayMissionUser add = BeanUtil.toBean(bo, UnionpayMissionUser.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUpMissionUserId(add.getUpMissionUserId());
        }
        return flag;
    }

    /**
     * 修改银联任务用户
     */
    @Override
    public Boolean updateByBo(UnionpayMissionUserBo bo) {
        UnionpayMissionUser update = BeanUtil.toBean(bo, UnionpayMissionUser.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(UnionpayMissionUser entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除银联任务用户
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
