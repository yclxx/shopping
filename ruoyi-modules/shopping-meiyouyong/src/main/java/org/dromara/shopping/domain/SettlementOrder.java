package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 结算订单对象 t_settlement_order
 *
 * @author yzg
 * @date 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_settlement_order")
public class SettlementOrder extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 结算记录ID
     */
    private Long settlementId;
    /**
     * 订单号
     */
    private Long number;

}
