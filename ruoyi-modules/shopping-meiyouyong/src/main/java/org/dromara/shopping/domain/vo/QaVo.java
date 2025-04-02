package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



/**
 * 常见问题视图对象
 *
 * @author yzg
 * @date 2025-04-02
 */
@Data
@ExcelIgnoreUnannotated
public class QaVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 问题描述
     */
    @ExcelProperty(value = "问题描述")
    private String issue;

    /**
     * 解决方案
     */
    @ExcelProperty(value = "解决方案")
    private String solution;


}
