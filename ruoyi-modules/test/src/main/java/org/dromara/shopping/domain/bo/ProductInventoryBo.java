package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 商品库存信息业务对象
 *
 * @author yzg
 * @date 2024-05-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductInventoryBo extends BaseEntity {

    /**
     * 商品库存id
     */
    @NotNull(message = "商品库存id不能为空", groups = { EditGroup.class })
    private Long inventoryId;

    /**
     * 商品id
     */
    //@NotNull(message = "商品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 前日库存数量
     */
    //@NotNull(message = "前日库存数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long yesterdayCount;

    private Date inventoryDate;

}
