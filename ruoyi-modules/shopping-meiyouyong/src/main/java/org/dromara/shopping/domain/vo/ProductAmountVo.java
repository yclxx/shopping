package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品价格配置视图对象
 *
 * @author yzg
 * @date 2023-07-24
 */
@Data
@ExcelIgnoreUnannotated
public class ProductAmountVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long amountId;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    @ExcelProperty(value = "发放金额，", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "票=券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量")
    private BigDecimal externalProductSendValue;

    /**
     * 中奖概率
     */
    @ExcelProperty(value = "中奖概率")
    private BigDecimal drawProbability;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private String status;


}
