package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformCityGroup;
import org.dromara.shopping.domain.bo.PlatformCityGroupBo;
import org.dromara.shopping.domain.vo.PlatformCityGroupVo;
import org.dromara.shopping.mapper.PlatformCityGroupMapper;
import org.dromara.shopping.service.IPlatformCityGroupService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 平台城市企业微信群Service业务层处理
 *
 * @author yzg
 * @date 2024-02-21
 */
@RequiredArgsConstructor
@Service
public class PlatformCityGroupServiceImpl implements IPlatformCityGroupService {

    private final PlatformCityGroupMapper baseMapper;

    /**
     * 查询平台城市企业微信群
     */
    @Override
    public PlatformCityGroupVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询平台城市企业微信群列表
     */
    @Override
    public TableDataInfo<PlatformCityGroupVo> queryPageList(PlatformCityGroupBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformCityGroup> lqw = buildQueryWrapper(bo);
        Page<PlatformCityGroupVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台城市企业微信群列表
     */
    @Override
    public List<PlatformCityGroupVo> queryList(PlatformCityGroupBo bo) {
        LambdaQueryWrapper<PlatformCityGroup> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformCityGroup> buildQueryWrapper(PlatformCityGroupBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformCityGroup> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformCityGroup::getPlatformKey, bo.getPlatformKey());
        lqw.like(StringUtils.isNotBlank(bo.getCityName()), PlatformCityGroup::getCityName, bo.getCityName());
        lqw.eq(StringUtils.isNotBlank(bo.getCityCode()), PlatformCityGroup::getCityCode, bo.getCityCode());
        lqw.eq(StringUtils.isNotBlank(bo.getGroupImages()), PlatformCityGroup::getGroupImages, bo.getGroupImages());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), PlatformCityGroup::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增平台城市企业微信群
     */
    @Override
    public Boolean insertByBo(PlatformCityGroupBo bo) {
        PlatformCityGroup add = BeanUtil.toBean(bo, PlatformCityGroup.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改平台城市企业微信群
     */
    @Override
    public Boolean updateByBo(PlatformCityGroupBo bo) {
        PlatformCityGroup update = BeanUtil.toBean(bo, PlatformCityGroup.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformCityGroup entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除平台城市企业微信群
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
