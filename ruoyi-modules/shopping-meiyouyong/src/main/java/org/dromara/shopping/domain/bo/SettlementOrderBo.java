package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 结算订单业务对象
 *
 * @author yzg
 * @date 2024-08-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SettlementOrderBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 结算记录ID
     */
    @NotNull(message = "结算记录ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long settlementId;

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long number;


}
