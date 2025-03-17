package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券业务对象
 *
 * @author yzg
 * @date 2023-10-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CouponBo extends BaseEntity {

    /**
     * 优惠券ID
     */
    @NotNull(message = "优惠券ID不能为空", groups = { EditGroup.class })
    private Long couponId;

    /**
     * 优惠券名称
     */
    @NotBlank(message = "优惠券名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String couponName;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 优惠券兑换码
     */
    private String redeemCode;

    /**
     * 优惠金额
     */
    //@NotNull(message = "优惠金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal couponAmount;

    /**
     * 最低消费金额
     */
    private BigDecimal minAmount;

    /**
     * 优惠券类型
     */
    @NotBlank(message = "优惠券类型不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotBlank(message = "使用状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String useStatus;

    /**
     * 使用日期
     */
    private Date useTime;

    /**
     * 使用订单编号，对应t_order中number，若是购物车合并购买记录大订单的订单号
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
     * 商品id(购物车进入可能为多个商品id)
     */
    private List<Long> productIds;

    /**
     * 是否自动支付
     */
    private String autoPay;

    /**
     * 短网址
     */
    private String shortUrl;
}
