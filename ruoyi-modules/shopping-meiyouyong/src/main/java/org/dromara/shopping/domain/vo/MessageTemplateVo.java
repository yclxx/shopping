package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;



/**
 * 消息模板视图对象
 *
 * @author yzg
 * @date 2023-11-23
 */
@Data
@ExcelIgnoreUnannotated
public class MessageTemplateVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long templateId;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 模板标识
     */
    @ExcelProperty(value = "模板标识")
    private String templateKey;

    /**
     * 模板名称
     */
    @ExcelProperty(value = "模板名称")
    private String templateName;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "channel_type")
    private String channel;

    /**
     * 关键字
     */
    @ExcelProperty(value = "关键字")
    private String keyword;


}
