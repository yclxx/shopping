package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformQa;
import org.dromara.shopping.domain.bo.PlatformQaBo;
import org.dromara.shopping.domain.vo.PlatformQaVo;
import org.dromara.shopping.mapper.PlatformQaMapper;
import org.dromara.shopping.service.IPlatformQaService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 平台常见问题Service业务层处理
 *
 * @author yzg
 * @date 2025-04-02
 */
@RequiredArgsConstructor
@Service
public class PlatformQaServiceImpl implements IPlatformQaService {

    private final PlatformQaMapper baseMapper;

    /**
     * 查询平台常见问题
     */
    @Override
    public PlatformQaVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询平台常见问题列表
     */
    @Override
    public TableDataInfo<PlatformQaVo> queryPageList(PlatformQaBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformQa> lqw = buildQueryWrapper(bo);
        Page<PlatformQaVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询平台常见问题列表
     */
    @Override
    public List<PlatformQaVo> queryList(PlatformQaBo bo) {
        LambdaQueryWrapper<PlatformQa> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformQa> buildQueryWrapper(PlatformQaBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformQa> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformQa::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getQaId() != null, PlatformQa::getQaId, bo.getQaId());
        lqw.eq(bo.getSort() != null, PlatformQa::getSort, bo.getSort());
        return lqw;
    }

    /**
     * 新增平台常见问题
     */
    @Override
    public Boolean insertByBo(PlatformQaBo bo) {
        PlatformQa add = BeanUtil.toBean(bo, PlatformQa.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改平台常见问题
     */
    @Override
    public Boolean updateByBo(PlatformQaBo bo) {
        PlatformQa update = BeanUtil.toBean(bo, PlatformQa.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformQa entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除平台常见问题
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
