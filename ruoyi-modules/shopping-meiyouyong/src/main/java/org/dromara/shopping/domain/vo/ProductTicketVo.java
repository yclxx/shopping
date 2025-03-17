package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 演出票视图对象
 *
 * @author yzg
 * @date 2023-09-11
 */
@Data
@ExcelIgnoreUnannotated
public class ProductTicketVo {

    private static final long serialVersionUID = 1L;

    /**
     * 演出票id
     */
    @ExcelProperty(value = "演出票id")
    private Long ticketId;

    /**
     * 产品id
     */
    @ExcelProperty(value = "产品id")
    private Long productId;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String ticketStatus;
    /**
     * 票种
     */
    @ExcelProperty(value = "票种")
    private String ticketTicketType;
    /**
     * 选座方式
     */
    @ExcelProperty(value = "选座方式")
    private String ticketChooseSeat;

    /**
     * 票形式
     */
    @ExcelProperty(value = "票形式")
    private String ticketForm;

    /**
     * 是否需要身份信息
     */
    @ExcelProperty(value = "是否需要身份信息")
    private String ticketCard;
    /**
     * 快递方式
     */
    @ExcelProperty(value = "快递方式")
    private String ticketPostWay;
    /**
     * 是否不支持退款
     */
    @ExcelProperty(value = "不支持退款")
    private String ticketNonsupport;
    /**
     * 是否电子发票
     */
    @ExcelProperty(value = "电子发票")
    private String ticketInvoice;
    /**
     * 是否过期退
     */
    @ExcelProperty(value = "过期退")
    private String ticketExpired;
    /**
     * 是否随时退
     */
    @ExcelProperty(value = "随时退")
    private String ticketAnyTime;
    /**
     * 邮费
     */
    @ExcelProperty(value = "邮费")
    private BigDecimal ticketPostage;
    /**
     * 须知
     */
    @ExcelProperty(value = "须知")
    private String ticketNotice;
}
