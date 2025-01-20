package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商户账户业务对象
 *
 * @author yzg
 * @date 2024-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CommercialTenantAccountBo extends BaseEntity {

    /**
     * 商户ID
     */
    @NotNull(message = "商户ID不能为空", groups = { EditGroup.class })
    private Long commercialTenantId;

    /**
     * 可提金额 单位:元
     */
    @NotNull(message = "可提金额 单位:元不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal balance;

    /**
     * 冻结金额 单位:元
     */
    @NotNull(message = "冻结金额 单位:元不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal frozenBalance;

    /**
     * 已提金额 单位:元
     */
    @NotNull(message = "已提金额 单位:元不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal withdrawBalance;

    /**
     * 总金额 单位:元(可提金额+冻结金额+已提金额)
     */
    @NotNull(message = "总金额 单位:元(可提金额+冻结金额+已提金额)不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal totalBalance;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    private String commercialTenantName;


}
