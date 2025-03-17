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
 * 演出票业务对象
 *
 * @author yzg
 * @date 2023-09-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTicketBo extends BaseEntity {

    /**
     * 演出票id
     */
    @NotNull(message = "演出票id不能为空", groups = {EditGroup.class})
    private Long ticketId;

    /**
     * 产品id
     */
    @NotNull(message = "产品id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long productId;
    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketStatus;
    /**
     * 票种
     */
    @NotBlank(message = "票种不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketTicketType;
    /**
     * 选座方式
     */
    @NotBlank(message = "选座方式不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketChooseSeat;
    /**
     * 票形式
     */
    @NotBlank(message = "票形式不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketForm;
    /**
     * 是否需要身份信息
     */
    @NotBlank(message = "身份信息不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketCard;
    /**
     * 快递方式
     */
    @NotBlank(message = "快递方式不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketPostWay;
    /**
     * 是否不支持退款
     */
    @NotBlank(message = "不支持退款不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketNonsupport;
    /**
     * 是否电子发票
     */
    @NotBlank(message = "电子发票不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketInvoice;
    /**
     * 是否过期退
     */
    @NotBlank(message = "过期退不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketExpired;
    /**
     * 是否随时退
     */
    @NotBlank(message = "随时退不能为空", groups = {AddGroup.class, EditGroup.class})
    private String ticketAnyTime;

    /**
     * 邮费
     */
    private BigDecimal ticketPostage;
    /**
     * 须知
     */
    private String ticketNotice;
}
