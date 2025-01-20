package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品调价比例视图对象
 *
 * @author yzg
 * @date 2024-05-30
 */
@Data
@ExcelIgnoreUnannotated
public class ProductAdjustRatioVo {

    private static final long serialVersionUID = 1L;

    /**
     * 调价比例id
     */
    @ExcelProperty(value = "调价比例id")
    private Long ratioId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 调价类型  1-平台统一调价  2-分类调价
     */
    @ExcelProperty(value = "调价类型  1-平台统一调价  2-分类调价", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_adjust_type")
    private String adjustType;

    /**
     * 栏目id   0为未分类
     */
    @ExcelProperty(value = "栏目id   0为未分类")
    private Long categoryId;

    /**
     * 调价比例（%）
     */
    @ExcelProperty(value = "调价比例", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "%=")
    private BigDecimal adjustRatio;


}
