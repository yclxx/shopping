package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券批次对象 t_product_action
 *
 * @author yzg
 * @date 2023-10-12
 */
@Data
@TableName("t_product_action")
public class ProductAction implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 批次ID
     */
    private Long actionId;
    /**
     * 商品id
     */
    private Long productId;
}
