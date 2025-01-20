package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 版块模板字段业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PageBlockColumnBo extends BaseEntity {

    /**
     * 编号
     */
    @NotNull(message = "编号不能为空", groups = { EditGroup.class })
    private Long columnId;

    /**
     * 归属模板编号
     */
    @NotNull(message = "归属模板编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long blockId;

    /**
     * 字段名称
     */
    @NotBlank(message = "字段名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String columnName;

    /**
     * 字段描述
     */
    private String columnComment;

    /**
     * JAVA字段名
     */
    @NotBlank(message = "JAVA字段名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String javaField;

    /**
     * 是否必填（1是）
     */
    @NotBlank(message = "是否必填不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isRequired;

    /**
     * 显示类型（文本框、文本域、下拉框、复选框、单选框、日期控件）
     */
    @NotBlank(message = "显示类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String htmlType;

    /**
     * 字典类型
     */
    private String dictType;

    /**
     * 排序
     */
    private Long sort;


}
