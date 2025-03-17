package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 奖品管理视图对象
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@ExcelIgnoreUnannotated
public class DrawVo {

    private static final long serialVersionUID = 1L;

    /**
     * 奖品ID
     */
    @ExcelProperty(value = "奖品ID")
    private Long drawId;

    @ExcelProperty(value = "任务组ID")
    private Long missionGroupId;

    /**
     * 奖品名称
     */
    @ExcelProperty(value = "奖品名称")
    private String drawName;

    /**
     * 奖品简称
     */
    @ExcelProperty(value = "奖品简称")
    private String drawSimpleName;

    /**
     * 奖品图片
     */
    @ExcelProperty(value = "奖品图片")
    private String drawImg;

    /**
     * 中奖概率
     */
    @ExcelProperty(value = "中奖概率")
    private BigDecimal drawProbability;

    /**
     * 第二套中奖概率
     */
    private BigDecimal secondDrawProbability;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 奖品类型（0银联票券 1云闪付红包 2云闪付积点）
     */
    @ExcelProperty(value = "奖品类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "draw_type")
    private String drawType;

    /**
     * 是否能中奖
     */
    private String drawWinning;

    /**
     * 取码编号
     */
    @ExcelProperty(value = "取码编号")
    private String drawNo;

    /**
     * 发放金额
     */
    @ExcelProperty(value = "发放金额")
    private BigDecimal sendValue;

    /**
     * 奖品额度
     */
    @ExcelProperty(value = "奖品额度")
    private BigDecimal drawQuota;

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
     * 展示开始时间
     */
    @ExcelProperty(value = "展示开始时间")
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    @ExcelProperty(value = "展示结束时间")
    private Date showEndDate;

    /**
     * 领取开始时间
     */
    @ExcelProperty(value = "领取开始时间")
    private Date sellStartDate;

    /**
     * 领取结束时间
     */
    @ExcelProperty(value = "领取结束时间")
    private Date sellEndDate;

    /**
     * 总次数，0为不限制
     */
    @ExcelProperty(value = "总次数")
    private Long totalCount;

    /**
     * 每月次数，0为不限制
     */
    @ExcelProperty(value = "每月次数")
    private Long monthCount;

    /**
     * 每周次数，0为不限制
     */
    @ExcelProperty(value = "每周次数")
    private Long weekCount;

    /**
     * 每日次数，0为不限制
     */
    @ExcelProperty(value = "每日次数")
    private Long dayCount;

    /**
     * 用户每日限领次数，0为不限制
     */
    @ExcelProperty(value = "用户每日限领次数")
    private Long dayUserCount;

    /**
     * 用户每周限领次数，0为不限制
     */
    @ExcelProperty(value = "用户每周限领次数")
    private Long weekUserCount;

    /**
     * 用户每月限领次数，0为不限制
     */
    @ExcelProperty(value = "用户每月限领次数")
    private Long monthUserCount;

    /**
     * 用户累计限领次数，0为不限制
     */
    @ExcelProperty(value = "用户累计限领次数")
    private Long totalUserCount;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序：从小到大")
    private Long sort;

    /**
     * 机构账户（介入方代码）
     */
    private String accessCode;

    /**
     *  是否专属活动日奖品
     */
    private String luckyDayDraw;


}
