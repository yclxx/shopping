package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 自定义首页视图对象
 *
 * @author yzg
 * @date 2023-08-07
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformCityIndexVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 行政编码
     */
    @ExcelProperty(value = "行政编码")
    private String adcode;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 跳转类型
     */
    @ExcelProperty(value = "跳转类型", converter = ExcelDictConvert.class)
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
     * 生效时间
     */
    @ExcelProperty(value = "生效时间")
    private Date startTime;

    /**
     * 失效时间
     */
    @ExcelProperty(value = "失效时间")
    private Date endTime;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 指定周几
     */
    @ExcelProperty(value = "指定周几", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_assign_date")
    private String assignDate;

    /**
     * 周几能领
     */
    @ExcelProperty(value = "周几能领", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_grad_period_date_list")
    private String weekDate;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
