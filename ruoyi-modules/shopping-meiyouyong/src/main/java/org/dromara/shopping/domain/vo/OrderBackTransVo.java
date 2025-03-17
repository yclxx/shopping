package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 退款订单视图对象
 *
 * @author yzg
 * @date 2023-04-03
 */
@Data
@ExcelIgnoreUnannotated
public class OrderBackTransVo {

    private static final long serialVersionUID = 1L;

    /**
     * 退货订单编号
     */
    @ExcelProperty(value = "退货订单编号")
    private String thNumber;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 退款类型：1-金额，2-积点
     */
    @ExcelProperty(value = "退款类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_pickup_method")
    private String pickupMethod;

    /**
     * 交易金额（就是退款金额）单位为元
     */
    @ExcelProperty(value = "交易金额", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "就=是退款金额")
    private BigDecimal refund;

    /**
     * 第三方退款订单号（如微信退款订单号）
     */
    @ExcelProperty(value = "第三方退款订单号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "如=微信退款订单号")
    private String refundId;

    /**
     * 退款渠道,ORIGINAL：原路退款；BALANCE：退回到余额；OTHER_BALANCE：原账户异常退到其他余额账户；OTHER_BANKCARD：原银行卡异常退到其他银行卡
     */
    @ExcelProperty(value = "退款渠道", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_back_trans_channel")
    private String channel;

    /**
     * 取当前退款单的退款入账方，有以下几种情况：1）退回银行卡：{银行名称}{卡类型}{卡尾号}2）退回支付用户零钱:支付用户零钱3）退还商户:商户基本账户商户结算银行账户4）退回支付用户零钱通:支付用户零钱通
     */
    @ExcelProperty(value = "退还账号")
    private String userReceivedAccount;

    /**
     * 订单发送时间
     */
    @ExcelProperty(value = "订单发送时间")
    private String txnTime;

    /**
     * 交易传输时间
     */
    @ExcelProperty(value = "交易传输时间")
    private String traceTime;

    /**
     * 退货交易的交易流水号，供查询用
     */
    @ExcelProperty(value = "退货交易的交易流水号")
    private String queryId;

    /**
     * 原始消费交易的queryId
     */
    @ExcelProperty(value = "原始消费交易Id")
    private String origQryId;

    /**
     * 系统跟踪号
     */
    @ExcelProperty(value = "系统跟踪号")
    private String traceNo;

    /**
     * 退款成功时间
     */
    @ExcelProperty(value = "退款成功时间")
    private Date successTime;

    /**
     * 订单状态 0-退款中 1-退款失败 2-退款成功
     */
    @ExcelProperty(value = "订单状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_back_trans_state")
    private String orderBackTransState;

    /**
     * 退款原因
     */
    @ExcelProperty(value = "退款原因")
    private String refundReason;

    private Date createTime;
}
