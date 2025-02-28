package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.PlatformGroupProblem;
import org.dromara.shopping.domain.bo.PlatformGroupProblemBo;
import org.dromara.shopping.domain.vo.PlatformGroupProblemVo;
import org.dromara.shopping.mapper.PlatformGroupProblemMapper;
import org.dromara.shopping.service.IPlatformGroupProblemService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户入群问题反馈Service业务层处理
 *
 * @author yzg
 * @date 2024-02-22
 */
@RequiredArgsConstructor
@Service
public class PlatformGroupProblemServiceImpl implements IPlatformGroupProblemService {

    private final PlatformGroupProblemMapper baseMapper;

    /**
     * 查询用户入群问题反馈
     */
    @Override
    public PlatformGroupProblemVo queryById(Long id){
        return baseMapper.selectVoById(id);
    }

    /**
     * 查询用户入群问题反馈列表
     */
    @Override
    public TableDataInfo<PlatformGroupProblemVo> queryPageList(PlatformGroupProblemBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<PlatformGroupProblem> lqw = buildQueryWrapper(bo);
        Page<PlatformGroupProblemVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户入群问题反馈列表
     */
    @Override
    public List<PlatformGroupProblemVo> queryList(PlatformGroupProblemBo bo) {
        LambdaQueryWrapper<PlatformGroupProblem> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<PlatformGroupProblem> buildQueryWrapper(PlatformGroupProblemBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<PlatformGroupProblem> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getPlatformKey() != null, PlatformGroupProblem::getPlatformKey, bo.getPlatformKey());
        lqw.eq(bo.getUserId() != null, PlatformGroupProblem::getUserId, bo.getUserId());
        lqw.eq(StringUtils.isNotBlank(bo.getContent()), PlatformGroupProblem::getContent, bo.getContent());
        return lqw;
    }

    /**
     * 新增用户入群问题反馈
     */
    @Override
    public Boolean insertByBo(PlatformGroupProblemBo bo) {
        PlatformGroupProblem add = BeanUtil.toBean(bo, PlatformGroupProblem.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setId(add.getId());
        }
        return flag;
    }

    /**
     * 修改用户入群问题反馈
     */
    @Override
    public Boolean updateByBo(PlatformGroupProblemBo bo) {
        PlatformGroupProblem update = BeanUtil.toBean(bo, PlatformGroupProblem.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(PlatformGroupProblem entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户入群问题反馈
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
