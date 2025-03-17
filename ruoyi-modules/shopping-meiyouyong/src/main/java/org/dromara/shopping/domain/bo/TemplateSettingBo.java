package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 落地页配置业务对象
 *
 * @author yzg
 * @date 2023-06-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class TemplateSettingBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 落地页
     */
    @NotNull(message = "落地页不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long templateId;

    /**
     * 图片
     */
    //@NotBlank(message = "图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String img;

    /**
     * 是否按钮
     */
    @NotBlank(message = "是否按钮不能为空", groups = { AddGroup.class, EditGroup.class })
    private String btn;

    /**
     * 跳转类型
     */
    private String toType;

    /**
     * 小程序ID
     */
    private String appId;

    /**
     * 页面地址
     */
    private String url;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 图片宽度
     */
    private BigDecimal width;

    /**
     * 父级id
     */
    private Long parentId;


}
