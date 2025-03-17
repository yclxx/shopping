package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 巡检奖励业务对象
 *
 * @author yzg
 * @date 2024-01-28
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopTourRewardBo extends BaseEntity {

    /**
     * 巡检奖励id
     */
    @NotNull(message = "巡检奖励id不能为空", groups = { EditGroup.class })
    private Long tourRewardId;

    /**
     * 巡检人员id
     */
    @NotNull(message = "巡检人员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    /**
     * 巡检次数
     */
    @NotNull(message = "巡检次数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long count;

    /**
     * 巡检奖励  单位：分
     */
    @NotNull(message = "巡检奖励  单位：分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long amount;

    /**
     * 发放状态  0-未发放  1-发放中  2-已发放
     */
    @NotBlank(message = "发放状态  0-未发放  1-发放中  2-已发放不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
