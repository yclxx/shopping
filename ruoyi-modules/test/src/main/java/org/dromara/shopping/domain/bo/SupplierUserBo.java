package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 供应商用户关联业务对象
 *
 * @author yzg
 * @date 2024-12-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierUserBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 供应商编号
     */
    @NotNull(message = "供应商编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long supplierId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;


}
