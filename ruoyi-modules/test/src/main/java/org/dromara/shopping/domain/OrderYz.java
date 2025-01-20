package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 有赞订单对象 t_order_yz
 *
 * @author yzg
 * @date 2023-10-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_yz")
public class OrderYz extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 订单号
     */
    @TableId(value = "number")
    private String number;
    /**
     * 订单状态
     */
    private String status;
    /**
     * 订单创建时间
     */
    private String createOrderTime;
    /**
     * 订单付款时间
     */
    private String payOrderTime;
    /**
     * 交易成功时间
     */
    private String successOrderTime;
    /**
     * 付款方式
     */
    private String payType;
    /**
     * 外部支付流水号
     */
    private String payNumberWb;
    /**
     * 支付流水号
     */
    private String payNumber;
    /**
     * 订单总金额
     */
    private BigDecimal totalAmount;
    /**
     * 订单优惠金额
     */
    private BigDecimal reducedPrice;
    /**
     * 需支付金额（订单总金额-优惠金额）
     */
    private BigDecimal wantAmount;
    /**
     * 实际支付金额
     */
    private BigDecimal outAmount;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 用户手机号
     */
    private String account;
    /**
     * 用户手机号
     */
    private String mobile;
    /**
     * 订单退款状态
     */
    private String refundStatus;
    /**
     * 订单退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    private Long orderNumber;
}
