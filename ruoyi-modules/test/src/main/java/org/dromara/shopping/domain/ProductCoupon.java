package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 优惠券对象 t_product_coupon
 *
 * @author yzg
 * @date 2023-10-16
 */
@Data
@TableName("t_product_coupon")
public class ProductCoupon implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 优惠券ID
     */
    private Long couponId;
    /**
     * 商品id
     */
    private Long productId;

}
