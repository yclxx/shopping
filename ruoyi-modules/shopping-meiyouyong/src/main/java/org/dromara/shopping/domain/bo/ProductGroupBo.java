package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 商品组规则配置业务对象
 *
 * @author yzg
 * @date 2024-01-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductGroupBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long productGroupId;

    /**
     * 商品组名称
     */
    @NotBlank(message = "商品组名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productGroupName;

    /**
     * 用户端提醒
     */
    private String productGroupTip;

    /**
     * 每日同一用户限领数量，0为不限制
     */
    @NotNull(message = "每日同一用户限领数量，0为不限制不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long dayUserCount;

    /**
     * 每周同一用户限领数量，0为不限制
     */
    @NotNull(message = "每周同一用户限领数量，0为不限制不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long weekUserCount;

    /**
     * 当月同一用户限领数量，0为不限制
     */
    @NotNull(message = "当月同一用户限领数量，0为不限制不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long monthUserCount;

    /**
     * 活动周期同一用户限领数量，0为不限制
     */
    @NotNull(message = "活动周期同一用户限领数量，0为不限制不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long totalUserCount;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
