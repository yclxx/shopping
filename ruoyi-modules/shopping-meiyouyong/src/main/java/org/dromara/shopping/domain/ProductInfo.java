package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品拓展对象 t_product_info
 *
 * @author yzg
 * @date 2023-05-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_info")
public class ProductInfo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "product_id")
    private Long productId;
    /**
     * 产品标题
     */
    private String title;
    /**
     * 产品主图
     */
    private String mainPicture;
    /**
     * 售卖起始时间（秒）
     */
    private String saleStartTime;
    /**
     * 售卖结束时间（秒）
     */
    private String saleEndTime;
    /**
     * 第三方产品id
     */
    private String itemId;
    /**
     * 佣金/取货价
     */
    private BigDecimal itemPrice;
    /**
     * 折扣
     */
    private String discount;
    /**
     * 产品库存
     */
    private Long stock;
    /**
     * 销量
     */
    private Long totalSales;
    /**
     * 适用门店数量（city_id不为空则返回当前城市可用门店数，否则返回全部可用门店数）
     */
    private Long applyShopCount;
    /**
     * 使用次数
     */
    private Long useTimes;
    /**
     * 佣金比例
     */
    private String commissionRate;
    /**
     * 活动价（分）
     */
    private BigDecimal activityPriceCent;
    /**
     * 原价（分）
     */
    private BigDecimal originalPriceCent;
    /**
     * 套餐内容
     */
    private String itemContentGroup;
    /**
     * 套餐图片
     */
    private String itemContentImage;
    /**
     * 购买须知
     */
    private String itemBuyNote;
    /**
     * 补充说明
     */
    private String reserveDesc;
    /**
     * 商家须知
     */
    private String shopInfo;
    /**
     * 使用须知
     */
    private String useNote;
    /**
     * 使用时间
     */
    private String ticketTimeRule;
    /**
     * 人数限制
     */
    private Long userNumLimited;
    /**
     * 相对有效期，单位：天
     */
    private String period;
    /**
     * 限购数量 -1不限
     */
    private Long buyLimit;
    /**
     * 品牌
     */
    private String brandName;

    private Boolean shopAll;
    private Boolean overdue;
    private Boolean anyTime;
    private BigDecimal leastPrice;
    private BigDecimal reducePrice;


}
