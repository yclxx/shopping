package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商品（旅游相关）每日价格库存视图对象
 *
 * @author yzg
 * @date 2024-06-19
 */
@Data
@ExcelIgnoreUnannotated
public class ProductDateMessageVo {

    private static final long serialVersionUID = 1L;

    /**
     * 价格库存id
     */
    @ExcelProperty(value = "价格库存id")
    private Long id;

    /**
     * 商品id
     */
    @ExcelProperty(value = "商品id")
    private Long productId;

    /**
     * 第三方商品id
     */
    private String itemId;


    /**
     * 售价元
     */
    @ExcelProperty(value = "售价元")
    private BigDecimal sellAmount;

    /**
     * 结算价格元
     */
    @ExcelProperty(value = "结算价格元")
    private BigDecimal settlementPrice;

    /**
     * 当日库存
     */
    @ExcelProperty(value = "当日库存")
    private Long stock;

    /**
     * 价格与库存日期
     */
    @ExcelProperty(value = "价格与库存日期")
    private String date;

    /**
     * 结算类型0-售价模式 1-底价模式
     */
    @ExcelProperty(value = "结算类型0-售价模式 1-底价模式")
    private String priceType;


}
