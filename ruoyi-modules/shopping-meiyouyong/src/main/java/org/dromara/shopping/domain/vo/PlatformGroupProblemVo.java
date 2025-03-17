package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 用户入群问题反馈视图对象
 *
 * @author yzg
 * @date 2024-02-22
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformGroupProblemVo {

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
     * 用户编号
     */
    @ExcelProperty(value = "用户编号")
    private Long userId;

    /**
     * 反馈内容
     */
    @ExcelProperty(value = "反馈内容")
    private String content;


}
