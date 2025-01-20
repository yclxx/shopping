package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品商铺关联对象 t_seller_product
 *
 * @author yzg
 * @date 2024-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_seller_product")
public class SellerProduct extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商铺ID
     */
    private Long sellerId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 排序：从小到大
     */
    private Long sort;

}
