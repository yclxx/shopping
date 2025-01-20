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
 * 商户视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class CommercialTenantVo implements Serializable {

    private static final long serialVersionUID = 1L;

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
     * 商户logo
     */
    @ExcelProperty(value = "商户logo")
    private String commercialTenantImg;
    /**
     * 商户简称
     */
    private String commercialTenantTitle;
    /**
     * 核销员id
     */
    private Long verifierId;
    /**
     * 管理员
     */
    private String adminName;
    /**
     * 管理员手机号
     */
    private String adminMobile;
    private String isCache;
    private String city;
    private String citycode;
    /**
     * 标签,英文逗号隔开
     */
    @ExcelProperty(value = "标签")
    private String tags;

    /**
     * 生效时间
     */
    @ExcelProperty(value = "生效时间")
    private Date startTime;

    /**
     * 失效时间
     */
    @ExcelProperty(value = "失效时间")
    private Date endTime;

    /**
     * 是否显示在首页（0显示 1不显示）
     */
    @ExcelProperty(value = "是否显示在首页", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_commercial_tenant_index_show")
    private String indexShow;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_commercial_tenant_status")
    private String status;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 是否共享
     */
    @ExcelProperty(value = "是否共享")
    private String isShare;
    /**
     * 供应商
     */
    @ExcelProperty(value = "供应商")
    private String supplier;
    /**
     * 营业执照
     */
    @ExcelProperty(value = "营业执照")
    private String license;
    /**
     * 性质
     */
    @ExcelProperty(value = "性质")
    private String nature;
    /**
     * 发票类型
     */
    @ExcelProperty(value = "发票类型")
    private String invoice;
    /**
     * 收款账户
     */
    @ExcelProperty(value = "收款账户")
    private String account;
    /**
     * 活动类型
     */
    @ExcelProperty(value = "活动类型")
    private String activity;
    /**
     * 活动商户
     */
    private String activityNature;
    /**
     * 第三方品牌ID
     */
    private Long brandId;

    /**
     * 第三方品牌ID（银联）
     */
    private String ylBrandId;

    private List<ProductVo> productCouponList;

    private List<ProductVo> productActivityList;

    private List<ProductVo> productFoodList;

    private ProductVo productVo;

    private ShopVo shopVo;

    private Long[] productIds;

    private Long[] categoryIds;

    /**
     * 距离 千米
     */
    private BigDecimal distance;

    /**
     * 数据来源：0-系统录入，1-API接口
     */
    private String source;

    /**
     * 商户地址
     */
    private String commercialTenantAddress;

    /**
     * 商户姓名
     */
    private String name;

    /**
     * 商户电话
     */
    private String mobile;

    /**
     * 紧急联系人
     */
    private String urgentMobile;

    /**
     * 商户类型
     */
    private String commercialTenantType;

    /**
     * 门店数量
     */
    private Long shopCount;
    /**
     * 未审核门店数量
     */
    private Long unExamineShopCount;

    private String supplierName;

    private CommercialTenantAccountVo accountVo;
}
