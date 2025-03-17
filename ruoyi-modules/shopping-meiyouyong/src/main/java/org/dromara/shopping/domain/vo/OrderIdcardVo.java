package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 订单身份信息视图对象
 * @author yzg
 * @date 2023-09-22
 */
@Data
@ExcelIgnoreUnannotated
public class OrderIdcardVo {

    private static final long serialVersionUID = 1L;

    /**
     * 此表ID
     */
    @ExcelProperty(value = "此表ID")
    private Long orderCardId;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 真实姓名
     */
    @ExcelProperty(value = "真实姓名")
    private String name;

    /**
     * 证件类型0-身份证 1-护照 2-港澳台居民居住证 3-户口簿
     */
    @ExcelProperty(value = "证件类型0-身份证 1-护照 2-港澳台居民居住证 3-户口簿")
    private String cardType;

    /**
     * 证件号
     */
    //@Sensitive(strategy = SensitiveStrategy.ID_CARD)
    @ExcelProperty(value = "证件号")
    private String idCard;

    /**
     * 与t_order表相同字段一致
     */
    @ExcelProperty(value = "与t_order表相同字段一致")
    private String orderType;
}
