package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 版块模板视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class PageBlockVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 版块模板名称
     */
    @ExcelProperty(value = "版块模板名称")
    private String blockName;

    /**
     * 板块模版主键字段
     */
    @ExcelProperty(value = "板块模版主键字段")
    private String mainField;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_page_block_status")
    private String status;

    /**
     * 排序，从小到大
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
