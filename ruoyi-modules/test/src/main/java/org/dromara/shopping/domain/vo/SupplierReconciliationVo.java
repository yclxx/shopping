package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商结算视图对象
 *
 * @author yzg
 * @date 2024-12-24
 */
@Data
@ExcelIgnoreUnannotated
public class SupplierReconciliationVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long reconciliationId;

    /**
     * 结算名称
     */
    @ExcelProperty(value = "结算名称")
    private String reconciliationName;

    /**
     * 供应商ID
     */
    @ExcelProperty(value = "供应商ID")
    private Long supplierId;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 截止时间
     */
    @ExcelProperty(value = "截止时间")
    private Date endTime;

    /**
     * 结算金额
     */
    @ExcelProperty(value = "结算金额")
    private BigDecimal amount;

    /**
     * 结算时间
     */
    @ExcelProperty(value = "结算时间")
    private Date settlementTime;

    /**
     * 结算状态
     */
    @ExcelProperty(value = "结算状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_supplier_reconciliation_status")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    private Date createTime;

    private String supplierName;


}
