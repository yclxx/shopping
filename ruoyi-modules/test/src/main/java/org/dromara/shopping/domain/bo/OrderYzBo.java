package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 有赞订单业务对象
 *
 * @author yzg
 * @date 2023-10-28
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderYzBo extends BaseEntity {

    /**
     * 订单号
     */
    @NotBlank(message = "订单号不能为空", groups = { EditGroup.class })
    private String number;

    /**
     * 订单状态
     */
    @NotBlank(message = "订单状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 订单创建时间
     */
    @NotBlank(message = "订单创建时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String createOrderTime;

    /**
     * 订单付款时间
     */
    @NotBlank(message = "订单付款时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payOrderTime;

    /**
     * 交易成功时间
     */
    @NotBlank(message = "交易成功时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String successOrderTime;

    /**
     * 付款方式
     */
    @NotBlank(message = "付款方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payType;

    /**
     * 外部支付流水号
     */
    @NotBlank(message = "外部支付流水号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payNumberWb;

    /**
     * 支付流水号
     */
    @NotBlank(message = "支付流水号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String payNumber;

    /**
     * 订单总金额
     */
    @NotNull(message = "订单总金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal totalAmount;

    /**
     * 订单优惠金额
     */
    @NotNull(message = "订单优惠金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal reducedPrice;

    /**
     * 需支付金额（订单总金额-优惠金额）
     */
    @NotNull(message = "需支付金额（订单总金额-优惠金额）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal wantAmount;

    /**
     * 实际支付金额
     */
    @NotNull(message = "实际支付金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal outAmount;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 用户手机号
     */
    @NotBlank(message = "用户手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String account;

    /**
     * 订单退款状态
     */
    @NotBlank(message = "订单退款状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String refundStatus;

    /**
     * 订单退款金额
     */
    @NotNull(message = "订单退款金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal refundAmount;

    private Long orderNumber;

    /**
     * 用户手机号
     */
    private String mobile;
}
