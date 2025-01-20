package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 核销员供应商关联业务对象
 *
 * @author yzg
 * @date 2024-03-25
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class VerifierSupplierBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 核销员id
     */
    @NotNull(message = "核销员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    /**
     * 供应商id
     */
    @NotNull(message = "供应商id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long supplierId;


}
