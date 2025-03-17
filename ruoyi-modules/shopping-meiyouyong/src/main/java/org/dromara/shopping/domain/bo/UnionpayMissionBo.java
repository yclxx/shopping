package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 银联任务配置业务对象
 *
 * @author yzg
 * @date 2024-02-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayMissionBo extends BaseEntity {

    /**
     * 银联任务ID
     */
    @NotNull(message = "银联任务ID不能为空", groups = { EditGroup.class })
    private Long upMissionId;

    /**
     * 银联任务组ID
     */
    //@NotNull(message = "银联任务组ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upMissionGroupId;

    /**
     * 银联任务名称
     */
    //@NotBlank(message = "银联任务名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String upMissionName;

    /**
     * 银联任务id
     */
    private String upMissionUpid;

    /**
     * 发放奖励产品id
     */
    //@NotNull(message = "发放奖励产品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 状态  0-正常  1-停用
     */
    //@NotBlank(message = "状态  0-正常  1-停用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 开始时间
     */
    //@NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startDate;

    /**
     * 结束时间
     */
    //@NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endDate;

    /**
     * 平台标识
     */
    //@NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 用户每日限参与次数
     */
    //@NotNull(message = "用户每日限参与次数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userCountDay;

    /**
     * 用户每周限参与次数
     */
    //@NotNull(message = "用户每周限参与次数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userCountWeek;

    /**
     * 用户每月限参与次数
     */
    //@NotNull(message = "用户每月限参与次数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userCountMonth;

    /**
     * 用户活动期间限参与次数
     */
    //@NotNull(message = "用户活动期间限参与次数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userCountActivity;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 交易类型
     */
    private String tranType;

    /**
     * 限制交易数量
     */
    private Long tranCount;

    /**
     * 单笔支付金额
     */
    private Long payAmount;

}
