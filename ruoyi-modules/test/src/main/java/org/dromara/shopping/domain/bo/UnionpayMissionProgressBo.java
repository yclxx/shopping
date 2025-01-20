package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 银联任务进度业务对象
 *
 * @author yzg
 * @date 2024-02-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayMissionProgressBo extends BaseEntity {

    /**
     * 任务进度ID
     */
    @NotNull(message = "任务进度ID不能为空", groups = { EditGroup.class })
    private Long progressId;

    /**
     * 银联任务ID
     */
    //@NotNull(message = "银联任务ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upMissionId;

    /**
     * 银联任务组ID
     */
    //@NotNull(message = "银联任务组ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upMissionGroupId;

    /**
     * 银联任务用户id
     */
    //@NotNull(message = "银联任务用户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upMissionUserId;

    /**
     * 当日进度
     */
    //@NotNull(message = "当日进度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long dayProgress;

    /**
     * 本周进度
     */
    //@NotNull(message = "本周进度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long weekProgress;

    /**
     * 本月进度
     */
    //@NotNull(message = "本月进度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long monthProgress;

    /**
     * 活动总进度
     */
    //@NotNull(message = "活动总进度不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityProgress;

    /**
     * 交易进度
     */
    private String tranProgress;

}
