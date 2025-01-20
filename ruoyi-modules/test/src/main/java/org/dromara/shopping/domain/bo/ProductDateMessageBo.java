package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品（旅游相关）每日价格库存业务对象
 *
 * @author yzg
 * @date 2024-06-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductDateMessageBo extends BaseEntity {

    /**
     * 价格库存id
     */
    @NotNull(message = "价格库存id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;
    /**
     * 第三方商品id
     */
    @NotNull(message = "商品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private String itemId;

    /**
     * 售价元
     */
    private BigDecimal sellAmount;

    /**
     * 结算价格元
     */
    private BigDecimal settlementPrice;

    /**
     * 当日库存
     */
    private Long stock;

    /**
     * 价格与库存日期
     */
    private String date;

    /**
     * 结算类型0-售价模式 1-底价模式
     */
    private String priceType;


}
