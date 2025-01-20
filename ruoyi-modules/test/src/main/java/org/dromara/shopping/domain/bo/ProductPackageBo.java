package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 商品券包业务对象
 *
 * @author yzg
 * @date 2023-06-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductPackageBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long packageId;

    /**
     * 券包ID
     */
    @NotNull(message = "券包ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 包内产品
     */
    @NotNull(message = "包内产品不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long extProductId;

    /**
     * 发放数量
     */
    @NotNull(message = "发放数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sendCount;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
