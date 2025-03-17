package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 商户活动报名门店业务对象
 *
 * @author yzg
 * @date 2024-04-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CommercialActivityApplyShopBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 报名记录ID
     */
    @NotNull(message = "报名记录ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long applyId;

    /**
     * 活动ID
     */
    @NotNull(message = "活动ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityName;

    /**
     * 门店ID
     */
    @NotNull(message = "门店ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long shopId;

    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shopName;

    /**
     * 商户号
     */
    @NotBlank(message = "商户号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantNo;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
