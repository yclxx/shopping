package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商户账户明细业务对象
 *
 * @author yzg
 * @date 2024-09-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CommercialTenantAccountDetailBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商户ID
     */
    @NotNull(message = "商户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long commercialTenantId;

    /**
     * 商户名称
     */
    private String commercialTenantName;

    /**
     * 交易金额 单位:元，加钱正数，扣钱负数
     */
    @NotNull(message = "交易金额 单位:元，加钱正数，扣钱负数不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 交易类型
     */
    @NotBlank(message = "交易类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String detailType;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 提现流水号
     */
    @NotBlank(message = "提现流水号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pushNumber;

    /**
     * 提现失败原因
     */
    @NotBlank(message = "提现失败原因不能为空", groups = { AddGroup.class, EditGroup.class })
    private String failReason;

    /**
     * 备注
     */
    @NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;


}
