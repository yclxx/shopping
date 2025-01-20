package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现审核业务对象
 *
 * @author yzg
 * @date 2024-10-31
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShareWithdrawDepositLogBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 分销员用户ID
     */
    @NotNull(message = "分销员用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 提现金额
     */
    @NotNull(message = "提现金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 审核状态
     */
    @NotBlank(message = "审核状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 发放状态
     */
    @NotBlank(message = "发放状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sendStatus;

    /**
     * 发放时间
     */
    @NotNull(message = "发放时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sendTime;

    /**
     * 发放账号
     */
    @NotBlank(message = "发放账号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sendAccount;

    /**
     * 发放订单号
     */
    @NotBlank(message = "发放订单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pushNumber;

    /**
     * 发放结果
     */
    @NotBlank(message = "发放结果不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pushRemake;

    /**
     * 拒绝原因
     */
    @NotBlank(message = "拒绝原因不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remake;

    /**
     * 回滚金额：0-未回滚，1-已回滚
     */
    private String callbackAmount;

    private String beginCreateTime;
    private String endCreateTime;
}
