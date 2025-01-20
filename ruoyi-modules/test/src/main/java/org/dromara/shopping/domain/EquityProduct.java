package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 权益包商品对象 t_equity_product
 *
 * @author yzg
 * @date 2023-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_equity_product")
public class EquityProduct extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 权益包ID
     */
    private Long equityId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品价值
     */
    private BigDecimal productAmount;
    /**
     * 产品归属
     */
    private String equityType;
    /**
     * 可领数量
     */
    private Long sendCount;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 状态
     */
    private String status;

}
