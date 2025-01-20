package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 落地页视图对象
 *
 * @author yzg
 * @date 2023-06-09
 */
@Data
@ExcelIgnoreUnannotated
public class TemplatePageVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long templateId;

    /**
     * 落地页描述
     */
    @ExcelProperty(value = "落地页描述")
    private String templateName;

    /**
     * 显示标题
     */
    @ExcelProperty(value = "显示标题", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_template_page_show_title")
    private String showTitle;

    /**
     * 页面标题
     */
    @ExcelProperty(value = "页面标题")
    private String pageTitle;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    private List<TemplateSettingVo> templateSettingVos;
}
