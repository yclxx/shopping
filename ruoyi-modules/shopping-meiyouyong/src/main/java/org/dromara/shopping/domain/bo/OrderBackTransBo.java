package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款订单业务对象
 *
 * @author yzg
 * @date 2023-04-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderBackTransBo extends BaseEntity {

    /**
     * 退货订单编号
     */
    private String thNumber;

    /**
     * 订单号
     */
    private Long number;

    /**
     * 退款类型：1-金额，2-积点
     */
    private String pickupMethod;

    /**
     * 交易金额（就是退款金额）单位为元
     */
    @NotNull(message = "交易金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal refund;

    /**
     * 第三方退款订单号（如微信退款订单号）
     */
    private String refundId;

    /**
     * 退款渠道,ORIGINAL：原路退款；BALANCE：退回到余额；OTHER_BALANCE：原账户异常退到其他余额账户；OTHER_BANKCARD：原银行卡异常退到其他银行卡
     */
    private String channel;

    /**
     * 取当前退款单的退款入账方，有以下几种情况：1）退回银行卡：{银行名称}{卡类型}{卡尾号}2）退回支付用户零钱:支付用户零钱3）退还商户:商户基本账户商户结算银行账户4）退回支付用户零钱通:支付用户零钱通
     */
    private String userReceivedAccount;

    /**
     * 订单发送时间
     */
    private String txnTime;

    /**
     * 交易传输时间
     */
    private String traceTime;

    /**
     * 退货交易的交易流水号，供查询用
     */
    private String queryId;

    /**
     * 原始消费交易的queryId
     */
    private String origQryId;

    /**
     * 系统跟踪号
     */
    private String traceNo;

    /**
     * 退款成功时间
     */
    private Date successTime;

    /**
     * 订单状态 0-退款中 1-退款失败 2-退款成功
     */
    private String orderBackTransState;

    /**
     * 退款原因
     */
    private String refundReason;

}
