package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算订单视图对象
 *
 * @author yzg
 * @date 2024-08-21
 */
@Data
@ExcelIgnoreUnannotated
public class QuerySettlementOrderBackVo implements Serializable {

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
     * 订单号
     */
    private Long number;

    /**
     * 退款金额
     */
    private BigDecimal refund;

    /**
     * 退款成功时间
     */
    private Date successTime;

    private SettlementVo settlementVo;

}
