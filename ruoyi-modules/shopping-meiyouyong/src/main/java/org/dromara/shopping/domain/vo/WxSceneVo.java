package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 微信小程序二维码视图对象
 *
 * @author yzg
 * @date 2024-10-22
 */
@Data
@ExcelIgnoreUnannotated
public class WxSceneVo {

    private static final long serialVersionUID = 1L;

    /**
     * scene内容，传给微信生成二维码的
     */
    @ExcelProperty(value = "scene内容，传给微信生成二维码的")
    private Long sceneId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 跳转页面
     */
    @ExcelProperty(value = "跳转页面")
    private String page;

    /**
     * 真实scene内容
     */
    @ExcelProperty(value = "真实scene内容")
    private String scene;


}
