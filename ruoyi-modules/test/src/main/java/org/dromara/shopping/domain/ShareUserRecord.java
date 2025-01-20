package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分销记录对象 t_share_user_record
 *
 * @author yzg
 * @date 2023-11-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_share_user_record")
public class ShareUserRecord extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "record_id")
    private Long recordId;
    /**
     * 分销员用户ID
     */
    private Long userId;
    /**
     * 被分销用户ID
     */
    private Long inviteeUserId;
    /**
     * 订单号
     */
    private Long number;

    private String orderPayWay;
    /**
     * 订单核销时间
     */
    private Date orderUsedTime;
    /**
     * 奖励金额
     */
    private BigDecimal awardAmount;
    /**
     * 奖励类型
     */
    private String awardType;
    /**
     * 分销状态
     */
    private String inviteeStatus;
    /**
     * 奖励状态
     */
    private String awardStatus;
    /**
     * 奖励时间
     */
    private Date awardTime;
    /**
     * 奖励发放账号
     */
    private String awardAccount;

    /**
     * 奖励订单号
     */
    private String awardPushNumber;
    /**
     * 发放结果
     */
    private String pushRemake;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 实际发放金额
     */
    private BigDecimal actualReleasAmount;
    /**
     * 备注
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
    private BigDecimal totalAmount;
    private BigDecimal payServiceCharge;
}
