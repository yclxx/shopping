package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 浏览任务视图对象
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@ExcelIgnoreUnannotated
public class BrowseVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long browseId;

    /**
     * 任务名称
     */
    @ExcelProperty(value = "任务名称")
    private String browseName;

    /**
     * 奖励说明
     */
    @ExcelProperty(value = "奖励说明")
    private String browseRemark;

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
     * 展示城市
     */
    @ExcelProperty(value = "展示城市")
    private String showCity;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "channel_type")
    private String supportChannel;

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
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

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
     * 用户是否浏览，0-未浏览，1-已浏览
     */
    private Integer browseCount = 0;
}
