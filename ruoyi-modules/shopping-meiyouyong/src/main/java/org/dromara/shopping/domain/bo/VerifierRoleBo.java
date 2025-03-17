package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 角色信息业务对象
 *
 * @author yzg
 * @date 2024-11-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class VerifierRoleBo extends BaseEntity {

    /**
     * 角色ID
     */
    @NotNull(message = "角色ID不能为空", groups = {EditGroup.class})
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String roleName;

    /**
     * 角色权限字符串
     */
    @NotBlank(message = "角色权限字符串不能为空", groups = {AddGroup.class, EditGroup.class})
    private String roleKey;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long roleSort;

    /**
     * 数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）
     */
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示
     */
    private Boolean menuCheckStrictly;

    /**
     * 部门树选择项是否关联显示
     */
    private Boolean deptCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    @NotBlank(message = "角色状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单集合
     */
    private Long[] menuIds;
}
