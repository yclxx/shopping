package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 活动商户号视图对象
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@ExcelIgnoreUnannotated
public class ActivityMerchantVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 核销人员
     */
    @ExcelProperty(value = "核销人员")
    private Long verifierId;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long activityId;

    /**
     * 商户号
     */
    @ExcelProperty(value = "商户号")
    private String merchantNo;

    /**
     * 商户类型
     */
    @ExcelProperty(value = "商户类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "channel_type")
    private String merchantType;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_show_hide")
    private String status;

    /**
     * 结算方式
     */
    @ExcelProperty(value = "结算方式")
    private String settlementWay;

    /**
     * 结算比例
     */
    @ExcelProperty(value = "结算比例")
    private String settlement;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 收款方式（主扫1，2被扫）
     */
    @ExcelProperty(value = "收款方式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "主=扫1，2被扫")
    private String paymentMethod;

    /**
     * 收单机构
     */
    @ExcelProperty(value = "收单机构")
    private String acquirer;

    /**
     * 终端编号
     */
    @ExcelProperty(value = "终端编号")
    private String terminalNo;


}
