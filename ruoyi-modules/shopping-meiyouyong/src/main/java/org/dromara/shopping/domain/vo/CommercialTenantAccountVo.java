package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商户账户视图对象
 *
 * @author yzg
 * @date 2024-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class CommercialTenantAccountVo {

    private static final long serialVersionUID = 1L;

    /**
     * 商户ID
     */
    @ExcelProperty(value = "商户ID")
    private Long commercialTenantId;

    /**
     * 可提金额 单位:元
     */
    @ExcelProperty(value = "可提金额 单位:元")
    private BigDecimal balance;

    /**
     * 冻结金额 单位:元
     */
    @ExcelProperty(value = "冻结金额 单位:元")
    private BigDecimal frozenBalance;

    /**
     * 已提金额 单位:元
     */
    @ExcelProperty(value = "已提金额 单位:元")
    private BigDecimal withdrawBalance;

    /**
     * 总金额 单位:元(可提金额+冻结金额+已提金额)
     */
    @ExcelProperty(value = "总金额 单位:元(可提金额+冻结金额+已提金额)")
    private BigDecimal totalBalance;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    private String commercialTenantName;


}
