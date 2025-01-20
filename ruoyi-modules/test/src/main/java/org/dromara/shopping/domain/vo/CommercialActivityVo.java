package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 商户活动视图对象
 *
 * @author yzg
 * @date 2024-03-26
 */
@Data
@ExcelIgnoreUnannotated
public class CommercialActivityVo {

    private static final long serialVersionUID = 1L;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long activityId;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String activityName;

    /**
     * 活动说明
     */
    @ExcelProperty(value = "活动说明")
    private String activityNote;

    /**
     * 活动银行,对应t_bank
     */
    @ExcelProperty(value = "活动银行,对应t_bank")
    private Long bankId;

    /**
     * 活动地区,存城市adcode,多个之间英文逗号隔开
     */
    @ExcelProperty(value = "活动地区,存城市adcode,多个之间英文逗号隔开")
    private String activityArea;

    /**
     * 活动广告图片
     */
    @ExcelProperty(value = "活动广告图片")
    private String activityImg;

    /**
     * 报名起始日期
     */
    @ExcelProperty(value = "报名起始日期")
    private Date applyStartDate;

    /**
     * 报名截止日期
     */
    @ExcelProperty(value = "报名截止日期")
    private Date applyEndDate;

    /**
     * 活动起始日期
     */
    @ExcelProperty(value = "活动起始日期")
    private Date activityStartDate;

    /**
     * 活动截止日期
     */
    @ExcelProperty(value = "活动截止日期")
    private Date activityEndDate;

    /**
     * 活动平台： 1-云闪付，2-微信，3-支付宝
     */
    @ExcelProperty(value = "活动平台： 0-微信 1-云闪付 2-支付宝", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_activity_platform")
    private String activityPlatform;

    /**
     * 活动类型： 1-线下满减，2-线上收单
     */
    @ExcelProperty(value = "活动类型： 1-线下满减，2-线上收单", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_activity_type")
    private String activityType;

    /**
     * 活动状态： 0-未开始，1-进行中，2-已结束
     */
    @ExcelProperty(value = "活动状态： 0-未开始，1-进行中，2-已结束", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_activity_status")
    private String activityStatus;

    /**
     * 状态: 0-正常，1-停用
     */
    @ExcelProperty(value = "状态: 0-正常，1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
