package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单取码记录视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class OrderPushInfoVo {

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
    @ExcelProperty(value = "取码提交供应商产品编号")
    private String externalProductId;

    /**
     * 订单状态 0-处理中 1-成功 2-失败
     */
    @ExcelProperty(value = "订单状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_push_status")
    private String status;

    /**
     * 交易失败原因
     */
    @ExcelProperty(value = "交易失败原因")
    private String remark;

    /**
     * 发放金额
     */
    private BigDecimal externalProductSendValue;

    @ExcelProperty(value = "创建时间")
    private Date createTime;
}
