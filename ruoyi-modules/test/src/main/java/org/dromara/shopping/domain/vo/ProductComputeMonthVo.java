package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * 订单数据统计（月份）视图对象
 *
 * @author yzg
 * @date 2023-07-12
 */
@Data
@ExcelIgnoreUnannotated
public class ProductComputeMonthVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 统计月份
     */
    @ExcelProperty(value = "统计月份")
    private String month;

    /**
     * 商品编号
     */
    @ExcelProperty(value = "商品编号")
    private Long productId;

    /**
     * 行政区号
     */
    @ExcelProperty(value = "行政区号")
    private String cityCode;

    /**
     * 城市
     */
    @ExcelProperty(value = "城市")
    private String cityName;

    /**
     * 每个城市用户人数
     */
    @ExcelProperty(value = "每个城市用户人数")
    private Long cityUserNumber;

    /**
     * 每个城市订单数量
     */
    @ExcelProperty(value = "每个城市订单数量")
    private Long cityOrderNumber;


}
