package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * 任务组可兑换商品配置业务对象
 *
 * @author yzg
 * @date 2023-05-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MissionGroupProductBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 任务组ID
     */
    @NotNull(message = "任务组ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long missionGroupId;

    /**
     * 任务ID
     */
    private Long missionId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 排序：从小到大
     */
    private Long sort;


}
