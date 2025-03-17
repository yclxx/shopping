package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品调价业务对象
 *
 * @author yzg
 * @date 2024-05-29
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAdjustPriceBo extends BaseEntity {

    /**
     * 调价id
     */
    @NotNull(message = "调价id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 原售价  元
     */
    @NotNull(message = "原售价  元不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal sellAmount;

    /**
     * 调价比例
     */
    @NotNull(message = "调价比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal priceRatio;

    /**
     * 调整价格（服务费）
     */
    @NotNull(message = "调整价格（服务费）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal adjustPrice;

    private String adjustBatch;

}
