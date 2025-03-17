package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品（旅游相关）每日价格库存对象 t_product_date_message
 *
 * @author yzg
 * @date 2024-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_date_message")
public class ProductDateMessage extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 价格库存id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 第三方商品id
     */
    private String itemId;
    /**
     * 售价元
     */
    private BigDecimal sellAmount;
    /**
     * 结算价格元
     */
    private BigDecimal settlementPrice;
    /**
     * 当日库存
     */
    private Long stock;
    /**
     * 价格与库存日期
     */
    private String date;
    /**
     * 结算类型0-售价模式 1-底价模式
     */
    private String priceType;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
