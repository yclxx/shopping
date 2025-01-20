package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



/**
 * 版块模板字段视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class PageBlockColumnVo {

    private static final long serialVersionUID = 1L;

    /**
     * 编号
     */
    @ExcelProperty(value = "编号")
    private Long columnId;

    /**
     * 归属模板编号
     */
    @ExcelProperty(value = "归属模板编号")
    private Long blockId;

    /**
     * 字段名称
     */
    @ExcelProperty(value = "字段名称")
    private String columnName;

    /**
     * 字段描述
     */
    @ExcelProperty(value = "字段描述")
    private String columnComment;

    /**
     * JAVA字段名
     */
    @ExcelProperty(value = "JAVA字段名")
    private String javaField;

    /**
     * 是否必填（1是）
     */
    @ExcelProperty(value = "是否必填")
    private String isRequired;

    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @ExcelProperty(value = "显示类型")
    private String htmlType;

    /**
     * 字典类型
     */
    @ExcelProperty(value = "字典类型")
    private String dictType;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 模板名称
     */
    private String blockName;


}
