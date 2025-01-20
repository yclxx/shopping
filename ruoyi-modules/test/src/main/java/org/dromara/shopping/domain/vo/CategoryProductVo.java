package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 栏目商品关联视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class CategoryProductVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 栏目ID
     */
    @ExcelProperty(value = "栏目ID")
    private Long categoryId;

    /**
     * 商品ID或商户ID，具体根据栏目内容类型决定
     */
    @ExcelProperty(value = "商品/商户ID")
    private Long productId;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序：从小到大")
    private Long sort;


}
