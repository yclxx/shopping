package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品调价比例业务对象
 *
 * @author yzg
 * @date 2024-05-30
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductAdjustRatioBo extends BaseEntity {

    /**
     * 调价比例id
     */
    @NotNull(message = "调价比例id不能为空", groups = { EditGroup.class })
    private Long ratioId;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 调价类型  1-平台统一调价  2-分类调价
     */
    @NotBlank(message = "调价类型  1-平台统一调价  2-分类调价不能为空", groups = { AddGroup.class, EditGroup.class })
    private String adjustType;

    /**
     * 栏目id   0为未分类
     */
    @NotNull(message = "栏目id   0为未分类不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryId;

    /**
     * 调价比例（%）
     */
    @NotNull(message = "调价比例（%）不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal adjustRatio;


}
