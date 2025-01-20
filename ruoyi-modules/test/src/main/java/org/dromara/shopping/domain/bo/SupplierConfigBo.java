package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * 供应商参数配置业务对象
 *
 * @author yzg
 * @date 2023-10-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierConfigBo extends BaseEntity {

    /**
     * 参数主键
     */
    @NotNull(message = "参数主键不能为空", groups = { EditGroup.class })
    private Long configId;

    /**
     * 供应商ID
     */
    @NotNull(message = "供应商ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long supplierId;

    /**
     * 参数名称
     */
    private String configName;

    /**
     * 参数键名
     */
    private String configKey;

    /**
     * 参数键值
     */
    private String configValue;

    /**
     * 备注
     */
    private String remark;


}
