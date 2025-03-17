package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 巡检活动业务对象
 *
 * @author yzg
 * @date 2024-03-01
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopTourActivityBo extends BaseEntity {

    /**
     * 巡检活动id
     */
    @NotNull(message = "巡检活动id不能为空", groups = { EditGroup.class })
    private Long tourActivityId;

    /**
     * 巡检活动名称
     */
    @NotBlank(message = "巡检活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tourActivityName;

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
     * 状态  0-正常  1-停用
     */
    @NotBlank(message = "状态  0-正常  1-停用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 可巡检人员：0-不限制，1-白名单限制
     */
    private String openType;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
