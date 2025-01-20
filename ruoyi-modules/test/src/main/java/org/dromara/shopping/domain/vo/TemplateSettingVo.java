package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 落地页配置视图对象
 *
 * @author yzg
 * @date 2023-06-09
 */
@Data
@ExcelIgnoreUnannotated
public class TemplateSettingVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 落地页
     */
    @ExcelProperty(value = "落地页")
    private Long templateId;

    /**
     * 图片
     */
    @ExcelProperty(value = "图片")
    private String img;

    /**
     * 是否按钮
     */
    @ExcelProperty(value = "是否按钮", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_template_setting_btn")
    private String btn;

    /**
     * 跳转类型
     */
    @ExcelProperty(value = "跳转类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_template_setting_to_type")
    private String toType;

    /**
     * 小程序ID
     */
    @ExcelProperty(value = "小程序ID")
    private String appId;

    /**
     * 页面地址
     */
    @ExcelProperty(value = "页面地址")
    private String url;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

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

    /**
     * 图片宽度
     */
    private BigDecimal width;

    /**
     * 父级id
     */
    private Long parentId;

    private List<TemplateSettingVo> settingVoList;

}
