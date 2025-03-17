package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 有赞订单视图对象
 *
 * @author yzg
 * @date 2023-10-28
 */
@Data
@ExcelIgnoreUnannotated
public class OrderYzVo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private String number;

    /**
     * 订单状态
     */
    @ExcelProperty(value = "订单状态")
    private String status;

    /**
     * 订单创建时间
     */
    @ExcelProperty(value = "订单创建时间")
    private String createOrderTime;

    /**
     * 订单付款时间
     */
    @ExcelProperty(value = "订单付款时间")
    private String payOrderTime;

    /**
     * 交易成功时间
     */
    @ExcelProperty(value = "交易成功时间")
    private String successOrderTime;

    /**
     * 付款方式
     */
    @ExcelProperty(value = "付款方式")
    private String payType;

    /**
     * 外部支付流水号
     */
    @ExcelProperty(value = "外部支付流水号")
    private String payNumberWb;

    /**
     * 支付流水号
     */
    @ExcelProperty(value = "支付流水号")
    private String payNumber;

    /**
     * 订单总金额
     */
    @ExcelProperty(value = "订单总金额")
    private BigDecimal totalAmount;

    /**
     * 订单优惠金额
     */
    @ExcelProperty(value = "订单优惠金额")
    private BigDecimal reducedPrice;

    /**
     * 需支付金额（订单总金额-优惠金额）
     */
    @ExcelProperty(value = "需支付金额", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "订=单总金额-优惠金额")
    private BigDecimal wantAmount;

    /**
     * 实际支付金额
     */
    @ExcelProperty(value = "实际支付金额")
    private BigDecimal outAmount;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 用户手机号
     */
    @ExcelProperty(value = "用户手机号")
    private String account;

    /**
     * 订单退款状态
     */
    @ExcelProperty(value = "订单退款状态")
    private String refundStatus;

    /**
     * 订单退款金额
     */
    @ExcelProperty(value = "订单退款金额")
    private BigDecimal refundAmount;

    private Long orderNumber;

    /**
     * 用户手机号
     */
    private String mobile;
}
