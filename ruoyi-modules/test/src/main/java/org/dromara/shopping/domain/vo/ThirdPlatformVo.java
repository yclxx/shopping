package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 第三方平台信息配置视图对象
 *
 * @author yzg
 * @date 2024-03-08
 */
@Data
@ExcelIgnoreUnannotated
public class ThirdPlatformVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * appId
     */
    @ExcelProperty(value = "appId")
    private String appId;

    /**
     * 密钥
     */
    @ExcelProperty(value = "密钥")
    private String secret;

    /**
     * 类型
     */
    @ExcelProperty(value = "类型")
    private String type;

    /**
     * 平台名称
     */
    @ExcelProperty(value = "平台名称")
    private String appName;

    /**
     * 状态：0-正常，1-禁用
     */
    @ExcelProperty(value = "状态：0-正常，1-禁用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_notice_status")
    private String status;


}
