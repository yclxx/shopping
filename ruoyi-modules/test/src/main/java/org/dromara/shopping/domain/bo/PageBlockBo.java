package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 版块模板业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PageBlockBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 版块模板名称
     */
    @NotBlank(message = "版块模板名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String blockName;

    /**
     * 板块模版主键字段
     */
    @NotBlank(message = "板块模版字段名称不能为空",groups = {AddGroup.class,EditGroup.class})
    private String mainField;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 排序，从小到大
     */
    private Long sort;

    /**
     * 备注
     */
    private String remark;


}
