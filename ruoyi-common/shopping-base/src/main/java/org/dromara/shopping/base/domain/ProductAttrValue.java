package org.dromara.shopping.base.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;

/**
 * 商品属性值对象 t_product_attr_value
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_attr_value")
public class ProductAttrValue extends TenantEntity {

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
     * 商品属性索引值 (attr_value|attr_value[|....])
     */
    private String sku;

    /**
     * 图片
     */
    private Long img;

    /**
     * 售价
     */
    private Long price;

    /**
     * 成本价
     */
    private Long cost;

    /**
     * 原价
     */
    private Long otPrice;

    /**
     * 总库存
     */
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
    private String attrValue;

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
