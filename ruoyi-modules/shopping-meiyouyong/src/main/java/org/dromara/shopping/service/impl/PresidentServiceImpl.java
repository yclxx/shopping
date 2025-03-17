package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.President;
import org.dromara.shopping.domain.bo.PresidentBo;
import org.dromara.shopping.domain.vo.PresidentVo;
import org.dromara.shopping.mapper.PresidentMapper;
import org.dromara.shopping.service.IPresidentService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 支行长Service业务层处理
 *
 * @author yzg
 * @date 2024-04-28
 */
@RequiredArgsConstructor
@Service
public class PresidentServiceImpl implements IPresidentService {

    private final PresidentMapper baseMapper;

    /**
     * 查询支行长
     */
    @Override
    public PresidentVo queryById(Long presidentId){
        return baseMapper.selectVoById(presidentId);
    }

    /**
     * 查询支行长列表
     */
    @Override
    public TableDataInfo<PresidentVo> queryPageList(PresidentBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<President> lqw = buildQueryWrapper(bo);
        Page<PresidentVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询支行长列表
     */
    @Override
    public List<PresidentVo> queryList(PresidentBo bo) {
        LambdaQueryWrapper<President> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<President> buildQueryWrapper(PresidentBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<President> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), President::getName, bo.getName());
        lqw.eq(StringUtils.isNotBlank(bo.getMobile()), President::getMobile, bo.getMobile());
        lqw.eq(StringUtils.isNotBlank(bo.getOpenId()), President::getOpenId, bo.getOpenId());
        lqw.eq(StringUtils.isNotBlank(bo.getBank()), President::getBank, bo.getBank());
        lqw.eq(StringUtils.isNotBlank(bo.getLinkmanBranch()), President::getLinkmanBranch, bo.getLinkmanBranch());
        lqw.eq(StringUtils.isNotBlank(bo.getLinkmanBranchSecond()), President::getLinkmanBranchSecond, bo.getLinkmanBranchSecond());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), President::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增支行长
     */
    @Override
    public Boolean insertByBo(PresidentBo bo) {
        President add = BeanUtil.toBean(bo, President.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setPresidentId(add.getPresidentId());
        }
        return flag;
    }

    /**
     * 修改支行长
     */
    @Override
    public Boolean updateByBo(PresidentBo bo) {
        President update = BeanUtil.toBean(bo, President.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(President entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除支行长
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
