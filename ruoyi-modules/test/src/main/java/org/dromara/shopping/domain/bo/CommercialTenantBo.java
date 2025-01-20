package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.AppEditGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CommercialTenantBo extends BaseEntity {

    /**
     * 商户ID
     */
    @NotNull(message = "商户ID不能为空", groups = {EditGroup.class})
    private Long commercialTenantId;

    /**
     * 商户名称
     */
    private String commercialTenantName;

    /**
     * 商户logo
     */
    private String commercialTenantImg;
    /**
     * 商户简称
     */
    @NotBlank(message = "商户名称不能为空", groups = {AppEditGroup.class, AddGroup.class, EditGroup.class})
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
    @NotBlank(message = "管理员手机号不能为空", groups = {AppEditGroup.class})
    private String adminMobile;

    private String isCache;

    private String city;

    private String citycode;

    /**
     * 标签,英文逗号隔开
     */
    private String tags;

    /**
     * 生效时间
     */
    private Date startTime;

    /**
     * 失效时间
     */
    private Date endTime;

    /**
     * 是否显示在首页（0显示 1不显示）
     */
    private String indexShow;
    /**
     * 是否共享
     */
    private String isShare;
    /**
     * 供应商
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
     * 活动类型
     */
    private String activity;
    /**
     * 活动商户
     */
    private String activityNature;
    /**
     * 第三方品牌ID
     */
    @NotNull(message = "品牌不能为空", groups = {AppEditGroup.class})
    private Long brandId;

    /**
     * 第三方品牌ID（银联）
     */
    private String ylBrandId;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 排序：从小到大
     */
    private Long sort;

    /**
     * 平台标识
     */
    //@NotNull(message = "平台标识不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long platformKey;

    private Long categoryId;

    private Long[] productIds;
    private BigDecimal latitude;

    private BigDecimal longitude;

    private Long[] categoryIds;

    private String weekDate;

    private Long shopId;

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
     * 传入的商品id 用于商品详情内的列表展示
     */
    private Long productId;

    private String type;
}
