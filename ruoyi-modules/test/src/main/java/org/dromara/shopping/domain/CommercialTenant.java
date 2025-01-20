package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商户对象 t_commercial_tenant
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_commercial_tenant")
public class CommercialTenant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商户ID
     */
    @TableId(value = "commercial_tenant_id")
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
    /**
     * 0 暂存 1正式
     */
    private String isCache;
    /**
     * 省 市 区 中间用逗号隔开
     */
    private String city;
    /**
     * 区城市编码
     */
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
     * 状态（0正常 1停用）
     */
    private String status;
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
    private Long brandId;
    /**
     * 第三方品牌ID（银联）
     */
    private String ylBrandId;
    /**
     * 排序：从小到大
     */
    private Long sort;
    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

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
}
