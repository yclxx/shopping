package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 商户活动业务对象
 *
 * @author yzg
 * @date 2024-03-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CommercialActivityBo extends BaseEntity {

    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空", groups = { EditGroup.class })
    private Long activityId;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityName;

    /**
     * 活动说明
     */
    @NotBlank(message = "活动说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityNote;

    /**
     * 活动银行,对应t_bank
     */
    @NotNull(message = "活动银行,对应t_bank不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bankId;

    /**
     * 活动地区,存城市adcode,多个之间英文逗号隔开
     */
    @NotBlank(message = "活动地区,存城市adcode,多个之间英文逗号隔开不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityArea;

    /**
     * 活动广告图片
     */
    @NotBlank(message = "活动广告图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityImg;

    /**
     * 报名起始日期
     */
    private Date applyStartDate;

    /**
     * 报名截止日期
     */
    @NotNull(message = "报名截止日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date applyEndDate;

    /**
     * 活动起始日期
     */
    private Date activityStartDate;

    /**
     * 活动截止日期
     */
    @NotNull(message = "活动截止日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date activityEndDate;

    /**
     * 活动平台： 1-云闪付，2-微信，3-支付宝
     */
    @NotBlank(message = "活动平台： 1-云闪付，2-微信，3-支付宝不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityPlatform;

    /**
     * 活动类型： 1-线下满减，2-线上收单
     */
    @NotBlank(message = "活动类型： 1-线下满减，2-线上收单不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityType;

    /**
     * 活动状态： 0-未开始，1-进行中，2-已结束
     */
    @NotBlank(message = "活动状态： 0-未开始，1-进行中，2-已结束不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityStatus;

    /**
     * 状态: 0-正常，1-停用
     */
    @NotBlank(message = "状态: 0-正常，1-停用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
