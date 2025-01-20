package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 内容分销内容方退券订单视图对象
 *
 * @author yzg
 * @date 2023-09-23
 */
@Data
@ExcelIgnoreUnannotated
public class UnionPayContentRefundOrderVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 银联分销内容方AppID
     */
    @ExcelProperty(value = "银联分销内容方AppID")
    private String unionPayAppId;

    /**
     * 银联退券订单号
     */
    @ExcelProperty(value = "银联退券订单号")
    private String unionPayOrderId;

    /**
     * 银联退券商品编号
     */
    @ExcelProperty(value = "银联退券商品编号")
    private String unionPayProdId;

    /**
     * 银联退券交易时间
     */
    @ExcelProperty(value = "银联退券交易时间")
    private String unionPayTxnTime;

    /**
     * 银联退券状态：00-退券成功，20-退券中，10-退券失败
     */
    @ExcelProperty(value = "银联退券状态：00-退券成功，20-退券中，10-退券失败", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_union_pay_result_status")
    private String unionPayResultStatus;

    /**
     * 银联退券券码
     */
    @ExcelProperty(value = "银联退券券码")
    private String unionPayBondNo;


}
