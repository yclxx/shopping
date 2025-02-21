package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

/**
 * 商品视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class ProductVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long productId;

    /**
     * 外部产品ID
     */
    @ExcelProperty(value = "外部产品ID")
    private String externalProductId;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 简称
     */
    @ExcelProperty(value = "简称")
    private String productAbbreviation;

    /**
     * 副标题
     */
    @ExcelProperty(value = "副标题")
    private String productSubhead;

    /**
     * 商品图片
     */
    @ExcelProperty(value = "商品图片")
    private String productImg;

    /**
     * 商品归属：0-商城内部（自己开发的页面），1-宣传展示（外部提供的页面）
     */
    @ExcelProperty(value = "商品归属", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_affiliation")
    private String productAffiliation;

    /**
     * 商品类型：0-优惠券，2-优惠活动
     */
    @ExcelProperty(value = "商品类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_type")
    private String productType;

    /**
     * 领取方式：0-免费领取，1-付费领取，2-积点兑换
     */
    @ExcelProperty(value = "领取方式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_pickup_method")
    private String pickupMethod;

    /**
     * 是否显示市场价格：0-不显示，1-显示
     */
    @ExcelProperty(value = "是否显示市场价格", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_show_original_amount")
    private String showOriginalAmount;

    /**
     * 市场价格
     */
    @ExcelProperty(value = "市场价格")
    private BigDecimal originalAmount;

    /**
     * 售卖价格
     */
    @ExcelProperty(value = "售卖价格")
    private BigDecimal sellAmount;

    /**
     * 62会员价格
     */
    @ExcelProperty(value = "62会员价格")
    private BigDecimal vipUpAmount;

    /**
     * 权益会员价格
     */
    @ExcelProperty(value = "权益会员价格")
    private BigDecimal vipAmount;

    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转
     */
    @ExcelProperty(value = "跳转类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_to_type")
    private String toType;

    /**
     * 小程序ID
     */
    @ExcelProperty(value = "小程序ID")
    private String appId;

    /**
     * 页面地址
     */
    @ExcelProperty(value = "页面地址")
    private String url;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_status")
    private String status;

    /**
     * 展示开始时间
     */
    @ExcelProperty(value = "展示开始时间")
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    @ExcelProperty(value = "展示结束时间")
    private Date showEndDate;

    /**
     * 领取开始时间
     */
    @ExcelProperty(value = "领取开始时间")
    private Date sellStartDate;

    /**
     * 领取结束时间
     */
    @ExcelProperty(value = "领取结束时间")
    private Date sellEndDate;

    /**
     * 上架时间
     */
    @ExcelProperty(value = "上架时间")
    private Date createTime;

    /**
     * 指定周几: 0-不指定，1-指定周几
     */
    @ExcelProperty(value = "指定周几", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_assign_date")
    private String assignDate;

    /**
     * 周几能领：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    @ExcelProperty(value = "周几能领")
    private String weekDate;

    /**
     * 几点开始领取,几点结束,格式：HH:mm:ss-HH:mm:ss
     */
    @ExcelProperty(value = "几点开始领取,几点结束")
    private String sellTime;

    /**
     * 总数量，-1为不限制抢购数量
     */
    @ExcelProperty(value = "总数量")
    private Long totalCount;

    /**
     * 每月数量，-1为不限制抢购数量
     */
    @ExcelProperty(value = "每月数量")
    private Long monthCount;

    /**
     * 每周数量，-1为不限制抢购数量
     */
    @ExcelProperty(value = "每周数量")
    private Long weekCount;

    /**
     * 每日数量，-1为不限制抢购数量
     */
    @ExcelProperty(value = "每日数量")
    private Long dayCount;

    /**
     * 每日同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "每日同一用户限领数量")
    private Long dayUserCount;

    /**
     * 每周同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "每周同一用户限领数量")
    private Long weekUserCount;

    /**
     * 当月同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "当月同一用户限领数量")
    private Long monthUserCount;

    /**
     * 活动周期同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "活动周期同一用户限领数量")
    private Long totalUserCount;

    /**
     * 商品详情
     */
    @ExcelProperty(value = "商品详情")
    private String description;

    /**
     * 活动提供方logo
     */
    @ExcelProperty(value = "活动提供方logo")
    private String providerLogo;

    /**
     * 活动提供方名称
     */
    @ExcelProperty(value = "活动提供方名称")
    private String providerName;

    /**
     * 标签,英文逗号隔开(已废弃，暂时保留)
     */
    @ExcelProperty(value = "标签")
    private String tags;

    private List<Long> tagsList;

    /**
     * 展示城市：ALL-全部、否则城市行政区号，多个之间用英文逗号隔开
     */
    @ExcelProperty(value = "展示城市")
    private String showCity;

    /**
     * 支付商户ID
     */
    @ExcelProperty(value = "支付商户ID")
    private Long merchantId;

    /**
     * 门店组ID
     */
    @ExcelProperty(value = "门店组ID")
    private Long shopGroupId;
    /**
     * 门店ID
     */
    @ExcelProperty(value = "门店ID")
    private String shopId;

    /**
     * 按钮名称
     */
    @ExcelProperty(value = "按钮名称")
    private String btnText;

    /**
     * 分享标题
     */
    @ExcelProperty(value = "分享标题")
    private String shareTitle;

    /**
     * 分享描述
     */
    @ExcelProperty(value = "分享描述")
    private String shareName;

    /**
     * 分享图片
     */
    @ExcelProperty(value = "分享图片")
    private String shareImage;

    /**
     * 分享海报
     */
    private String sharePoster;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    private String categoryId;

    private String commercialTenantId;

    private ProductInfoVo productInfoVo;

    private ProductYcfVo productYcfVo;

    private SupplierVo supplierVo;

    /**
     * 发放金额
     */
    private BigDecimal externalProductSendValue;

    /**
     * 是否可被搜索
     */
    private String search;
    /**
     * 是否能被搜索
     */
    private String searchStatus;

    /**
     * 可购买用户：0-所有用户，1-62VIP用户
     */
    private String payUser;

    /**
     * 是否显示在首页
     */
    private String showIndex;

    /**
     * 发券账号类型：0-手机号，1-openId
     */
    private String sendAccountType;

    private String cusRefund;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

    /**
     * 银联分销：0-不通过，1-通过
     */
    private String unionPay;

    /**
     * 银联产品编号
     */
    private String unionProductId;

    /**
     * 可使用开始时间
     */
    private Date usedStartTime;

    /**
     * 可使用结束时间
     */
    private Date usedEndTime;

    /**
     * 是否校验用户购买城市
     */
    private String checkPayCity;
    /**
     * 支持优惠券
     */
    private String isCoupon;
    /**
     * 是否分享
     */
    private String isShare;
    /**
     * 供应商
     */
    private String supplier;
    /**
     * 支持端
     */
    private String supportChannel;
    /**
     * 单次购买上限(购物车加号上限)
     */
    private Long lineUpperLimit;

    /**
     * 支持过期退（t_product_info）参数
     */
    private Boolean overdue;

    private ProductTicketVo ticket;

    private List<ProductTicketSessionVo> ticketSession;

    private ShopVo shopVo;

    private String payBankType;

    private String productSmallImg;
    private String productTypeImg;

    private String isPoup;

    private String poupText;

    private String sharePermission;
    private String shareAmountType;
    private BigDecimal shareOneAmount;
    private BigDecimal shareTwoAmount;

    private String couponTip;
    private String warnMessage;
    private String warnEmail;
    private Long warnCount;
    private String autoRefund;

    /**
     * 机构账户代码
     */
    private String institutionAccountId;

    /**
     * 第三方机构产品编号
     */
    private String institutionProductId;
    /**
     * 下单是否可以更改手机号 0-是 1-否
     */
    private String changeMobile;

    /**
     * 是否可以加入购物车0否 1是 （为空也可加入购物车）
     */
    private String shoppingCart;

    /**
     * 是否开启每月专属活动日
     */
    private String startLuckyDay;
    /**
     * 专属活动日期 用;隔开
     */
    private String luckyDay;
    /**
     * 专属活动日商品日限量
     */
    private Long luckyDayCount;
    /**
     * 邮费（单位元）
     */
    private BigDecimal postage;

    /**
     * 是否需要邮费，0-不需要，1-需要
     */
    private String isPost;

    /**
     * 折扣
     */
    private String discount;
    /**
     * 是否支持分期：0-不支持，1-支持
     */
    private String installmentOpen;
    /**
     * 限制只能分期支付：0-不限制，1-限制
     */
    private String installmentLimit;
    /**
     * 分期银行，多个之间英文逗号隔开
     */
    private String installmentBank;
    /**
     * 分期期数，多个之间英文逗号隔开
     */
    private String installmentDateType;
    /**
     * 分期地区，多个之间英文逗号隔开
     */
    private String installmentCity;
    /**
     * 退款说明
     */
    private String refundRemark;

    /**
     * 结算价格
     */
    private BigDecimal itemPrice;

    /**
     * 房号id 目前仅太目 三方id：后面的值
     */
    private String roomProductId;
    /**
     * 囤券id 目前仅太目 三方id前面的值
     */
    private String exMainProductId;
    /**
     * 用户预约，0-普通预约，1-强预约 2-非预约商品
     */
    private String isAppointment;
    /**
     * 用户退票，0-不支持，1-支持
     */
    private String isTicket;
    /**
     * 预约提示
     */
    private String appointmentRemark;

    /**
     * 是否人为修改
     */
    private String manualOperation;

    /**
     * 是否拼团
     */
    private String groupBuying;

    /**
     * 真实用户分销奖励金额，不论是比例还是啥，统一换算成金额，单位元
     */
    private BigDecimal realShareOneAmount;
    /**
     * 真实用户分销奖励金额，不论是比例还是啥，统一换算成金额，单位元
     */
    private BigDecimal realShareTwoAmount;

    /**
     * 拼团价格
     */
    private BigDecimal groupBuyingAmount;

    public void calculateDiscount() {
        if (null != this.sellAmount && null != this.originalAmount && this.originalAmount.compareTo(BigDecimal.ZERO) > 0 && this.sellAmount.compareTo(BigDecimal.ZERO) > 0 && this.originalAmount.compareTo(this.sellAmount) > 0) {
            BigDecimal divide = this.sellAmount.divide(this.originalAmount, 2, RoundingMode.HALF_UP);
            divide = divide.multiply(new BigDecimal("10")).setScale(1, RoundingMode.HALF_UP);
            this.discount = divide.stripTrailingZeros().toPlainString();
        }
    }

}
