package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 优惠券批次视图对象
 *
 * @author yzg
 * @date 2023-10-12
 */
@Data
@ExcelIgnoreUnannotated
public class ActionVo {

    private static final long serialVersionUID = 1L;

    /**
     * 批次ID
     */
    @ExcelProperty(value = "批次ID")
    private Long actionId;

    /**
     * 批次号
     */
    @ExcelProperty(value = "批次号")
    private String actionNo;

    /**
     * 批次描述
     */
    @ExcelProperty(value = "批次描述")
    private String actionNote;

    /**
     * 优惠券名称
     */
    @ExcelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * 优惠金额
     */
    @ExcelProperty(value = "优惠金额")
    private BigDecimal couponAmount;

    /**
     * 最低消费金额
     */
    @ExcelProperty(value = "最低消费金额")
    private BigDecimal minAmount;

    /**
     * 优惠券类型
     */
    @ExcelProperty(value = "优惠券类型")
    private String couponType;

    /**
     * 优惠券可使用起始日期
     */
    @ExcelProperty(value = "使用起始日期")
    private Date periodOfStart;

    /**
     * 使用有效截止日期
     */
    @ExcelProperty(value = "使用有效截止日期")
    private Date periodOfValidity;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 优惠券数量
     */
    @ExcelProperty(value = "优惠券数量")
    private Long couponCount;

    /**
     * 优惠券描述
     */
    @ExcelProperty(value = "优惠券描述")
    private String couponDescription;

    /**
     * 优惠券图片
     */
    @ExcelProperty(value = "优惠券图片")
    private String couponImage;

    /**
     * 可兑换起始日期
     */
    @ExcelProperty(value = "兑换起始日期")
    private Date conversionStartDate;

    /**
     * 可兑换截止日期
     */
    @ExcelProperty(value = "兑换截止日期")
    private Date conversionEndDate;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 是否自动支付
     */
    private String autoPay;

    /**
     * 落地页模板ID
     */
    private Long templateId;
    /**
     * 优惠券失效时间类型 0-指定失效时间，1-增加指定天数
     */
    private String couponExpiryType;
    /**
     * 优惠券失效时间类型为1（填写数字）
     */
    private String couponExpiryDate;
}
