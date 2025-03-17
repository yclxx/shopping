package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 巡检商户号临时视图对象
 *
 * @author yzg
 * @date 2024-03-10
 */
@Data
@ExcelIgnoreUnannotated
public class ShopTourLsMerchantVo {

    private static final long serialVersionUID = 1L;

    /**
     * 巡检商户号临时表ID
     */
    @ExcelProperty(value = "巡检商户号临时表ID")
    private Long tourMerchantLsId;

    /**
     * 巡检ID
     */
    @ExcelProperty(value = "巡检ID")
    private Long tourId;

    /**
     * 巡检记录ID
     */
    @ExcelProperty(value = "巡检记录ID")
    private Long tourLogId;

    /**
     * 巡检人员ID
     */
    @ExcelProperty(value = "巡检人员ID")
    private Long verifierId;

    /**
     * 门店ID
     */
    @ExcelProperty(value = "门店ID")
    private Long shopId;

    /**
     * 商户号
     */
    @ExcelProperty(value = "商户号")
    private String merchantNo;

    /**
     * 商户号类型  0-微信  1-云闪付  2-支付宝
     */
    @ExcelProperty(value = "商户号类型  0-微信  1-云闪付  2-支付宝", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_merchant_type")
    private String merchantType;

    /**
     * 收款方式  1-住扫  2被扫
     */
    @ExcelProperty(value = "收款方式  1-住扫  2被扫")
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

    /**
     * 商编截图
     */
    @ExcelProperty(value = "商编截图")
    private String merchantImg;

    /**
     * 是否邮储商编  0-是  1-否
     */
    @ExcelProperty(value = "是否邮储商编  0-是  1-否", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_is_yc_merchant")
    private String ycMerchant;

    /**
     * 是否修改  0-是  1-不是
     */
    @ExcelProperty(value = "是否修改  0-是  1-不是", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_is_update")
    private String isUpdate;

    private String oldMerchantNo;

    private String shopName;

}
