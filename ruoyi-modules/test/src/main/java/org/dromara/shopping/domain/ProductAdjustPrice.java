package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品调价对象 t_product_adjust_price
 *
 * @author yzg
 * @date 2024-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_adjust_price")
public class ProductAdjustPrice extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 调价id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 原售价  元
     */
    private BigDecimal sellAmount;
    /**
     * 调价比例
     */
    private BigDecimal priceRatio;
    /**
     * 调整价格（服务费）
     */
    private BigDecimal adjustPrice;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

    private String adjustBatch;

}
