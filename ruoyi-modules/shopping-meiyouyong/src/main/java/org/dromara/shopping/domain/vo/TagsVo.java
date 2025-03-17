package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 标签视图对象
 *
 * @author yzg
 * @date 2023-10-09
 */
@Data
@ExcelIgnoreUnannotated
public class TagsVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long tagsId;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String tagsName;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "tags_type")
    private String tagsType;


}
