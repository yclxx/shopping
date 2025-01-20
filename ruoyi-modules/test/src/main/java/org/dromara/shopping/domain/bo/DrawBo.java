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
 * 奖品管理业务对象
 *
 * @author yzg
 * @date 2023-05-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class DrawBo extends BaseEntity {

    /**
     * 奖品ID
     */
    @NotNull(message = "奖品ID不能为空", groups = { EditGroup.class })
    private Long drawId;

    @NotNull(message = "任务组不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long missionGroupId;

    /**
     * 奖品名称
     */
    @NotBlank(message = "奖品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String drawName;

    /**
     * 奖品简称
     */
    @NotBlank(message = "奖品简称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String drawSimpleName;

    /**
     * 奖品图片
     */
    private String drawImg;

    /**
     * 中奖概率
     */
    @NotNull(message = "中奖概率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal drawProbability;

    /**
     * 第二套中奖概率
     */
    private BigDecimal secondDrawProbability;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 奖品类型（0银联票券 1云闪付红包 2云闪付积点）
     */
    @NotBlank(message = "奖品类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String drawType;

    /**
     * 是否能中奖
     */
    private String drawWinning;

    /**
     * 取码编号
     */
    private String drawNo;

    /**
     * 发放金额
     */
    private BigDecimal sendValue;

    /**
     * 奖品额度
     */
    @NotNull(message = "奖品额度不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal drawQuota;

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
     * 展示开始时间
     */
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    private Date showEndDate;

    /**
     * 领取开始时间
     */
    private Date sellStartDate;

    /**
     * 领取结束时间
     */
    private Date sellEndDate;

    /**
     * 总次数，0为不限制
     */
    private Long totalCount;

    /**
     * 每月次数，0为不限制
     */
    private Long monthCount;

    /**
     * 每周次数，0为不限制
     */
    private Long weekCount;

    /**
     * 每日次数，0为不限制
     */
    private Long dayCount;

    /**
     * 用户每日限领次数，0为不限制
     */
    private Long dayUserCount;

    /**
     * 用户每周限领次数，0为不限制
     */
    private Long weekUserCount;

    /**
     * 用户每月限领次数，0为不限制
     */
    private Long monthUserCount;

    /**
     * 用户累计限领次数，0为不限制
     */
    private Long totalUserCount;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 排序：从小到大
     */
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
