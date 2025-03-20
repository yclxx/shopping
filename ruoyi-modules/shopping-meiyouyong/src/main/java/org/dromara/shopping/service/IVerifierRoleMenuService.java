package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.VerifierRoleMenuBo;
import org.dromara.shopping.domain.vo.VerifierRoleMenuVo;

import java.util.Collection;
import java.util.List;

/**
 * 角色和菜单关联Service接口
 *
 * @author yzg
 * @date 2024-11-26
 */
public interface IVerifierRoleMenuService {

    /**
     * 查询角色和菜单关联
     */
    VerifierRoleMenuVo queryById(Long roleId);

    /**
     * 查询角色和菜单关联列表
     */
    TableDataInfo<VerifierRoleMenuVo> queryPageList(VerifierRoleMenuBo bo, PageQuery pageQuery);

    /**
     * 查询角色和菜单关联列表
     */
    List<VerifierRoleMenuVo> queryList(VerifierRoleMenuBo bo);

    /**
     * 修改角色和菜单关联
     */
    Boolean insertByBo(VerifierRoleMenuBo bo);

    /**
     * 修改角色和菜单关联
     */
    Boolean updateByBo(VerifierRoleMenuBo bo);

    /**
     * 校验并批量删除角色和菜单关联信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
