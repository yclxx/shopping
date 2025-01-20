package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 多平台类别业务对象
 *
 * @author yzg
 * @date 2024-02-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryPlatformBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 类别名称
     */
    @NotBlank(message = "类别名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 类别ID，对应category表，多个之间用逗号隔开
     */
    private String categoryIds;


}
