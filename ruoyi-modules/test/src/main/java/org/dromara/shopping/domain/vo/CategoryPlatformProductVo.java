package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 多平台栏目商品关联视图对象
 *
 * @author yzg
 * @date 2024-02-28
 */
@Data
@ExcelIgnoreUnannotated
public class CategoryPlatformProductVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 多平台栏目ID
     */
    @ExcelProperty(value = "多平台栏目ID")
    private Long categoryPlatformId;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序：从小到大")
    private Long sort;


}
