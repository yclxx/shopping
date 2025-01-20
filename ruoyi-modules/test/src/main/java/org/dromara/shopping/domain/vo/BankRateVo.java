package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 银行费率视图对象
 *
 * @author yzg
 * @date 2024-05-29
 */
@Data
@ExcelIgnoreUnannotated
public class BankRateVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long bankRateId;

    /**
     * 银行ID
     */
    @ExcelProperty(value = "银行ID")
    private Long bankId;

    /**
     * 费率
     */
    @ExcelProperty(value = "费率")
    private BigDecimal rate;

    /**
     * 期数
     */
    @ExcelProperty(value = "期数")
    private String stagesNumber;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    private String bankName;

    private String bankRateName;


}
