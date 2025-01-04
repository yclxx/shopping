package org.dromara.shopping.base.domain.bo;

import org.dromara.shopping.base.domain.ProductImg;
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
 * 商品图片业务对象 t_product_img
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ProductImg.class, reverseConvertGenerate = false)
public class ProductImgBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long id;

    /**
     * 商品
     */
    @NotNull(message = "商品不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long productId;

    /**
     * 图片
     */
    @NotBlank(message = "图片不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long img;

    /**
     * 图片归属
     */
    @NotBlank(message = "图片归属不能为空", groups = {AddGroup.class, EditGroup.class})
    private String imgAttribution;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;

    /**
     * 排序
     */
    private Long sort;

}
