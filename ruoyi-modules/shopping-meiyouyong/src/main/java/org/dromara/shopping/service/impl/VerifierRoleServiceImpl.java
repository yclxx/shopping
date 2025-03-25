package org.dromara.shopping.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.VerifierRole;
import org.dromara.shopping.domain.VerifierRoleMenu;
import org.dromara.shopping.domain.bo.VerifierRoleBo;
import org.dromara.shopping.domain.vo.VerifierRoleVo;
import org.dromara.shopping.mapper.VerifierRoleMapper;
import org.dromara.shopping.mapper.VerifierRoleMenuMapper;
import org.dromara.shopping.service.IVerifierRoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * 角色信息Service业务层处理
 *
 * @author yzg
 * @date 2024-11-26
 */
@RequiredArgsConstructor
@Service
public class VerifierRoleServiceImpl implements IVerifierRoleService {

    private final VerifierRoleMapper baseMapper;
    private final VerifierRoleMenuMapper roleMenuMapper;

    /**
     * 查询角色信息
     */
    @Override
    public VerifierRoleVo queryById(Long roleId) {
        return baseMapper.selectVoById(roleId);
    }

    /**
     * 查询角色信息列表
     */
    @Override
    public TableDataInfo<VerifierRoleVo> queryPageList(VerifierRoleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<VerifierRole> lqw = buildQueryWrapper(bo);
        Page<VerifierRoleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询角色信息列表
     */
    @Override
    public List<VerifierRoleVo> queryList(VerifierRoleBo bo) {
        LambdaQueryWrapper<VerifierRole> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<VerifierRole> buildQueryWrapper(VerifierRoleBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<VerifierRole> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getRoleName()), VerifierRole::getRoleName, bo.getRoleName());
        lqw.eq(StringUtils.isNotBlank(bo.getRoleKey()), VerifierRole::getRoleKey, bo.getRoleKey());
        lqw.eq(bo.getRoleSort() != null, VerifierRole::getRoleSort, bo.getRoleSort());
        lqw.eq(StringUtils.isNotBlank(bo.getDataScope()), VerifierRole::getDataScope, bo.getDataScope());
        lqw.eq(bo.getMenuCheckStrictly() != null, VerifierRole::getMenuCheckStrictly, bo.getMenuCheckStrictly());
        lqw.eq(bo.getDeptCheckStrictly() != null, VerifierRole::getDeptCheckStrictly, bo.getDeptCheckStrictly());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), VerifierRole::getStatus, bo.getStatus());
        return lqw;
    }

    /**
     * 新增角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean insertByBo(VerifierRoleBo bo) {
        VerifierRole add = BeanUtil.toBean(bo, VerifierRole.class);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setRoleId(add.getRoleId());
        }
        int i = insertRoleMenu(bo.getRoleId(), bo.getMenuIds());
        return flag && i > 0;
    }

    /**
     * 修改角色信息
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean updateByBo(VerifierRoleBo bo) {
        VerifierRole update = BeanUtil.toBean(bo, VerifierRole.class);
        boolean b = baseMapper.updateById(update) > 0;
        roleMenuMapper.delete(new LambdaQueryWrapper<VerifierRoleMenu>().eq(VerifierRoleMenu::getRoleId, bo.getRoleId()));
        int i = insertRoleMenu(bo.getRoleId(), bo.getMenuIds());
        return b && i > 0;
    }

    /**
     * 新增角色菜单信息
     */
    public int insertRoleMenu(Long roleId, Long[] menuIds) {
        int rows = 1;
        // 新增用户与角色管理
        List<VerifierRoleMenu> list = new ArrayList<>();
        for (Long menuId : menuIds) {
            VerifierRoleMenu rm = new VerifierRoleMenu();
            rm.setRoleId(roleId);
            rm.setMenuId(menuId);
            list.add(rm);
        }
        if (!list.isEmpty()) {
            rows = roleMenuMapper.insertBatch(list) ? list.size() : 0;
        }
        return rows;
    }

    @Override
    public Boolean changeStatus(Long roleId) {
        VerifierRoleVo verifierRoleVo = this.queryById(roleId);
        if (null == verifierRoleVo) {
            return false;
        }
        VerifierRole bo = new VerifierRole();
        bo.setRoleId(verifierRoleVo.getRoleId());
        if (verifierRoleVo.getStatus().equals("0")) {
            bo.setStatus("1");
        } else {
            bo.setStatus("0");
        }
        return baseMapper.updateById(bo) > 0;
    }

    /**
     * 批量删除角色信息
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid) {
        if (isValid) {
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
