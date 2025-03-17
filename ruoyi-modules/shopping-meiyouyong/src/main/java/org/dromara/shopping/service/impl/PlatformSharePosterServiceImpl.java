package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformSharePoster;
import org.dromara.shopping.domain.bo.PlatformSharePosterBo;
import org.dromara.shopping.domain.vo.PlatformSharePosterVo;
import org.dromara.shopping.mapper.PlatformSharePosterMapper;
import org.dromara.shopping.service.IPlatformSharePosterService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 平台分享海报Service业务层处理
 *
 * @author yzg
 * @date 2025-01-03
 */
@RequiredArgsConstructor
@Service
public class PlatformSharePosterServiceImpl implements IPlatformSharePosterService {

    private final PlatformSharePosterMapper baseMapper;

    /**
     * 查询平台分享海报
     */
    @Override
    public PlatformSharePosterVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询平台分享海报列表
     */
    @Override
    public TableDataInfo<PlatformSharePosterVo> queryPageList(PlatformSharePosterBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformSharePoster> lqw = buildQueryWrapper(bo);
        Page<PlatformSharePosterVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台分享海报列表
     */
    @Override
    public List<PlatformSharePosterVo> queryList(PlatformSharePosterBo bo) {
        LambdaQueryWrapper<PlatformSharePoster> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformSharePoster> buildQueryWrapper(PlatformSharePosterBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformSharePoster> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformSharePoster::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), PlatformSharePoster::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增平台分享海报
     */
    @Override
    public Boolean insertByBo(PlatformSharePosterBo bo) {
        PlatformSharePoster add = BeanUtil.toBean(bo, PlatformSharePoster.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改平台分享海报
     */
    @Override
    public Boolean updateByBo(PlatformSharePosterBo bo) {
        PlatformSharePoster update = BeanUtil.toBean(bo, PlatformSharePoster.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformSharePoster entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除平台分享海报
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
