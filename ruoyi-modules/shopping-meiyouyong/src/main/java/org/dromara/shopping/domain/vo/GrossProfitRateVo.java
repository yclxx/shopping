package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiexi
 * @description
 * @date 2024/8/14 10:51
 */
@Data
@ExcelIgnoreUnannotated
public class GrossProfitRateVo {
    /**
     * 类别
     */
    @ExcelProperty(value = "类别编号")
    private Long categoryId;
    /**
     * 类别名称
     */
    @ExcelProperty(value = "类别名称")
    private String categoryName;
    /**
     * 省份
     */
    @ExcelProperty(value = "归属省份")
    private String province;
    /**
     * 产品数量
     */
    @ExcelProperty(value = "产品数量")
    private Long productCount;
    /**
     * 最低毛利率
     */
    @ExcelProperty(value = "最低毛利率")
    private BigDecimal minRate;
    /**
     * 最高毛利率
     */
    @ExcelProperty(value = "最高毛利率")
    private BigDecimal maxRate;
    /**
     * 平均毛利率
     */
    @ExcelProperty(value = "平均毛利率")
    private BigDecimal avgRate;
}
