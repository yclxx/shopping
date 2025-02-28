package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformCityChange;
import org.dromara.shopping.domain.bo.PlatformCityChangeBo;
import org.dromara.shopping.domain.vo.PlatformCityChangeVo;
import org.dromara.shopping.mapper.PlatformCityChangeMapper;
import org.dromara.shopping.service.IPlatformCityChangeService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 平台切换Service业务层处理
 *
 * @author yzg
 * @date 2024-03-19
 */
@RequiredArgsConstructor
@Service
public class PlatformCityChangeServiceImpl implements IPlatformCityChangeService {

    private final PlatformCityChangeMapper baseMapper;

    /**
     * 查询平台切换
     */
    @Override
    public PlatformCityChangeVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询平台切换列表
     */
    @Override
    public TableDataInfo<PlatformCityChangeVo> queryPageList(PlatformCityChangeBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformCityChange> lqw = buildQueryWrapper(bo);
        Page<PlatformCityChangeVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台切换列表
     */
    @Override
    public List<PlatformCityChangeVo> queryList(PlatformCityChangeBo bo) {
        LambdaQueryWrapper<PlatformCityChange> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformCityChange> buildQueryWrapper(PlatformCityChangeBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformCityChange> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformCityChange::getPlatformKey, bo.getPlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getAdcode()), PlatformCityChange::getAdcode, bo.getAdcode());
        lqw.eq(bo.getChangePlatformKey() != null, PlatformCityChange::getChangePlatformKey, bo.getChangePlatformKey());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), PlatformCityChange::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增平台切换
     */
    @Override
    public Boolean insertByBo(PlatformCityChangeBo bo) {
        PlatformCityChange add = BeanUtil.toBean(bo, PlatformCityChange.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改平台切换
     */
    @Override
    public Boolean updateByBo(PlatformCityChangeBo bo) {
        PlatformCityChange update = BeanUtil.toBean(bo, PlatformCityChange.class);
        return baseMapper.updateById(update) > 0;
    }


    /**
     * 批量删除平台切换
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
