package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 分销用户账户对象 t_share_user_account
 *
 * @author yzg
 * @date 2023-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_share_user_account")
public class ShareUserAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id")
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
    /**
     * 乐观锁版本号
     */
    @Version
    private Long version;

}
