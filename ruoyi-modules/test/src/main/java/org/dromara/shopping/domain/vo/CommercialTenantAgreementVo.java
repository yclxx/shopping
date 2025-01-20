package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户合同视图对象
 *
 * @author yzg
 * @date 2024-09-10
 */
@Data
@ExcelIgnoreUnannotated
public class CommercialTenantAgreementVo {

    private static final long serialVersionUID = 1L;

    /**
     * 采购商合同ID
     */
    @ExcelProperty(value = "采购商合同ID")
    private Long agreementId;

    /**
     * 合同名称
     */
    @ExcelProperty(value = "合同名称")
    private String agreementName;

    /**
     * 合同时间
     */
    @ExcelProperty(value = "合同时间")
    private Date agreementTime;

    /**
     * 合同地址
     */
    @ExcelProperty(value = "合同地址")
    private String agreementUrl;

    /**
     * 金额（单位：元）
     */
    @ExcelProperty(value = "金额", converter = ExcelDictConvert.class)
    private BigDecimal amount;

    /**
     * 服务费率  百分比
     */
    @ExcelProperty(value = "服务费率  百分比")
    private String serviceRate;

    /**
     * 签约日期
     */
    @ExcelProperty(value = "签约日期")
    private Date contractDate;

    /**
     * 签约开始日期
     */
    @ExcelProperty(value = "签约开始日期")
    private Date contractDateStart;

    /**
     * 签约结束日期
     */
    @ExcelProperty(value = "签约结束日期")
    private Date contractDateEnd;

    /**
     * 商户id
     */
    @ExcelProperty(value = "商户id")
    private Long commercialTenantId;


}
