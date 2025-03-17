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
 * 要出发产品对象 t_product_ycf
 *
 * @author yzg
 * @date 2024-06-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_ycf")
public class ProductYcf extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 价格库存id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 第三方产品编号（可以理解为套餐ID）
     */
    private String itemId;
    /**
     * 产品类型:" 0:套餐; 1:单房; 2:单票; 3:单餐"
     */
    private String productType;
    /**
     * 是否为票券:0：非票券，1：票券类为期票产品，使用日期范围为一个周期
     */
    private String isTicketVoucher;
    /**
     * 票券使用开始日期（2020-01-01 16:30:00，票券产品必填）
     */
    private String ticketVoucherDateBegin;
    /**
     * 票券使用结束日期（2020-12-31 22:00:00，票券产品必填）
     */
    private String ticketVoucherDateEnd;
    /**
     * 酒景编号（可以理解为产品ID，比如说某个酒店产品，或者某个景区产品）
     */
    private String poiId;
    /**
     * 提前多少分钟预订:如必须提前一天的下午4点前购买本产品，则为1*24*60+16*60+0=2400，如不限制为0
     */
    private Long bookAheadMin;
    /**
     * 产品最低购买份数:"0为不限制1为最少购买一份"
     */
    private Long minNum;
    /**
     * 产品最多购买份数:"	0为不限制1为最多购买一份"
     */
    private Long maxNum;
    /**
     * 产品最低购买晚数:"	0为不限制1为最少购买一晚"
     */
    private Long minNight;
    /**
     * 产品最多购买晚数:"	0为不限制1为最少购买一晚"
     */
    private Long maxNight;
    /**
     * 产品市场价
     */
    private BigDecimal marketPrice;
    /**
     * 产品上架时间
     */
    private Date startDate;
    /**
     * 产品下架时间
     */
    private Date endDate;
    /**
     * 退改类型:1：可退改；2：不可退改；3：条件退
     */
    private String refundType;
    /**
     * 使用日期前或使用日期后:"0：使用日期前; 1：使用日期后"
     */
    private String advanceOrDelayType;
    /**
     * 退款提前时间:"只有refundType为3的时候才有用"
     */
    private Long refundPreminute;
    /**
     * 退改说明（纯文本）
     */
    private String refundNote;
    /**
     * 房型必选择数:"不支持M选N套餐，忽略该字段；房资源组内有多个房型时，最小的选择数量"
     */
    private Long roomChoiceNum;
    /**
     * 房型可选择数:"不支持M选N套餐，忽略该字段；房资源组内有多个房型时，可供选择的数量，如二选一的房，RoomChoiceNum为1，RoomOptionNum为2"
     */
    private Long roomOptionNum;
    /**
     * 门票必选择数:"不支持M选N套餐，忽略该字段；门票资源组内有多个票种时，最小的选择数量"
     */
    private Long ticketChoiceNum;
    /**
     * 门票可选择数:"不支持M选N套餐，忽略该字段；门票资源组内有多个票种时，可供选择的数量，如二选一的票，TicketChoiceNum为1，TicketOptionNum为2"
     */
    private Long ticketOptionNum;
    /**
     * 1-普通票,2-成人票,3-亲子票,4-家庭票,5-情侣票,6-双人票,7-儿童票,8,老人票,9-学生票,10-军人票,11-教师票,12-残疾票,13-团体票,14-特殊票,15-优惠套票
     */
    private String ticketType;
    /**
     * 取票方式:产品含门票才有，例如：“请在取票机凭取票凭证码取票，或刷二代身份证取票。”
     */
    private String getTicketMode;
    /**
     * 餐饮必选择数:"不支持M选N套餐，忽略该字段；餐饮资源组内有多个餐饮券时，最小的选择数量"
     */
    private Long foodChoiceNum;
    /**
     * 餐饮可选择数:"不支持M选N套餐，忽略该字段；餐饮券资源组内有多个餐饮券时，可供选择的数量，如二选一的券，FoodChoiceNum为1，FoddOptionNum为2"
     */
    private Long foodOptionNum;
    /**
     * 是否支持全网预售:0不支持，1支持
     */
    private String isGlobalSale;
    /**
     * 房型详情
     */
    private String roomMessage;
    /**
     * 票券详情
     */
    private String ticketMessage;
    /**
     * 美食详情
     */
    private String foodMessage;
    /**
     * 规则详情
     */
    private String bookRuleMessage;
    /**
     * 预售使用开始时间,例如：2018-02-27 00:00:00
     */
    private Date orderDateBegin;
    /**
     * 预售使用结束时间,例如：2018-04-28 00:00:00
     */
    private Date orderDateEnd;
    /**
     * 预售说明
     */
    private String preSaleDescription;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
