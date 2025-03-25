package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.VerifierRoleMenu;
import org.dromara.shopping.domain.bo.VerifierRoleMenuBo;
import org.dromara.shopping.domain.vo.VerifierRoleMenuVo;
import org.dromara.shopping.mapper.VerifierRoleMenuMapper;
import org.dromara.shopping.service.IVerifierRoleMenuService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 角色和菜单关联Service业务层处理
 *
 * @author yzg
 * @date 2024-11-26
 */
@RequiredArgsConstructor
@Service
public class VerifierRoleMenuServiceImpl implements IVerifierRoleMenuService {

    private final VerifierRoleMenuMapper baseMapper;

    /**
     * 查询角色和菜单关联
     */
    @Override
    public VerifierRoleMenuVo queryById(Long roleId){
        return baseMapper.selectVoById(roleId);
    }

    /**
     * 查询角色和菜单关联列表
     */
    @Override
    public TableDataInfo<VerifierRoleMenuVo> queryPageList(VerifierRoleMenuBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<VerifierRoleMenu> lqw = buildQueryWrapper(bo);
        Page<VerifierRoleMenuVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询角色和菜单关联列表
     */
    @Override
    public List<VerifierRoleMenuVo> queryList(VerifierRoleMenuBo bo) {
        LambdaQueryWrapper<VerifierRoleMenu> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<VerifierRoleMenu> buildQueryWrapper(VerifierRoleMenuBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<VerifierRoleMenu> lqw = Wrappers.lambdaQuery();
        return lqw;
    }

    /**
     * 新增角色和菜单关联
     */
    @Override
    public Boolean insertByBo(VerifierRoleMenuBo bo) {
        VerifierRoleMenu add = BeanUtil.toBean(bo, VerifierRoleMenu.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRoleId(add.getRoleId());
        }
        return flag;
    }

    /**
     * 修改角色和菜单关联
     */
    @Override
    public Boolean updateByBo(VerifierRoleMenuBo bo) {
        VerifierRoleMenu update = BeanUtil.toBean(bo, VerifierRoleMenu.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(VerifierRoleMenu entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除角色和菜单关联
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
