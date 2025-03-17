package org.dromara.shopping.domain.bo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class LogisticsImportBo {
    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private String number;
    /**
     * 物流单号
     */
    @ExcelProperty(value = "物流单号")
    private String logistics;
    /**
     * 物流公司
     */
    @ExcelProperty(value = "物流公司")
    private String logisticsCom;
    /**
     * 物流状态 0未发货 1已发货 2已签收 3已拒收
     */
    @ExcelProperty(value = "物流状态(未发货-0,已发货-1,已签收-2,已拒收-3)")
    private String logisticsStatus;
}
