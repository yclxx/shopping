package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团活动视图对象
 *
 * @author yzg
 * @date 2024-09-26
 */
@Data
@ExcelIgnoreUnannotated
public class GroupActivityVo {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号ID
     */
    @ExcelProperty(value = "活动编号ID")
    private Long activityId;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String activityName;

    /**
     * 活动简称
     */
    @ExcelProperty(value = "活动简称")
    private String activityShortName;

    /**
     * 活动开始时间
     */
    @ExcelProperty(value = "活动开始时间")
    private Date activityStartDate;

    /**
     * 活动结束时间
     */
    @ExcelProperty(value = "活动结束时间")
    private Date activityEndDate;

    /**
     * 活动状态：0-未发布
     */
    @ExcelProperty(value = "活动状态：0-未发布", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_group_activity_status")
    private String activityStatus;

    /**
     * 拼团人数
     */
    @ExcelProperty(value = "拼团人数")
    private Long activityCount;

    /**
     * 拼团价格
     */
    @ExcelProperty(value = "拼团价格")
    private BigDecimal activityPrice;

    /**
     * 活动规则
     */
    @ExcelProperty(value = "活动规则")
    private String activityRule;

    /**
     * 分享标题
     */
    @ExcelProperty(value = "分享标题")
    private String shareTitle;

    /**
     * 分享图片
     */
    @ExcelProperty(value = "分享图片")
    private String shareImage;

    /**
     * 拼团有效期
     */
    @ExcelProperty(value = "拼团有效期")
    private Long groupValid;

    /**
     * 商品编号
     */
    @ExcelProperty(value = "商品编号")
    private Long productId;

    /**
     * 是否自动成团
     */
    @ExcelProperty(value = "是否自动成团", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_group_auto")
    private String isGroup;

    /**
     * 活动分享海报
     */
    @ExcelProperty(value = "活动分享海报")
    private String sharePoster;

    /**
     * 海报文案
     */
    @ExcelProperty(value = "海报文案")
    private String sharePosterTitle;

    /**
     * 拼团时间
     */
    @ExcelProperty(value = "拼团时间", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_group_time")
    private String groupTime;

    private Date createTime;

    private Long platformKey;
}
