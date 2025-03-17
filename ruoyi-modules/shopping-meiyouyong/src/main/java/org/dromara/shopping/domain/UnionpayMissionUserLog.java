package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银联任务奖励发放记录对象 t_unionpay_mission_user_log
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_unionpay_mission_user_log")
public class UnionpayMissionUserLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 奖励记录ID
     */
    @TableId(value = "up_mission_user_log")
    private Long upMissionUserLog;
    /**
     * 银联任务用户ID
     */
    private Long upMissionUserId;
    /**
     * 银联任务组ID
     */
    private Long upMissionGroupId;
    /**
     * 银联任务ID
     */
    private Long upMissionId;
    /**
     * 奖励产品ID
     */
    private Long productId;
    /**
     * 状态  0-未发放  1-发放中  2-发放成功  3-发放失败
     */
    private String status;
    /**
     * 发放账号
     */
    private String account;
    /**
     * 发放时间
     */
    private Date sendTime;
    /**
     * 金额
     */
    private BigDecimal amount;
    /**
     * 发放数量
     */
    private Long sendCount;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 订单号
     */
    private Long number;

}
