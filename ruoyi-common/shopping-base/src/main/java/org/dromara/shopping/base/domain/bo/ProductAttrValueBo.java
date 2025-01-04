package org.dromara.shopping.base.domain.bo;

import org.dromara.shopping.base.domain.ProductAttrValue;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

/**
 * 商品属性值业务对象 t_product_attr_value
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ProductAttrValue.class, reverseConvertGenerate = false)
public class ProductAttrValueBo extends BaseEntity {

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
     * 商品属性索引值 (attr_value|attr_value[|....])
     */
    @NotBlank(message = "商品属性索引值 (attr_value|attr_value[|....])不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sku;

    /**
     * 图片
     */
    @NotBlank(message = "图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long img;

    /**
     * 售价
     */
    @NotNull(message = "售价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long price;

    /**
     * 成本价
     */
    @NotNull(message = "成本价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cost;

    /**
     * 原价
     */
    private Long otPrice;

    /**
     * 总库存
     */
    @NotNull(message = "总库存不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long totalStock;

    /**
     * 销量
     */
    private Long sales;

    /**
     * 剩余库存
     */
    private Long stock;

    /**
     * attr_values 创建更新时的属性对应
     */
    @NotBlank(message = "attr_values 创建更新时的属性对应不能为空", groups = { AddGroup.class, EditGroup.class })
    private String attrValue;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
