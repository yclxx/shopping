package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 返还记录业务对象
 *
 * @author yzg
 * @date 2025-01-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RefundCouponLogBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 大订单号
     */
    @NotNull(message = "大订单号不能为空", groups = { AddGroup.class, EditGroup.class })
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
