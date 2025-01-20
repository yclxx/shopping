package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 任务配置视图对象
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@ExcelIgnoreUnannotated
public class MissionVo {

    private static final long serialVersionUID = 1L;

    /**
     * 任务ID
     */
    @ExcelProperty(value = "任务ID")
    private Long missionId;

    /**
     * 任务组ID
     */
    @ExcelProperty(value = "任务组ID")
    private Long missionGroupId;

    /**
     * 任务名称
     */
    @ExcelProperty(value = "任务名称")
    private String missionName;

    /**
     * 任务编号
     */
    @ExcelProperty(value = "任务编号")
    private String missionUpid;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endDate;
    /**
     * 任务时间
     */
    private String missionTime;

    /**
     * 任务刷新周期: 0-无周期，1-日，2-周，3-月
     */
    @ExcelProperty(value = "任务刷新周期")
    private String periodType;

    /**
     * 规则图片
     */
    @ExcelProperty(value = "规则图片")
    private String missionImg;

    /**
     * 任务奖励类型：0-抽奖机会（需抽取奖品，票券、红包、积点等），1-奖品（直接发放奖品，票券、红包、积点等）
     */
    @ExcelProperty(value = "任务奖励类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "mission_award_type")
    private String missionAwardType;

    /**
     * 奖励失效时间类型：0-无失效时间，1-指定失效时间，2-增加指定天数，3-次月指定时间失效
     */
    @ExcelProperty(value = "奖励失效时间类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "award_expiry_type")
    private String awardExpiryType;

    /**
     * 奖励失效时间,失效时间类型为1（yyyy-MM-dd HH:mm:ss）,失效时间类型为2（填写数字）,失效时间类型为3（dd HH:mm:ss）
     */
    @ExcelProperty(value = "奖励失效时间", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "y=yyy-MM-dd,H=H:mm:ss")
    private String awardExpiryDate;

    /**
     * 奖励总额度，0为不限制上限
     */
    @ExcelProperty(value = "奖励总额度")
    private BigDecimal missionTotalQuota;

    /**
     * 任务周期额度，0为不限制上限
     */
    @ExcelProperty(value = "任务周期额度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "period_type")
    private BigDecimal missionPeriodQuota;

    /**
     * 用户累计可获奖励，0为不限制上限
     */
    @ExcelProperty(value = "用户累计可获奖励")
    private BigDecimal userTotalQuota;

    /**
     * 用户任务周期可获奖励，0为不限制上限
     */
    @ExcelProperty(value = "用户任务周期可获奖励")
    private BigDecimal userPeriodQuota;

    /**
     * 用户每日限参与次数
     */
    @ExcelProperty(value = "用户每日限参与次数")
    private Long userCountDay;

    /**
     * 用户每周限参与次数
     */
    @ExcelProperty(value = "用户每周限参与次数")
    private Long userCountWeek;

    /**
     * 用户每月限参与次数
     */
    @ExcelProperty(value = "用户每月限参与次数")
    private Long userCountMonth;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

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
     * 展示首页
     */
    @ExcelProperty(value = "展示首页", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "mission_show_index")
    private String showIndex;

    /**
     * 展示城市
     */
    @ExcelProperty(value = "展示城市")
    private String showCity;

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
     * 展示内容类型
     */
    @ExcelProperty(value = "展示内容类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "show_content_type")
    private String showContentType;

    /**
     * 展示内容
     */
    @ExcelProperty(value = "展示内容")
    private String showContent;

    /**
     * 指定周几
     */
    @ExcelProperty(value = "指定周几", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_assign_date")
    private String assignDate;

    /**
     * 周几展示
     */
    @ExcelProperty(value = "周几展示", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_grad_period_date_list")
    private String weekDate;

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
     * 排序：从小到大
     */
    private Long sort;

    private String missionType;

    private String missionAffiliation;

    /**
     * 支持端
     */
    private String supportChannel;
    /**
     * 助力时间类型0-长期有效 1-指定失效活动时间
     */
    private String assistanceTimeType;
    /**
     * 助力用户类型0-无限制 1-新用户 2-n天未登录
     */
    private String assistanceUserType;

    /**
     * 是否一人助力多用户0-否 1-是
     */
    private String assistanceAllInvite;

    /**
     *助力失效天数
     */
    private String assistanceTime;

    /**
     *需要助力的人数
     */
    private String assistancePerson;

    /**
     *助力成功发放的奖品id
     */
    private String assistanceProductId;

    /**
     *助力文案
     */
    private String assistanceText;
}
