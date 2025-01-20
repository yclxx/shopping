package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;



/**
 * 页面视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class PageVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 页面标识
     */
    @ExcelProperty(value = "页面标识")
    private String pagePath;

    /**
     * 页面标题
     */
    @ExcelProperty(value = "页面标题")
    private String pageName;

    /**
     * 所属页面
     */
    @ExcelProperty(value = "所属页面")
    private String pageRemake;

    /**
     * 导航栏(含状态栏)背景色及透明度。16进制，前2位(8F)透明度，后六位(FFFFFF)颜色，仅对当前页有效
     */
    @ExcelProperty(value = "导航栏(含状态栏)背景色")
    private String navBackgroundColor;

    /**
     * 导航栏主题颜色：black-黑色主题，white-白色主题
     */
    @ExcelProperty(value = "导航栏主题颜色", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_page_applet_style")
    private String appletStyle;

    /**
     * 标题栏是否显示。0不显示，1显示，默认显示
     */
    @ExcelProperty(value = "标题栏是否显示", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_page_applet_title_bar_visible")
    private String appletTitleBarVisible;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_page_status")
    private String status;

    /**
     * 平台标识
     */
    private Long platformKey;
}
