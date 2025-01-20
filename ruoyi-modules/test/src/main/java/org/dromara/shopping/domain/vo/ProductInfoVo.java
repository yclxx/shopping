package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;


/**
 * 商品拓展视图对象
 *
 * @author yzg
 * @date 2023-05-15
 */
@Data
@ExcelIgnoreUnannotated
public class ProductInfoVo {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

    /**
     * 产品标题
     */
    @ExcelProperty(value = "产品标题")
    private String title;

    /**
     * 产品主图
     */
    @ExcelProperty(value = "产品主图")
    private String mainPicture;

    /**
     * 售卖起始时间（秒）
     */
    @ExcelProperty(value = "售卖起始时间", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "秒=")
    private String saleStartTime;

    /**
     * 售卖结束时间（秒）
     */
    @ExcelProperty(value = "售卖结束时间", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "秒=")
    private String saleEndTime;

    /**
     * 第三方产品id
     */
    @ExcelProperty(value = "第三方产品id")
    private String itemId;

    /**
     * 第三方产品结算价格
     */
    //@NotBlank(message = "第三方产品id不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal itemPrice;
    /**
     * 折扣
     */
    @ExcelProperty(value = "折扣")
    private String discount;

    /**
     * 产品库存
     */
    @ExcelProperty(value = "产品库存")
    private Long stock;

    /**
     * 销量
     */
    @ExcelProperty(value = "销量")
    private Long totalSales;

    /**
     * 适用门店数量（city_id不为空则返回当前城市可用门店数，否则返回全部可用门店数）
     */
    @ExcelProperty(value = "适用门店数量", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "c=ity_id不为空则返回当前城市可用门店数，否则返回全部可用门店数")
    private Long applyShopCount;

    /**
     * 使用次数
     */
    @ExcelProperty(value = "使用次数")
    private Long useTimes;

    /**
     * 佣金比例
     */
    @ExcelProperty(value = "佣金比例")
    private String commissionRate;

    /**
     * 活动价（分）
     */
    @ExcelProperty(value = "活动价", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "分=")
    private BigDecimal activityPriceCent;

    /**
     * 原价（分）
     */
    @ExcelProperty(value = "原价", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "分=")
    private BigDecimal originalPriceCent;

    /**
     * 套餐内容
     */
    @ExcelProperty(value = "套餐内容")
    private String itemContentGroup;

    /**
     * 套餐图片
     */
    @ExcelProperty(value = "套餐图片")
    private String itemContentImage;

    /**
     * 购买须知
     */
    @ExcelProperty(value = "购买须知")
    private String itemBuyNote;

    /**
     * 补充说明
     */
    @ExcelProperty(value = "补充说明")
    private String reserveDesc;

    /**
     * 商家须知
     */
    @ExcelProperty(value = "商家须知")
    private String shopInfo;

    /**
     * 使用须知
     */
    @ExcelProperty(value = "使用须知")
    private String useNote;

    /**
     * 使用时间
     */
    @ExcelProperty(value = "使用时间")
    private String ticketTimeRule;

    /**
     * 人数限制
     */
    @ExcelProperty(value = "人数限制")
    private Long userNumLimited;

    /**
     * 相对有效期，单位：天
     */
    @ExcelProperty(value = "相对有效期，单位：天")
    private String period;

    /**
     * 限购数量 -1不限
     */
    @ExcelProperty(value = "限购数量 -1不限")
    private Long buyLimit;

    /**
     * 品牌
     */
    @ExcelProperty(value = "品牌")
    private String brandName;

    private Boolean shopAll;
    private Boolean overdue;
    private Boolean anyTime;
    private BigDecimal leastPrice;
    private BigDecimal reducePrice;

}
