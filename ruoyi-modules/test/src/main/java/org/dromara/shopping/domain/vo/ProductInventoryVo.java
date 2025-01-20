package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;



/**
 * 商品库存信息视图对象
 *
 * @author yzg
 * @date 2024-05-10
 */
@Data
@ExcelIgnoreUnannotated
public class ProductInventoryVo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品库存id
     */
    @ExcelProperty(value = "商品库存id")
    private Long inventoryId;

    /**
     * 商品id
     */
    @ExcelProperty(value = "商品id")
    private Long productId;

    /**
     * 前日库存数量
     */
    @ExcelProperty(value = "前日库存数量")
    private Long yesterdayCount;

    private Date inventoryDate;


}
