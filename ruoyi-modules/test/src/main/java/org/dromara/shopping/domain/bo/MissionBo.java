package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 任务配置业务对象
 *
 * @author yzg
 * @date 2023-05-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MissionBo extends BaseEntity {

    /**
     * 任务ID
     */
    @NotNull(message = "任务ID不能为空", groups = { EditGroup.class })
    private Long missionId;

    /**
     * 任务组ID
     */
    @NotNull(message = "任务组ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long missionGroupId;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String missionName;

    /**
     * 任务编号
     */
    private String missionUpid;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startDate;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endDate;
    /**
     * 任务时间
     */
    private String missionTime;

    /**
     * 任务刷新周期: 0-无周期，1-日，2-周，3-月
     */
    @NotBlank(message = "任务刷新周期不能为空", groups = { AddGroup.class, EditGroup.class })
    private String periodType;

    /**
     * 规则图片
     */
    private String missionImg;

    /**
     * 任务奖励类型：0-抽奖机会（需抽取奖品，票券、红包、积点等），1-奖品（直接发放奖品，票券、红包、积点等）
     */
    private String missionAwardType;

    /**
     * 奖励失效时间类型：0-无失效时间，1-指定失效时间，2-增加指定天数，3-次月指定时间失效
     */
    private String awardExpiryType;

    /**
     * 奖励失效时间,失效时间类型为1（yyyy-MM-dd HH:mm:ss）,失效时间类型为2（填写数字）,失效时间类型为3（dd HH:mm:ss）
     */
    private String awardExpiryDate;

    /**
     * 奖励总额度，0为不限制上限
     */
    private BigDecimal missionTotalQuota;

    /**
     * 任务周期额度，0为不限制上限
     */
    private BigDecimal missionPeriodQuota;

    /**
     * 用户累计可获奖励，0为不限制上限
     */
    private BigDecimal userTotalQuota;

    /**
     * 用户任务周期可获奖励，0为不限制上限
     */
    private BigDecimal userPeriodQuota;

    /**
     * 用户每日限参与次数
     */
    private Long userCountDay;

    /**
     * 用户每周限参与次数
     */
    private Long userCountWeek;

    /**
     * 用户每月限参与次数
     */
    private Long userCountMonth;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 展示首页
     */
    private String showIndex;

    /**
     * 展示城市
     */
    private String showCity;

    /**
     * 展示开始时间
     */
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    private Date showEndDate;

    /**
     * 展示内容类型
     */
    private String showContentType;

    /**
     * 展示内容
     */
    private String showContent;

    /**
     * 指定周几
     */
    private String assignDate;

    /**
     * 周几展示
     */
    private String weekDate;

    /**
     * 跳转类型
     */
    private String toType;

    /**
     * 小程序ID
     */
    private String appId;

    /**
     * 页面地址
     */
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
