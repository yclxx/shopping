package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 供应商产品分类业务对象
 *
 * @author yzg
 * @date 2023-09-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CategorySupplierBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 供应商编号（字典键值）
     */
    @NotBlank(message = "供应商编号（字典键值）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierId;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;

    /**
     * 供应商多级分类
     */
    @NotBlank(message = "供应商多级分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fullName;

    /**
     * 分类Id(多个分类名称以英文逗号隔开)
     */
    private String categoryId;


}
