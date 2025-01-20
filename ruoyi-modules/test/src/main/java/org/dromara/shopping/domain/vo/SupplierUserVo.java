package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 供应商用户关联视图对象
 *
 * @author yzg
 * @date 2024-12-20
 */
@Data
@ExcelIgnoreUnannotated
public class SupplierUserVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 供应商编号
     */
    @ExcelProperty(value = "供应商编号")
    private Long supplierId;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;


}
