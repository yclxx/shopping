package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 结算订单退款冲正对象 t_settlement_order_back
 *
 * @author yzg
 * @date 2024-09-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_settlement_order_back")
public class SettlementOrderBack extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 冲正结算记录ID
     */
    private Long backSettlementId;
    /**
     * 订单号
     */
    private Long number;
    /**
     * 状态：0-未处理，1-已冲正
     */
    private String status;

}
