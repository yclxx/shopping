package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;



/**
 * 结算订单退款冲正视图对象
 *
 * @author yzg
 * @date 2024-09-09
 */
@Data
@ExcelIgnoreUnannotated
public class SettlementOrderBackVo {

    private static final long serialVersionUID = 1L;

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

}
