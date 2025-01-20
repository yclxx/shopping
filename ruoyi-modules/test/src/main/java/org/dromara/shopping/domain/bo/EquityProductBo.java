package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 权益包商品业务对象
 *
 * @author yzg
 * @date 2023-06-06
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EquityProductBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 权益包ID
     */
    @NotNull(message = "权益包ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long equityId;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 商品价值
     */
    @NotNull(message = "商品价值不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal productAmount;

    /**
     * 产品归属
     */
    @NotBlank(message = "产品归属不能为空", groups = { AddGroup.class, EditGroup.class })
    private String equityType;

    /**
     * 可领数量
     */
    @NotNull(message = "可领数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sendCount;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
