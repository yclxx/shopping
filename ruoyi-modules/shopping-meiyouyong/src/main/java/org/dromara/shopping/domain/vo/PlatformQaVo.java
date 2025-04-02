package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



/**
 * 平台常见问题视图对象
 *
 * @author yzg
 * @date 2025-04-02
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformQaVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 常见问题
     */
    @ExcelProperty(value = "常见问题")
    private Long qaId;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;


}
