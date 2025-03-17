package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 营销活动视图对象
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@ExcelIgnoreUnannotated
public class MarketActivityVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long activityId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String name;

    /**
     * 图片
     */
    @ExcelProperty(value = "图片")
    private String image;

    /**
     * 规则图片
     */
    @ExcelProperty(value = "规则图片")
    private String ruleImg;

    /**
     * 详情图片
     */
    @ExcelProperty(value = "详情图片")
    private String detailsImg;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "channel_type")
    private String channel;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;


}
