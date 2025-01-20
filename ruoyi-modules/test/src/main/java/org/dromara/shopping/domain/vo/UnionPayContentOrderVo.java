package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 内容分销内容方订单视图对象
 *
 * @author yzg
 * @date 2023-09-16
 */
@Data
@ExcelIgnoreUnannotated
public class UnionPayContentOrderVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 银联发券内容方AppId
     */
    private String unionPayAppId;

    /**
     * 银联发券订单号
     */
    @ExcelProperty(value = "银联发券订单号")
    private String unionPayOrderId;

    /**
     * 银联发券商品编号
     */
    @ExcelProperty(value = "银联发券商品编号")
    private String unionPayProdId;

    /**
     * 银联发券交易时间
     */
    @ExcelProperty(value = "银联发券交易时间")
    private String unionPayTxnTime;

    /**
     * 银联发券购买数量
     */
    @ExcelProperty(value = "银联发券购买数量")
    private String unionPayPurQty;

    /**
     * 银联发券账号类型：0-手机号;1-qq号;2-微信号;3-其他类 型账号
     */
    @ExcelProperty(value = "银联发券账号类型：0-手机号;1-qq号;2-微信号;3-其他类 型账号")
    private String unionPayProdAstIdTp;

    /**
     * 银联发券账号
     */
    @ExcelProperty(value = "银联发券账号")
    private String unionPayProdAstId;

    /**
     * 银联发券状态
     */
    @ExcelProperty(value = "银联发券状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "union_pay_result_status")
    private String unionPayResultStatus;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;


}
