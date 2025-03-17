package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;



/**
 * 用户和角色关联视图对象
 *
 * @author yzg
 * @date 2024-11-26
 */
@Data
@ExcelIgnoreUnannotated
public class VerifierUserRoleVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 角色ID
     */
    @ExcelProperty(value = "角色ID")
    private Long roleId;


}
