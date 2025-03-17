package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 角色和菜单关联视图对象
 *
 * @author yzg
 * @date 2024-11-26
 */
@Data
@ExcelIgnoreUnannotated
public class VerifierRoleMenuVo {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ExcelProperty(value = "角色ID")
    private Long roleId;

    /**
     * 菜单ID
     */
    @ExcelProperty(value = "菜单ID")
    private Long menuId;


}
