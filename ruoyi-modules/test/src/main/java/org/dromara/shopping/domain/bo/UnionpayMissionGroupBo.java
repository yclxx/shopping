package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 银联任务组业务对象
 *
 * @author yzg
 * @date 2024-02-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayMissionGroupBo extends BaseEntity {

    /**
     * 任务组ID
     */
    @NotNull(message = "任务组ID不能为空", groups = { EditGroup.class })
    private Long upMissionGroupId;

    /**
     * 任务组名称
     */
    //@NotBlank(message = "任务组名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String upMissionGroupName;

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
     * 银联任务组编号
     */
    //@NotBlank(message = "银联任务组编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String upMissionGroupUpid;

    /**
     * 平台标识
     */
    //@NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;


}
