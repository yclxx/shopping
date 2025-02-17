package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.shopping.domain.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实物订单视图对象
 *
 * @author yzg
 * @date 2024-03-20
 */
@Data
@ExcelIgnoreUnannotated
public class OrderGoodsVo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单编号")
    private Long number;

    /**
     * 产品id
     */
    //@ExcelProperty(value = "产品id")
    private Long productId;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;

    private String productImg;

    /**
     * 购买金额
     */
    //@ExcelProperty(value = "购买金额")
    private BigDecimal price;

    /**
     * 结算金额
     */
    //@ExcelProperty(value = "结算金额")
    private BigDecimal sellPrice;

    /**
     * 订单表中 productPrice 产品售价
     */
    private BigDecimal productPrice;
    /**
     * 订单表中供应商结算价
     */
    private BigDecimal itemPrice;

    /**
     * 购买数量
     */
    //@ExcelProperty(value = "购买数量")
    private Long count;

    /**
     * 购买人手机号
     */
    //@ExcelProperty(value = "购买人手机号")
    private String mobile;

    /**
     * 用户地址id
     */
    //@ExcelProperty(value = "用户地址id")
    private Long userAddressId;

    /**
     * 订单状态
     */
    @ExcelProperty(value = "订单状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_status")
    private String orderStatus;

    /**
     * 联系人
     */
    @ExcelProperty(value = "联系人")
    private String name;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String tel;

    /**
     * 地址中文，省市县等，用空格隔开
     */
    //@ExcelProperty(value = "地址中文，省市县等，用空格隔开")
    private String address;

    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    @ExcelProperty(value = "收货地址")
    //@ExcelDictFormat(readConverterExp = "街=道门牌号啥的，全地址需要address+address_info")
    private String addressInfo;

    /**
     * 邮费
     */
    //@ExcelProperty(value = "邮费")
    private BigDecimal ticketPostage;

    /**
     * 物流单号
     */
    @ExcelProperty(value = "快递单号")
    private String logistics;

    /**
     * 物流状态
     */
    //@ExcelProperty(value = "物流状态", converter = ExcelDictConvert.class)
    //@ExcelDictFormat(dictType = "t_logistics_status")
    private String logisticsStatus;

    /**
     * 物流公司
     */
    @ExcelProperty(value = "快递公司")
    private String logisticsCom;

    private String platformKey;

    private Order order;

    private Date createTime;

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

}
