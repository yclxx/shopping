package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 结算订单退款冲正业务对象
 *
 * @author yzg
 * @date 2024-09-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SettlementOrderBackBo extends BaseEntity {

    /**
     * ID
     */
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
