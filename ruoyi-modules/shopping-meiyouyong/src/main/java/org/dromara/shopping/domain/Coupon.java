package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券对象 t_coupon
 *
 * @author yzg
 * @date 2023-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_coupon")
public class Coupon extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券ID
     */
    @TableId(value = "coupon_id")
    private Long couponId;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠券兑换码
     */
    private String redeemCode;
    /**
     * 优惠金额
     */
    private BigDecimal couponAmount;
    /**
     * 最低消费金额
     */
    private BigDecimal minAmount;
    /**
     * 优惠券类型
     */
    private String couponType;
    /**
     * 优惠券可使用起始日期
     */
    private Date periodOfStart;
    /**
     * 使用有效截止日期
     */
    private Date periodOfValidity;
    /**
     * 使用状态
     */
    private String useStatus;
    /**
     * 使用日期
     */
    private Date useTime;
    /**
     * 大订单的订单号
     */
    private String number;
    /**
     * 优惠券创建时间
     */
    private Date genTime;
    /**
     * 优惠券描述
     */
    private String couponDescription;
    /**
     * 用户添加时间
     */
    private Date userAddTime;
    /**
     * 批次号
     */
    private String actionNo;
    /**
     * 优惠券图片
     */
    private String couponImage;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 可兑换起始日期
     */
    private Date conversionStartDate;
    /**
     * 可兑换截止日期
     */
    private Date conversionEndDate;
    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 是否自动支付
     */
    private String autoPay;

    /**
     * 短网址
     */
    private String shortUrl;
}
