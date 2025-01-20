package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 商品商品组关联视图对象
 *
 * @author yzg
 * @date 2024-01-16
 */
@Data
@ExcelIgnoreUnannotated
public class ProductGroupConnectVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 商品组ID
     */
    @ExcelProperty(value = "商品组ID")
    private Long productGroupId;

    /**
     * 商品Id
     */
    @ExcelProperty(value = "商品Id")
    private Long productId;


}
