package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银联分销订单详情对象 t_order_union_pay
 *
 * @author yzg
 * @date 2023-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_union_pay")
public class OrderUnionPay extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableId(value = "number")
    private Long number;
    /**
     * 交易订单号
     */
    private String orderId;
    /**
     * 商品订单号
     */
    private String prodTn;
    /**
     * 交易发送时间
     */
    private String txnTime;
    /**
     * 支付金额（分）
     */
    private String usrPayAmt;
    /**
     * 支付订单号（发起支付出现）
     */
    private String payTn;
    /**
     * 支付发起时间
     */
    private String payTxnTime;
    /**
     * 支付跳转地址
     */
    private String payUrl;
    /**
     * 清算日期
     */
    private String settleDt;
    /**
     * 清算金额
     */
    private String settleAmt;
    /**
     * 清算币种
     */
    private String settleCurrencyCode;
    /**
     * 清算主键
     */
    private String settleKey;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
