package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class ProductCouponVo {
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
