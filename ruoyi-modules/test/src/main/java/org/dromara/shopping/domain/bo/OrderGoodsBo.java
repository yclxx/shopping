package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 实物订单业务对象
 *
 * @author yzg
 * @date 2024-03-20
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderGoodsBo extends BaseEntity {

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = {EditGroup.class})
    private Long number;

    /**
     * 产品id
     */
    //@NotNull(message = "产品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 商品名称
     */
    //@NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 购买金额
     */
    //@NotNull(message = "购买金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal price;

    /**
     * 结算金额
     */
    //@NotNull(message = "结算金额不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal sellPrice;

    /**
     * 购买数量
     */
    //@NotNull(message = "购买数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long count;

    /**
     * 购买人手机号
     */
    //@NotBlank(message = "购买人手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String mobile;

    /**
     * 用户地址id
     */
    //@NotNull(message = "用户地址id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userAddressId;

    /**
     * 联系人
     */
    //@NotBlank(message = "联系人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 联系电话
     */
    //@NotBlank(message = "联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tel;

    /**
     * 地址中文，省市县等，用空格隔开
     */
    //@NotBlank(message = "地址中文，省市县等，用空格隔开不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    //@NotBlank(message = "详细地址（街道门牌号啥的，全地址需要address+address_info）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String addressInfo;

    /**
     * 邮费
     */
    //@NotNull(message = "邮费不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal ticketPostage;

    /**
     * 物流单号
     */
    //@NotBlank(message = "物流单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String logistics;

    /**
     * 物流状态
     */
    //@NotBlank(message = "物流状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String logisticsStatus;

    private List<String> logisticsStatusList;

    /**
     * 物流公司
     */
    //@NotBlank(message = "物流公司不能为空", groups = { AddGroup.class, EditGroup.class })
    private String logisticsCom;

    /**
     * 发货时间
     */
    private Date deliveryTime;

    /**
     * 是否主动
     */
    private String isActive;

    /**
     * 签收时间
     */
    private Date receiveTime;

    private String orderStatus;
    private List<String> orderStatusList;

    private Long platformKey;

    private List<Long> numbers;

    private String sendEmail;

    private List<Long> productIds;

    private String queryStr;

    private Long supplier;
}
