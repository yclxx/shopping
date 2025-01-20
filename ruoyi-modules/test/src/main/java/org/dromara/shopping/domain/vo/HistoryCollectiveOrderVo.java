package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 历史大订单视图对象
 *
 * @author yzg
 * @date 2023-11-08
 */
@Data
@ExcelIgnoreUnannotated
public class HistoryCollectiveOrderVo {

    private static final long serialVersionUID = 1L;

    /**
     * 大订单号
     */
    @ExcelProperty(value = "大订单号")
    private Long collectiveNumber;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

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
     * 购买数量
     */
    @ExcelProperty(value = "购买数量")
    private Long count;

    /**
     * 优惠券id
     */
    @ExcelProperty(value = "优惠券id")
    private Long couponId;

    /**
     * 支付完成时间
     */
    @ExcelProperty(value = "支付完成时间")
    private Date payTime;

    /**
     * 订单失效时间
     */
    @ExcelProperty(value = "订单失效时间")
    private Date expireDate;

    /**
     * 订单状态 0-待付款 1-支付确认中 2-支付成功 3-已关闭 4-退款处理中,5-退款成功,6-退款失败
     */
    @ExcelProperty(value = "订单状态 0-待付款 1-支付确认中 2-支付成功 3-已关闭 4-退款处理中,5-退款成功,6-退款失败")
    private String status;

    /**
     * 退款状态0-退款中 1-退款成功 2-退款失败 3-部分退款成功
     */
    @ExcelProperty(value = "退款状态0-退款中 1-退款成功 2-退款失败 3-部分退款成功")
    private String cancelStatus;

    /**
     * 已退金额
     */
    @ExcelProperty(value = "已退金额")
    private BigDecimal cancelAmount;

    /**
     * 下单所在城市
     */
    @ExcelProperty(value = "下单所在城市")
    private String orderCityName;

    /**
     * 下单所在城市行政区号
     */
    @ExcelProperty(value = "下单所在城市行政区号")
    private String orderCityCode;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 支付商户号
     */
    @ExcelProperty(value = "支付商户号")
    private Long payMerchant;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    private Long sysDeptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long sysUserId;

    private String isGroup;


}
