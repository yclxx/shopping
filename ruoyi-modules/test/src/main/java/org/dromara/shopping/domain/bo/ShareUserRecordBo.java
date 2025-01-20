package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分销记录业务对象
 *
 * @author yzg
 * @date 2023-11-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShareUserRecordBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long recordId;

    /**
     * 分销员用户ID
     */
    @NotNull(message = "分销员用户ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userId;

    /**
     * 被分销用户ID
     */
    @NotNull(message = "被分销用户ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long inviteeUserId;

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long number;

    private String orderPayWay;

    private String queryOrderType;

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

    private String beginCreateTime;
    private String endCreateTime;
    private BigDecimal totalAmount;
    private BigDecimal payServiceCharge;

    private Long platformKey;
}
