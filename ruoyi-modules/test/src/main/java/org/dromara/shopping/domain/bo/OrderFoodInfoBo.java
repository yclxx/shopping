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
 * 美食套餐详细订单业务对象
 *
 * @author yzg
 * @date 2023-05-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderFoodInfoBo extends BaseEntity {

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = { EditGroup.class })
    private Long number;

    /**
     * 第三方订单id
     */
    @NotBlank(message = "第三方订单id不能为空", groups = { AddGroup.class, EditGroup.class })
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
     * 订单状态。当前只有PAID一个状态
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
