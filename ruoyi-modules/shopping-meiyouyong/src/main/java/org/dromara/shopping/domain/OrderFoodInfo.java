package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 美食套餐详细订单对象 t_order_food_info
 *
 * @author yzg
 * @date 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_food_info")
public class OrderFoodInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @TableId(value = "number")
    private Long number;
    /**
     * 第三方订单id
     */
    private String bizOrderId;
    /**
     * 补差订单号
     */
    private String orderBxNumber;
    /**
     * 第三方补差订单号
     */
    private String bizBxOrderId;

    /**
     * 下单人姓名
     */
    private String userName;
    /**
     * 核销码
     */
    private String ticketCode;
    /**
     * 二维码图片地址
     */
    public String ticketCodeUrl;
    /**
     * 凭证ID
     */
    private String voucherId;
    /**
     * 凭证状态 可用EFFECTIVE、已用USED、失效CANCELED
     */
    private String voucherStatus;
    /**
     * 凭证生效时间
     */
    private String effectTime;
    /**
     * 凭证过期时间
     */
    private String expireTime;
    /**
     * 总份数
     */
    private Integer totalAmount;
    /**
     * 已使用份数
     */
    private Integer usedAmount;
    /**
     * 已退款份数（售中、售后）
     */
    private Integer refundAmount;
    /**
     * 订单状态。
     * 美食套餐当前只有 PAID 一个状态，联联商品为纯数字
     * 联联订单状态 110:支付中 111:已支付 210:已预约 310:已核销 311:核销异常
     * 410:退款中 411:退款不退佣金 412:已退款 510:赔付中 511:赔付成功 610:已过期
     * 享库这个状态当退款状态了*
     */
    private String orderStatus;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品id
     */
    private String itemId;
    /**
     * 活动价
     */
    private BigDecimal sellingPrice;
    /**
     * 原价
     */
    private BigDecimal officialPrice;
    /**
     * 顾客姓名
     */
    private String cusName;
    /**
     * 顾客手机号
     */
    private String cusMobile;
    /**
     * 顾客身份证号
     */
    private String cusIdcard;
    /**
     * 选择购买日期
     */
    private String chooseDate;
    /**
     * 选择离店日期
     */
    private String chooseOutDate;


}
