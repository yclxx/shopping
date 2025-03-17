package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券视图对象
 *
 * @author yzg
 * @date 2023-10-16
 */
@Data
@ExcelIgnoreUnannotated
public class CouponVo {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券ID
     */
    @ExcelProperty(value = "优惠券ID")
    private Long couponId;

    /**
     * 优惠券名称
     */
    @ExcelProperty(value = "优惠券名称")
    private String couponName;

    /**
     * 优惠券兑换码
     */
    @ExcelProperty(value = "优惠券兑换码")
    private String redeemCode;

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
    @ExcelProperty(value = "优惠券可使用起始日期")
    private Date periodOfStart;

    /**
     * 使用有效截止日期
     */
    @ExcelProperty(value = "使用有效截止日期")
    private Date periodOfValidity;

    /**
     * 使用状态
     */
    @ExcelProperty(value = "使用状态")
    private String useStatus;

    /**
     * 使用日期
     */
    @ExcelProperty(value = "使用日期")
    private Date useTime;

    /**
     * 使用订单编号，对应t_order中number，若是购物车合并购买记录大订单的订单号
     */
    @ExcelProperty(value = "使用订单号")
    private String number;

    /**
     * 优惠券创建时间
     */
    @ExcelProperty(value = "优惠券创建时间")
    private Date genTime;

    /**
     * 优惠券描述
     */
    @ExcelProperty(value = "优惠券描述")
    private String couponDescription;

    /**
     * 用户添加时间
     */
    @ExcelProperty(value = "用户添加时间")
    private Date userAddTime;

    /**
     * 批次号
     */
    @ExcelProperty(value = "批次号")
    private String actionNo;

    /**
     * 优惠券图片
     */
    @ExcelProperty(value = "优惠券图片")
    private String couponImage;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 可兑换起始日期
     */
    @ExcelProperty(value = "可兑换起始日期")
    private Date conversionStartDate;

    /**
     * 可兑换截止日期
     */
    @ExcelProperty(value = "可兑换截止日期")
    private Date conversionEndDate;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    private List<ProductVo> productVoList;

    /**
     * 是否自动支付
     */
    private String autoPay;

    /**
     * 短网址
     */
    @ExcelProperty(value = "短网址")
    private String shortUrl;
}
