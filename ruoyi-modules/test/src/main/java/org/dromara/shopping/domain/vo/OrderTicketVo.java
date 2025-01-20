package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnore;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.zlyyh.domain.Order;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 演出票订单视图对象
 *
 * @author yzg
 * @date 2023-09-22
 */
@Data
@ExcelIgnoreUnannotated
public class OrderTicketVo {

    private static final long serialVersionUID = 1L;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 产品id
     */
    @ExcelIgnore
    private Long productId;
    @ExcelProperty(value = "商品")
    private String productName;

    /**
     * 场次id
     */
    @ExcelIgnore
    private Long sessionId;
    @ExcelProperty(value = "场次")
    private String sessionName;

    /**
     * 票种id
     */
    @ExcelIgnore
    private Long lineId;
    @ExcelProperty(value = "票档")
    private String lineName;
    @ExcelIgnore
    private String status;
    @ExcelProperty(value = "手机号")
    private String mobile;
    /**
     * 观影时间
     */
    @ExcelProperty(value = "观影时间")
    private Date ticketTime;
    /**
     * 预约时间
     */
    @ExcelProperty(value = "预约时间")
    private String reservation;

    /**
     * 原价
     */
    @ExcelIgnore
    private BigDecimal price;

    /**
     * 售价
     */
    @ExcelProperty(value = "售价")
    private BigDecimal sellPrice;

    /**
     * 购买数量
     */
    @ExcelProperty(value = "购买数量")
    private Long count;

    /**
     * 用户地址id
     */
    @ExcelIgnore
    private Long userAddressId;

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
    @ExcelProperty(value = "省市县")
    private String address;

    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    @ExcelProperty(value = "详细地址")
    private String addressInfo;

    /**
     * 门店id
     */
    @ExcelIgnore
    private Long shopId;

    /**
     * 门店名称
     */
    @ExcelIgnore
    private String shopName;

    /**
     * 门店地址
     */
    @ExcelIgnore
    private String shopAddress;
    /**
     * 是否不支持退款
     */
    @ExcelIgnore
    private String ticketNonsupport;
    /**
     * 是否电子发票
     */
    @ExcelIgnore
    private String ticketInvoice;
    /**
     * 是否过期退
     */
    @ExcelIgnore
    private String ticketExpired;
    /**
     * 随时退
     */
    @ExcelIgnore
    private String ticketAnyTime;
    /**
     * 选座方式
     */
    @ExcelIgnore
    private String ticketChooseSeat;
    /**
     * 票形式
     */
    @ExcelIgnore
    private String ticketForm;
    /**
     * 快递方式
     */
    @ExcelIgnore
    private String ticketPostWay;
    /**
     * 邮费
     */
    @ExcelIgnore
    private BigDecimal ticketPostage;
    /**
     * 物流单号
     */
    @ExcelIgnore
    private String logistics;
    /**
     * 状态
     */
    @ExcelIgnore
    private String logisticsStatus;
    /**
     * 物流公司
     */
    @ExcelIgnore
    private String logisticsCom;

    @ExcelIgnore
    private Order order;
    /**
     * 观影人信息
     */
    @ExcelIgnore
    private List<OrderIdcardVo> orderIdCardVos;
    /**
     * 券码
     */
    @ExcelIgnore
    private List<CodeVo> codeVos;
}
