package org.dromara.shopping.service;

import org.dromara.common.mybatis.core.page.PageQuery;
import org.dromara.common.mybatis.core.page.TableDataInfo;
import org.dromara.shopping.domain.bo.VerifierRoleBo;
import org.dromara.shopping.domain.vo.VerifierRoleVo;

import java.util.Collection;
import java.util.List;

/**
 * 角色信息Service接口
 *
 * @author yzg
 * @date 2024-11-26
 */
public interface IVerifierRoleService {

    /**
     * 查询角色信息
     */
    VerifierRoleVo queryById(Long roleId);

    /**
     * 查询角色信息列表
     */
    TableDataInfo<VerifierRoleVo> queryPageList(VerifierRoleBo bo, PageQuery pageQuery);

    /**
     * 查询角色信息列表
     */
    List<VerifierRoleVo> queryList(VerifierRoleBo bo);

    /**
     * 修改角色信息
     */
    Boolean insertByBo(VerifierRoleBo bo);

    Boolean changeStatus(Long roleId);

    /**
     * 修改角色信息
     */
    Boolean updateByBo(VerifierRoleBo bo);

    /**
     * 校验并批量删除角色信息信息
     */
    Boolean deleteWithValidByIds(Collection<Long> ids, Boolean isValid);
}
