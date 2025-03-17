package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品调价视图对象
 *
 * @author yzg
 * @date 2024-05-29
 */
@Data
@ExcelIgnoreUnannotated
public class ProductAdjustPriceVo {

    private static final long serialVersionUID = 1L;

    /**
     * 调价id
     */
    @ExcelProperty(value = "调价id")
    private Long id;

    /**
     * 商品id
     */
    @ExcelProperty(value = "商品id")
    private Long productId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 原售价  元
     */
    @ExcelProperty(value = "原售价  元")
    private BigDecimal sellAmount;

    /**
     * 调价比例
     */
    @ExcelProperty(value = "调价比例")
    private BigDecimal priceRatio;

    /**
     * 调整价格（服务费）
     */
    @ExcelProperty(value = "调整价格", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "服=务费")
    private BigDecimal adjustPrice;

    private String adjustBatch;


}
