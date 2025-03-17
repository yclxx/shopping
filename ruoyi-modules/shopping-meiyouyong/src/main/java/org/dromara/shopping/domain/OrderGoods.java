package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 实物订单对象 t_order_goods
 *
 * @author yzg
 * @date 2024-03-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_goods")
public class OrderGoods extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 订单号
     */
    @TableId(value = "number")
    private Long number;
    /**
     * 产品id
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 购买金额
     */
    private BigDecimal price;
    /**
     * 结算金额
     */
    private BigDecimal sellPrice;
    /**
     * 购买数量
     */
    private Long count;
    /**
     * 购买人手机号
     */
    private String mobile;
    /**
     * 用户地址id
     */
    private Long userAddressId;
    /**
     * 联系人
     */
    private String name;
    /**
     * 联系电话
     */
    private String tel;
    /**
     * 地址中文，省市县等，用空格隔开
     */
    private String address;
    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    private String addressInfo;
    /**
     * 邮费
     */
    private BigDecimal ticketPostage;
    /**
     * 物流单号
     */
    private String logistics;
    /**
     * 物流状态
     */
    private String logisticsStatus;
    /**
     * 物流公司
     */
    private String logisticsCom;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;
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
