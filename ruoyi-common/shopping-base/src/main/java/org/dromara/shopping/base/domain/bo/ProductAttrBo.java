package org.dromara.shopping.base.domain.bo;

import org.dromara.shopping.base.domain.ProductAttr;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 商品属性业务对象 t_product_attr
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ProductAttr.class, reverseConvertGenerate = false)
public class ProductAttrBo extends BaseEntity {

    /**
     * 主键
     */
    @NotNull(message = "主键不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品
     */
    @NotNull(message = "商品不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 属性名
     */
    @NotBlank(message = "属性名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrName;

    /**
     * 属性值
     */
    @NotBlank(message = "属性值不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrValues;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
