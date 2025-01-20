package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现审核对象 t_share_withdraw_deposit_log
 *
 * @author yzg
 * @date 2024-10-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_share_withdraw_deposit_log")
public class ShareWithdrawDepositLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 分销员用户ID
     */
    private Long userId;
    /**
     * 提现金额
     */
    private BigDecimal amount;
    /**
     * 审核状态
     */
    private String status;
    /**
     * 发放状态
     */
    private String sendStatus;
    /**
     * 发放时间
     */
    private Date sendTime;
    /**
     * 发放账号
     */
    private String sendAccount;
    /**
     * 发放订单号
     */
    private String pushNumber;
    /**
     * 发放结果
     */
    private String pushRemake;
    /**
     * 拒绝原因
     */
    private String remake;
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;

    /**
     * 回滚金额：0-未回滚，1-已回滚
     */
    private String callbackAmount;

    public void setPushRemake(String pushRemake) {
        if (StringUtils.isNotBlank(pushRemake) && pushRemake.length() > 4900) {
            this.pushRemake = pushRemake.substring(0, 4900);
        } else {
            this.pushRemake = pushRemake;
        }
    }
}
