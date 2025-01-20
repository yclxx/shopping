package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 购物车对象 t_cart
 *
 * @author yzg
 * @date 2023-10-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_cart")
public class Cart extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 加入时售价
     */
    private BigDecimal createSellingPrice;
    /**
     * 数量
     */
    private Long quantity;

}
