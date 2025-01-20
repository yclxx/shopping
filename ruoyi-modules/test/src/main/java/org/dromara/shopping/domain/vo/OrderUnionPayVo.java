package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.io.Serializable;


/**
 * 银联分销订单详情视图对象
 *
 * @author yzg
 * @date 2023-08-08
 */
@Data
@ExcelIgnoreUnannotated
public class OrderUnionPayVo implements Serializable {


    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 交易订单号
     */
    @ExcelProperty(value = "交易订单号")
    private String orderId;

    /**
     * 商品订单号
     */
    @ExcelProperty(value = "商品订单号")
    private String prodTn;

    /**
     * 交易发送时间
     */
    @ExcelProperty(value = "交易发送时间")
    private String txnTime;

    /**
     * 支付金额（分）
     */
    @ExcelProperty(value = "支付金额", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "分=")
    private String usrPayAmt;

    /**
     * 支付订单号（发起支付出现）
     */
    @ExcelProperty(value = "支付订单号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "发起支付出现")
    private String payTn;

    /**
     * 支付发起时间
     */
    @ExcelProperty(value = "支付发起时间")
    private String payTxnTime;

    /**
     * 支付跳转地址
     */
    @ExcelProperty(value = "支付跳转地址")
    private String payUrl;
    /**
     * 清算日期
     */
    @ExcelProperty(value = "清算日期")
    private String settleDt;

    /**
     * 清算金额
     */
    @ExcelProperty(value = "清算金额")
    private String settleAmt;

    /**
     * 清算币种
     */
    @ExcelProperty(value = "清算币种")
    private String settleCurrencyCode;

    /**
     * 清算主键
     */
    @ExcelProperty(value = "清算主键")
    private String settleKey;

}
