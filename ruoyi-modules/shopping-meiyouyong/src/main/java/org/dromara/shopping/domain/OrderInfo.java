package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 订单扩展信息对象 t_order_info
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_info")
public class OrderInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 订单号
     */
    @TableId(value = "number",type = IdType.INPUT)
    private Long number;
    /**
     * 62会员状态：00-普通用户 01-会员用户 02-会员冻结用户 03-试用用户
     */
    private String vip62Status;
    /**
     * 62会员类型 00-试用 01-月卡 02-季卡 03-年卡 04-普通用户
     */
    private String vip62MemberType;
    /**
     * 62会员到期时间
     */
    private String vip62EndTime;
    /**
     * 62会员首次开通时间
     */
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
