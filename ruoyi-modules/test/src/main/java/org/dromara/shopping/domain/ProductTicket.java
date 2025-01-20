package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 演出票对象 t_product_ticket
 *
 * @author yzg
 * @date 2023-09-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_ticket")
public class ProductTicket extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 演出票id
     */
    @TableId(value = "ticket_id")
    private Long ticketId;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 状态
     */
    private String ticketStatus;
    /**
     * 票种
     */
    private String ticketTicketType;
    /**
     * 选座方式
     */
    private String ticketChooseSeat;
    /**
     * 票形式
     */
    private String ticketForm;
    /**
     * 是否需要身份信息
     */
    private String ticketCard;
    /**
     * 快递方式
     */
    private String ticketPostWay;
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
     * 邮费
     */
    private BigDecimal ticketPostage;
    /**
     * 须知
     */
    private String ticketNotice;
}
