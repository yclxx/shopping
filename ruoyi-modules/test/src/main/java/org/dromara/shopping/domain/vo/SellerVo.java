package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 店家配置视图对象
 *
 * @author yzg
 * @date 2024-10-21
 */
@Data
@ExcelIgnoreUnannotated
public class SellerVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 商家名称
     */
    @ExcelProperty(value = "商家名称")
    private String sellerName;

    /**
     * 展示名称
     */
    @ExcelProperty(value = "展示名称")
    private String showName;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_banner_status")
    private String status;

    /**
     * 展示内容类型：0-图片
     */
    @ExcelProperty(value = "展示内容类型：0-图片", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "show_content_type")
    private String showContentType;

    /**
     * 展示内容
     */
    @ExcelProperty(value = "展示内容")
    private String showContent;

    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转
     */
    @ExcelProperty(value = "跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_banner_to_type")
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
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    private Long sysDeptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long sysUserId;


}
