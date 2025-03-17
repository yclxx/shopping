package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.Mission;
import org.dromara.shopping.domain.bo.MissionBo;
import org.dromara.shopping.domain.vo.MissionVo;
import org.dromara.shopping.mapper.MissionMapper;
import org.dromara.shopping.service.IMissionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 任务配置Service业务层处理
 *
 * @author yzg
 * @date 2023-05-10
 */
@RequiredArgsConstructor
@Service
public class MissionServiceImpl implements IMissionService {

    private final MissionMapper baseMapper;

    /**
     * 查询任务配置
     */
    @Override
    public MissionVo queryById(Long missionId) {
        return baseMapper.selectVoById(missionId);
    }

    /**
     * 查询任务配置列表
     */
    @Override
    public TableDataInfo<MissionVo> queryPageList(MissionBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Mission> lqw = buildQueryWrapper(bo);
        Page<MissionVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询任务配置列表
     */
    @Override
    public List<MissionVo> queryList(MissionBo bo) {
        LambdaQueryWrapper<Mission> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Mission> buildQueryWrapper(MissionBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Mission> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getMissionGroupId() != null, Mission::getMissionGroupId, bo.getMissionGroupId());
        lqw.like(StringUtils.isNotBlank(bo.getMissionName()), Mission::getMissionName, bo.getMissionName());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), Mission::getStatus, bo.getStatus());
        lqw.eq(bo.getPlatformKey() != null, Mission::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getMissionAffiliation()), Mission::getMissionAffiliation, bo.getMissionAffiliation());
        return lqw;
    }

    /**
     * 新增任务配置
     */
    @Override
    public Boolean insertByBo(MissionBo bo) {
        Mission add = BeanUtil.toBean(bo, Mission.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setMissionId(add.getMissionId());
        }
        return flag;
    }

    /**
     * 修改任务配置
     */
    @Override
    public Boolean updateByBo(MissionBo bo) {
        Mission update = BeanUtil.toBean(bo, Mission.class);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 批量删除任务配置
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
