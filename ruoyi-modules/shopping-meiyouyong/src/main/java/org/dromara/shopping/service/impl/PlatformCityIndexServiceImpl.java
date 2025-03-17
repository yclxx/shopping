package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformCityIndex;
import org.dromara.shopping.domain.bo.PlatformCityIndexBo;
import org.dromara.shopping.domain.vo.PlatformCityIndexVo;
import org.dromara.shopping.mapper.PlatformCityIndexMapper;
import org.dromara.shopping.service.IPlatformCityIndexService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 自定义首页Service业务层处理
 *
 * @author yzg
 * @date 2023-08-07
 */
@RequiredArgsConstructor
@Service
public class PlatformCityIndexServiceImpl implements IPlatformCityIndexService {

    private final PlatformCityIndexMapper baseMapper;

    /**
     * 查询自定义首页
     */
    @Override
    public PlatformCityIndexVo queryById(Long id) {
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询自定义首页列表
     */
    @Override
    public TableDataInfo<PlatformCityIndexVo> queryPageList(PlatformCityIndexBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformCityIndex> lqw = buildQueryWrapper(bo);
        Page<PlatformCityIndexVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询自定义首页列表
     */
    @Override
    public List<PlatformCityIndexVo> queryList(PlatformCityIndexBo bo) {
        LambdaQueryWrapper<PlatformCityIndex> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformCityIndex> buildQueryWrapper(PlatformCityIndexBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformCityIndex> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getAdcode()), PlatformCityIndex::getAdcode, bo.getAdcode());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), PlatformCityIndex::getStatus, bo.getStatus());
        lqw.eq(StringUtils.isNotBlank(bo.getToType()), PlatformCityIndex::getToType, bo.getToType());
        lqw.eq(bo.getPlatformKey() != null, PlatformCityIndex::getPlatformKey, bo.getPlatformKey());
        return lqw;
    }

    /**
     * 新增自定义首页
     */
    @Override
    public Boolean insertByBo(PlatformCityIndexBo bo) {
        PlatformCityIndex add = BeanUtil.toBean(bo, PlatformCityIndex.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改自定义首页
     */
    @Override
    public Boolean updateByBo(PlatformCityIndexBo bo) {
        PlatformCityIndex update = BeanUtil.toBean(bo, PlatformCityIndex.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformCityIndex entity) {
    }

    /**
     * 批量删除自定义首页
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
