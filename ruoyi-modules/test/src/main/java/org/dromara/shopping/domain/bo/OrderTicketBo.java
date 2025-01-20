package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 演出票订单业务对象
 *
 * @author yzg
 * @date 2023-09-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderTicketBo extends BaseEntity {

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = {EditGroup.class})
    private Long number;

    /**
     * 产品id
     */
    @NotNull(message = "产品id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long productId;

    /**
     * 场次id
     */
    @NotNull(message = "场次id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long sessionId;

    /**
     * 票种id
     */
    @NotNull(message = "票种id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long lineId;

    /**
     * 购买人手机号
     */
    private String mobile;
    /**
     * 观影时间
     */
    @NotNull(message = "观影时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date ticketTime;
    /**
     * 预约时间
     */
    private String reservation;
    /**
     * 原价
     */
    @NotNull(message = "原价不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal price;

    /**
     * 售价
     */
    @NotNull(message = "售价不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal sellPrice;

    /**
     * 购买数量
     */
    @NotNull(message = "购买数量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long count;

    /**
     * 用户地址id
     */
    @NotNull(message = "用户地址id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long userAddressId;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = {AddGroup.class, EditGroup.class})
    private String tel;

    /**
     * 地址中文，省市县等，用空格隔开
     */
    @NotBlank(message = "地址中文，省市县等，用空格隔开不能为空", groups = {AddGroup.class, EditGroup.class})
    private String address;

    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    @NotBlank(message = "详细地址（街道门牌号啥的，全地址需要address+address_info）不能为空", groups = {AddGroup.class, EditGroup.class})
    private String addressInfo;

    /**
     * 门店id
     */
    @NotNull(message = "门店id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long shopId;

    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String shopName;

    /**
     * 门店地址
     */
    @NotBlank(message = "门店地址不能为空", groups = {AddGroup.class, EditGroup.class})
    private String shopAddress;

    /**
     * 是否不支持退款
     */
    private String ticketNonsupport;
    /**
     * 是否电子发票
     */
    private String ticketInvoice;
    /**
     * 是否过期退
     */
    private String ticketExpired;
    /**
     * 随时退
     */
    private String ticketAnyTime;
    /**
     * 选座方式
     */
    private String ticketChooseSeat;
    /**
     * 票形式
     */
    private String ticketForm;
    /**
     * 快递方式
     */
    private String ticketPostWay;
    /**
     * 邮费
     */
    private BigDecimal ticketPostage;
    /**
     * 物流单号
     */
    private String logistics;
    /**
     * 状态
     */
    private String logisticsStatus;
    /**
     * 物流公司
     */
    private String logisticsCom;

    private String codeNo;

    private String status;
    private String productName;
    private String sessionName;
    private String lineName;
}
