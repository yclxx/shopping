package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品价格配置业务对象
 *
 * @author yzg
 * @date 2023-07-24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAmountBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long amountId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    @NotNull(message = "发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal externalProductSendValue;

    /**
     * 中奖概率
     */
    @NotNull(message = "中奖概率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal drawProbability;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
