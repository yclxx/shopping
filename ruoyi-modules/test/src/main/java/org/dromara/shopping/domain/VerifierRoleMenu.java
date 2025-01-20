package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 角色和菜单关联对象 t_verifier_role_menu
 *
 * @author yzg
 * @date 2024-11-26
 */
@Data
@TableName("t_verifier_role_menu")
public class VerifierRoleMenu implements Serializable {

    private static final long serialVersionUID=1L;

    /**
     * 角色ID
     */
    @TableId(type = IdType.INPUT)
    private Long roleId;
    /**
     * 菜单ID
     */
    private Long menuId;

}
