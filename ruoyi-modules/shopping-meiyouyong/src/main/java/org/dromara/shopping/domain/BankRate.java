package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 银行费率对象 t_bank_rate
 *
 * @author yzg
 * @date 2024-05-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_bank_rate")
public class BankRate extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "bank_rate_id")
    private Long bankRateId;
    /**
     * 银行ID
     */
    private Long bankId;
    /**
     * 费率
     */
    private BigDecimal rate;
    /**
     * 期数
     */
    private String stagesNumber;
    /**
     * 状态
     */
    private String status;

}
