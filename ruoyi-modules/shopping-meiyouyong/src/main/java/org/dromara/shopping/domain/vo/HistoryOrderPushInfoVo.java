package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 历史订单取码记录视图对象
 *
 * @author yzg
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
public class HistoryOrderPushInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 取码(充值)订单号
     */
    @ExcelProperty(value = "取码(充值)订单号")
    private String pushNumber;

    /**
     * 供应商订单号
     */
    @ExcelProperty(value = "供应商订单号")
    private String externalOrderNumber;

    /**
     * 取码提交供应商产品编号（供应商提供）如遇面值类的，填面值
     */
    @ExcelProperty(value = "取码提交供应商产品编号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "供=应商提供")
    private String externalProductId;

    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    @ExcelProperty(value = "发放金额，", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "票=券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量")
    private BigDecimal externalProductSendValue;

    /**
     * 订单状态 0-处理中 1-成功 2-失败
     */
    @ExcelProperty(value = "订单状态 0-处理中 1-成功 2-失败")
    private String status;

    /**
     * 交易失败原因
     */
    @ExcelProperty(value = "交易失败原因")
    private String remark;


}
