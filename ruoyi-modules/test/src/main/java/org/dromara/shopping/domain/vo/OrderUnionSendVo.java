package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;


/**
 * 银联分销订单卡券视图对象
 *
 * @author yzg
 * @date 2023-08-08
 */
@Data
@ExcelIgnoreUnannotated
public class OrderUnionSendVo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 商品订单号
     */
    @ExcelProperty(value = "商品订单号")
    private String prodTn;

    /**
     * 卡券类型 0-仅券码;1-券码+券密;2-短链;3-直充
     */
    @ExcelProperty(value = "卡券类型 0-仅券码;1-券码+券密;2-短链;3-直充")
    private String prodTp;

    /**
     * 券码流水号
     */
    @ExcelProperty(value = "券码流水号")
    private String bondSerlNo;

    /**
     * 券码
     */
    @ExcelProperty(value = "券码")
    private String bondNo;

    /**
     * 券密
     */
    @ExcelProperty(value = "券密")
    private String bondEncNo;

    /**
     * 生效时间
     */
    @ExcelProperty(value = "生效时间")
    private Date effectDtTm;

    /**
     * 过期时间
     */
    @ExcelProperty(value = "过期时间")
    private Date exprDtTm;

    /**
     * 购买须知
     */
    @ExcelProperty(value = "购买须知")
    private String cusIstr;

    /**
     * 退券订单号
     */
    @ExcelProperty(value = "券状态")
    private String rfdTn;

    /**
     * 券状态
     */
    @ExcelProperty(value = "券状态")
    private String bondSt;


}
