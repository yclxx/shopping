package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.VerifierUserRole;
import org.dromara.shopping.domain.bo.VerifierUserRoleBo;
import org.dromara.shopping.domain.vo.VerifierUserRoleVo;
import org.dromara.shopping.mapper.VerifierUserRoleMapper;
import org.dromara.shopping.service.IVerifierUserRoleService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 用户和角色关联Service业务层处理
 *
 * @author yzg
 * @date 2024-11-26
 */
@RequiredArgsConstructor
@Service
public class VerifierUserRoleServiceImpl implements IVerifierUserRoleService {

    private final VerifierUserRoleMapper baseMapper;

    /**
     * 查询用户和角色关联
     */
    @Override
    public VerifierUserRoleVo queryById(Long userId){
        return baseMapper.selectVoById(userId);
    }

    /**
     * 查询用户和角色关联列表
     */
    @Override
    public TableDataInfo<VerifierUserRoleVo> queryPageList(VerifierUserRoleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<VerifierUserRole> lqw = buildQueryWrapper(bo);
        Page<VerifierUserRoleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询用户和角色关联列表
     */
    @Override
    public List<VerifierUserRoleVo> queryList(VerifierUserRoleBo bo) {
        LambdaQueryWrapper<VerifierUserRole> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<VerifierUserRole> buildQueryWrapper(VerifierUserRoleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<VerifierUserRole> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增用户和角色关联
     */
    @Override
    public Boolean insertByBo(VerifierUserRoleBo bo) {
        VerifierUserRole add = BeanUtil.toBean(bo, VerifierUserRole.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setUserId(add.getUserId());
        }
        return flag;
    }

    /**
     * 修改用户和角色关联
     */
    @Override
    public Boolean updateByBo(VerifierUserRoleBo bo) {
        VerifierUserRole update = BeanUtil.toBean(bo, VerifierUserRole.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(VerifierUserRole entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除用户和角色关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
