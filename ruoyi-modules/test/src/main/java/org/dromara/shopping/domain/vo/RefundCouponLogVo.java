package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 返还记录视图对象
 *
 * @author yzg
 * @date 2025-01-15
 */
@Data
@ExcelIgnoreUnannotated
public class RefundCouponLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 大订单号
     */
    @ExcelProperty(value = "大订单号")
    private Long collectiveNumber;

    /**
     * 订单号(补发优惠券时才有值，对应补发优惠券的子订单)
     */
    @ExcelProperty(value = "订单号(补发优惠券时才有值，对应补发优惠券的子订单)")
    private Long number;

    /**
     * 优惠券ID
     */
    @ExcelProperty(value = "优惠券ID")
    private Long couponId;

    /**
     * 优惠券兑换码
     */
    @ExcelProperty(value = "优惠券兑换码")
    private String redeemCode;

    /**
     * 记录类型
     */
    @ExcelProperty(value = "记录类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_coupon_refund_type")
    private String logType;

    /**
     * 处理状态
     */
    @ExcelProperty(value = "处理状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_coupon_refund_status")
    private String status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;


}
