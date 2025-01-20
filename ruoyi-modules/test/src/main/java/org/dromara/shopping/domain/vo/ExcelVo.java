package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author 25487
 */
@Data
@AllArgsConstructor
@ExcelIgnoreUnannotated
public class ExcelVo {
    @ExcelProperty(value = "链接")
    private String url;
    @ExcelProperty(value = "编号")
    private String no;
}
