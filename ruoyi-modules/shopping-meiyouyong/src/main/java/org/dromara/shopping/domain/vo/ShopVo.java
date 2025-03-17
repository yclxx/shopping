package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 门店视图对象
 *
 * @author yzgnet
 * @date 2023-09-16
 */
@Data
@ExcelIgnoreUnannotated
public class ShopVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long shopId;

    /**
     * 商户ID
     */
    @ExcelProperty(value = "商户ID")
    private Long commercialTenantId;

    /**
     * 商户名称
     */
    @ExcelProperty(value = "商户名称")
    private String commercialTenantName;

    /**
     * 门店名称
     */
    @ExcelProperty(value = "门店名称")
    private String shopName;

    /**
     * 门店电话
     */
    @ExcelProperty(value = "门店电话")
    private String shopTel;

    /**
     * 营业时间
     */
    @ExcelProperty(value = "营业时间")
    private String businessHours;

    /**
     * 门店地址
     */
    @ExcelProperty(value = "门店地址")
    private String address;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_shop_status")
    private String status;

    /**
     * 结构化地址信息省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码
     */
    @ExcelProperty(value = "结构化地址信息")
    private String formattedAddress;

    /**
     * 省份名
     */
    @ExcelProperty(value = "省份名")
    private String province;

    /**
     * 城市名
     */
    @ExcelProperty(value = "城市名")
    private String city;

    /**
     * 地址所在区
     */
    @ExcelProperty(value = "地址所在区")
    private String district;

    /**
     * 省份编码（行政区号例如：浙江330000）
     */
    @ExcelProperty(value = "省份编码")
    private String procode;

    /**
     * 城市编码（行政区号例如：杭州330100）
     */
    @ExcelProperty(value = "城市编码")
    private String citycode;

    /**
     * 区域编码（行政区号例如：拱墅区330105）
     */
    @ExcelProperty(value = "区域编码")
    private String adcode;

    /**
     * 经度,基于高德地图
     */
    @ExcelProperty(value = "经度")
    private BigDecimal longitude;

    /**
     * 纬度,基于高德地图
     */
    @ExcelProperty(value = "纬度")
    private BigDecimal latitude;

    /**
     * 三方门店id
     */
    @ExcelProperty(value = "供应商门店id")
    private String supplierShopId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

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
     * 指定周几: 0-不指定，1-指定周几
     */
    @ExcelProperty(value = "指定周几: 0-不指定，1-指定周几")
    private String assignDate;

    /**
     * 周几展示：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    @ExcelProperty(value = "周几展示：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开")
    private String weekDate;

    /**
     * 几点开始展示,几点结束,格式：HH:mm:ss-HH:mm:ss
     */
    @ExcelProperty(value = "几点开始展示,几点结束,格式：HH:mm:ss-HH:mm:ss")
    private String sellTime;

    /**
     * 节假日是否营业
     */
    @ExcelProperty(value = "节假日是否营业")
    private Boolean holiday;

    /**
     * 门店图片
     */
    @ExcelProperty(value = "门店图片")
    private String shopImgs;

    /**
     * 门店logo
     */
    @ExcelProperty(value = "门店logo")
    private String shopLogo;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序：从小到大")
    private Long sort;

    /**
     * 标签，多个之间用英文逗号隔开
     */
    @ExcelProperty(value = "标签，多个之间用英文逗号隔开")
    private String shopTags;

    /**
     * 门店类型：0-餐饮，1-便利店，2-茶饮，3-酒店，4-景点，5-演出，6-电影院
     */
    @ExcelProperty(value = "门店类型：0-餐饮，1-便利店，2-茶饮，3-酒店，4-景点，5-演出，6-电影院")
    private String shopType;

    /**
     * 距离 千米
     */
    private BigDecimal distance;

    /**
     * 距离 千米
     */
    private String distanceString;

    /**
     * 商户拓展服务商表
     */
    @ExcelProperty(value = "商户拓展服务商表")
    private Long extensionServiceProviderId;

    /**
     * 推荐类型：0-不推荐，1-商圈精选，2-品牌精选
     */
    @ExcelProperty(value = "推荐类型：0-不推荐，1-商圈精选，2-品牌精选")
    private String pushType;
    /**
     * 是否共享
     */
    private String isShare;
    /**
     * 供应商（与supplierShopId无关系）
     */
    private String supplier;
    /**
     * 营业执照
     */
    private String license;
    /**
     * 性质
     */
    private String nature;
    /**
     * 发票类型
     */
    private String invoice;
    /**
     * 收款账户
     */
    private String account;
    /**
     * 开户行
     */
    private String accountBank;
    /**
     * 收款人
     */
    private String accountPayee;
    /**
     * 活动类型
     */
    private String activity;
    /**
     * 支持端
     */
    private String supportChannel;

    private List<Long> tagsList;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    private Long sysDeptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long sysUserId;

    /**
     * 商品类型
     */
    private String productType;

    private String businessDistrictId;

    List<ProductVo> productList;

    /**
     * 自动匹配商圈
     */
    private String autoBusiness;
    /**
     * 商户是否审核
     */
    private String examineVerifier;

    private String adminMobile;

    private Date createTime;

    private String commercialTenantTitle;

    private List<ShopMerchantVo> shopMerchantVos;
}
