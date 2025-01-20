package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商户账户明细视图对象
 *
 * @author yzg
 * @date 2024-09-13
 */
@Data
@ExcelIgnoreUnannotated
public class CommercialTenantAccountDetailVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 商户ID
     */
    @ExcelProperty(value = "商户ID")
    private Long commercialTenantId;

    /**
     * 商户名称
     */
    private String commercialTenantName;

    /**
     * 交易金额 单位:元，加钱正数，扣钱负数
     */
    @ExcelProperty(value = "交易金额 单位:元，加钱正数，扣钱负数")
    private BigDecimal amount;

    /**
     * 交易类型
     */
    @ExcelProperty(value = "交易类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_commercial_tenant_account_type")
    private String detailType;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_commercial_tenant_account_status")
    private String status;

    /**
     * 提现流水号
     */
    @ExcelProperty(value = "提现流水号")
    private String pushNumber;

    /**
     * 提现失败原因
     */
    @ExcelProperty(value = "提现失败原因")
    private String failReason;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
