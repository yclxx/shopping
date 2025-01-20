package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 多平台栏目商品关联业务对象
 *
 * @author yzg
 * @date 2024-02-28
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryPlatformProductBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 多平台栏目ID
     */
    @NotNull(message = "多平台栏目ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryPlatformId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 排序：从小到大
     */
    @NotNull(message = "排序：从小到大不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;

    /**
     * 商品ids
     */
    private List<Long> productIds;

}
