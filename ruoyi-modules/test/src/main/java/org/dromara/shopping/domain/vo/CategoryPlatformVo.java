package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 多平台类别视图对象
 *
 * @author yzg
 * @date 2024-02-27
 */
@Data
@ExcelIgnoreUnannotated
public class CategoryPlatformVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 类别名称
     */
    @ExcelProperty(value = "类别名称")
    private String name;

    /**
     * 类别ID，对应category表，多个之间用逗号隔开
     */
    @ExcelProperty(value = "类别ID，对应category表，多个之间用逗号隔开")
    private String categoryIds;


}
