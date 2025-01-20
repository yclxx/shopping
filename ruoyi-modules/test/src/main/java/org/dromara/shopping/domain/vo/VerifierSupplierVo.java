package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 核销员供应商关联视图对象
 *
 * @author yzg
 * @date 2024-03-25
 */
@Data
@ExcelIgnoreUnannotated
public class VerifierSupplierVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 核销员id
     */
    @ExcelProperty(value = "核销员id")
    private Long verifierId;

    /**
     * 供应商id
     */
    @ExcelProperty(value = "供应商id")
    private Long supplierId;

    private String supplierName;
}
