package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 栏目商品关联业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryProductBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 栏目ID
     */
    @NotNull(message = "栏目ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryId;

    /**
     * 商品ID或商户ID，具体根据栏目内容类型决定
     */
    @NotNull(message = "商品ID或商户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 排序：从小到大
     */
    @NotNull(message = "排序不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;

    private List<Long> productIds;
}
