package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 返还记录对象 t_refund_coupon_log
 *
 * @author yzg
 * @date 2025-01-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_refund_coupon_log")
public class RefundCouponLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 大订单号
     */
    private Long collectiveNumber;
    /**
     * 订单号(补发优惠券时才有值，对应补发优惠券的子订单)
     */
    private Long number;
    /**
     * 优惠券ID
     */
    private Long couponId;
    /**
     * 优惠券兑换码
     */
    private String redeemCode;
    /**
     * 记录类型
     */
    private String logType;
    /**
     * 处理状态
     */
    private String status;

}
