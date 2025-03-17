package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 退款订单登记对象 t_refund
 *
 * @author yzg
 * @date 2023-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_refund")
public class Refund extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 退款订单ID
     */
    @TableId(value = "refund_id")
    private Long refundId;
    /**
     * 订单号
     */
    private Long number;
    /**
     * 退款金额
     */
    private BigDecimal refundAmount;
    /**
     * 退款申请人(userId)
     */
    private String refundApplicant;
    /**
     * 退款审核人
     */
    private String refundReviewer;
    /**
     * 0=审核中，1=审核通过，2=审核不通过
     */
    private String status;
    /**
     * 审核拒绝原因
     */
    private String refuseReason;
    /**
     * 退款原因
     */
    private String refundRemark;

    /**
     * 支持端
     */
    private String supportChannel;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
