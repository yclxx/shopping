package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品商品组关联对象 t_product_group_connect
 *
 * @author yzg
 * @date 2024-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_group_connect")
public class ProductGroupConnect extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商品组ID
     */
    private Long productGroupId;
    /**
     * 商品Id
     */
    private Long productId;

}
