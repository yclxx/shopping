package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 银联任务奖励发放记录业务对象
 *
 * @author yzg
 * @date 2024-02-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayMissionUserLogBo extends BaseEntity {

    /**
     * 奖励记录ID
     */
    @NotNull(message = "奖励记录ID不能为空", groups = { EditGroup.class })
    private Long upMissionUserLog;

    /**
     * 银联任务用户ID
     */
    //@NotNull(message = "银联任务用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upMissionUserId;

    /**
     * 银联任务组ID
     */
    //@NotNull(message = "银联任务组ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upMissionGroupId;

    /**
     * 银联任务ID
     */
    //@NotNull(message = "银联任务ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long upMissionId;

    /**
     * 奖励产品ID
     */
    //@NotNull(message = "奖励产品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 状态  0-未发放  1-发放中  2-发放成功  3-发放失败
     */
    //@NotBlank(message = "状态  0-未发放  1-发放中  2-发放成功  3-发放失败不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 发放账号
     */
    //@NotBlank(message = "发放账号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String account;

    /**
     * 发放时间
     */
    //@NotNull(message = "发放时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sendTime;

    /**
     * 金额
     */
    //@NotNull(message = "金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal amount;

    /**
     * 发放数量
     */
    //@NotNull(message = "发放数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sendCount;

    /**
     * 失败原因
     */
    //@NotBlank(message = "失败原因不能为空", groups = { AddGroup.class, EditGroup.class })
    private String failReason;

    /**
     * 订单号
     */
    private Long number;

}
