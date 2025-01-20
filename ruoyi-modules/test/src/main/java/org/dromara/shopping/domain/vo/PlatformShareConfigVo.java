package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 平台分享配置视图对象
 *
 * @author yzg
 * @date 2025-01-14
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformShareConfigVo {

    private static final long serialVersionUID = 1L;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 分享标题
     */
    @ExcelProperty(value = "分享标题")
    private String shareTitle;

    /**
     * 分享描述
     */
    @ExcelProperty(value = "分享描述")
    private String shareName;

    /**
     * 分享图片
     */
    @ExcelProperty(value = "分享图片")
    private String shareImage;


}
