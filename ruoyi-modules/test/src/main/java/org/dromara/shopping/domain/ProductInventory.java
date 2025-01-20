package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商品库存信息对象 t_product_inventory
 *
 * @author yzg
 * @date 2024-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_inventory")
public class ProductInventory extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 商品库存id
     */
    @TableId(value = "inventory_id")
    private Long inventoryId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 前日库存数量
     */
    private Long yesterdayCount;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

    private Date inventoryDate;

}
