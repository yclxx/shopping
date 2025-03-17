package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 分销用户账户业务对象
 *
 * @author yzg
 * @date 2023-11-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShareUserAccountBo extends BaseEntity {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = {EditGroup.class})
    private Long userId;

    /**
     * 是否可提现：0-可提现，1-禁止提现
     */
    private String status;

    /**
     * 冻结金额
     */
    private BigDecimal freezeBalance;

    /**
     * 可提金额
     */
    private BigDecimal balance;

    /**
     * 已提金额
     */
    private BigDecimal withdrawDeposit;

    /**
     * 已退金额
     */
    private BigDecimal refundBalance;
    /**
     * 已冲正金额
     */
    private BigDecimal reversalBalance;
}
