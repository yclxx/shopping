package org.dromara.shopping.base.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;

import java.io.Serial;

/**
 * 商品图片对象 t_product_img
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_img")
public class ProductImg extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;

    /**
     * 商品
     */
    private Long productId;

    /**
     * 图片
     */
    private Long img;

    /**
     * 图片归属
     */
    private String imgAttribution;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序
     */
    private Long sort;


}
