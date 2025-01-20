package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 银行视图对象
 *
 * @author yzg
 * @date 2024-04-03
 */
@Data
@ExcelIgnoreUnannotated
public class BankVo {

    private static final long serialVersionUID = 1L;

    /**
     * 银行ID
     */
    @ExcelProperty(value = "银行ID")
    private Long bankId;

    /**
     * 银行名称
     */
    @ExcelProperty(value = "银行名称")
    private String bankName;

    /**
     * 银行logo
     */
    @ExcelProperty(value = "银行logo")
    private String bankLogo;

    /**
     * 简称
     */
    @ExcelProperty(value = "简称")
    private String bankShortName;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 英文简称
     */
    private String englishAbbreviation;
}
