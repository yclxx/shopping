package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 平台销售利润视图对象
 *
 * @author yzg
 * @date 2024-09-11
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformSaleProfitVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 月份
     */
    @ExcelProperty(value = "月份")
    private String month;

    /**
     * 销售额
     */
    @ExcelProperty(value = "销售额")
    private BigDecimal salePrice;

    /**
     * 结算额
     */
    @ExcelProperty(value = "结算额")
    private BigDecimal settlementPrice;

    /**
     * 已核销销售额
     */
    @ExcelProperty(value = "已核销销售额")
    private BigDecimal usedSalePrice;

    /**
     * 已核销结算额
     */
    @ExcelProperty(value = "已核销结算额")
    private BigDecimal usedSettlementPrice;

    /**
     * 利润率
     */
    @ExcelProperty(value = "利润率")
    private BigDecimal usedProfit;


}
