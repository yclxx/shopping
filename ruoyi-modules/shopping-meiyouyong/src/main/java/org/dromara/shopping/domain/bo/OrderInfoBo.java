package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 订单扩展信息业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderInfoBo extends BaseEntity {

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = { EditGroup.class })
    private Long number;

    /**
     * 62会员状态：00-普通用户 01-会员用户 02-会员冻结用户 03-试用用户
     */
    @NotBlank(message = "62会员状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String vip62Status;

    /**
     * 62会员类型 00-试用 01-月卡 02-季卡 03-年卡 04-普通用户
     */
    @NotBlank(message = "62会员类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String vip62MemberType;

    /**
     * 62会员到期时间
     */
    @NotBlank(message = "62会员到期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String vip62EndTime;

    /**
     * 62会员首次开通时间
     */
    @NotBlank(message = "62会员首次开通时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String vip62BeginTime;

    /**
     * （银联支付）订单发送时间：格式YYYYMMDDhhmmss
     */
    private String txnTime;

    /**
     * （银联支付）查询流水号
     */
    private String queryId;

    /**
     * （银联支付）交易传输时间
     */
    private String traceTime;

    /**
     * （银联支付）系统跟踪号
     */
    private String traceNo;

    /**
     * （银联支付）支付金额
     */
    private BigDecimal txnAmt;

    /**
     * （银联支付）订单优惠信息（支持单品）json字符串，详细内容参照银联单品营销issAddnData内容信息
     */
    private String issAddnData;

    /**
     * 商品快照信息json字符串
     */
    private String commodityJson;

    private String payBankType;



}
