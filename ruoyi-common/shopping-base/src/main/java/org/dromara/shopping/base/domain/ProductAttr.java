package org.dromara.shopping.base.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 商品属性对象 t_product_attr
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_attr")
public class ProductAttr extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 商品
     */
    private Long productId;

    /**
     * 属性名
     */
    private String attrName;

    /**
     * 属性值
     */
    private String attrValues;

    /**
     * 状态
     */
    private String status;

    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;


}
