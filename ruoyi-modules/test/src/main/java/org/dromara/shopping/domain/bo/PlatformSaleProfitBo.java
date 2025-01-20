package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 平台销售利润业务对象
 *
 * @author yzg
 * @date 2024-09-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformSaleProfitBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 月份
     */
    @NotBlank(message = "月份不能为空", groups = { AddGroup.class, EditGroup.class })
    private String month;

    /**
     * 销售额
     */
    @NotNull(message = "销售额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal salePrice;

    /**
     * 结算额
     */
    @NotNull(message = "结算额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal settlementPrice;

    /**
     * 已核销销售额
     */
    @NotNull(message = "已核销销售额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal usedSalePrice;

    /**
     * 已核销结算额
     */
    @NotNull(message = "已核销结算额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal usedSettlementPrice;

    /**
     * 利润率
     */
    @NotNull(message = "利润率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal usedProfit;


}
