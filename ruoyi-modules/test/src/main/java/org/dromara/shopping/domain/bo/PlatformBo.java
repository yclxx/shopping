package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

/**
 * 平台信息业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformBo extends BaseEntity {

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = {EditGroup.class})
    private Long platformKey;

    /**
     * 平台名称
     */
    @NotBlank(message = "平台名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String platformName;

    /**
     * 小程序标题
     */
    private String platformTitle;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;

    /**
     * appId
     */
    private String appId;

    /**
     * 小程序ID
     */
    private String encryptAppId;

    /**
     * 密钥
     */
    private String secret;

    /**
     * 对称密钥
     */
    private String symmetricKey;

    /**
     * rsa签名私钥
     */
    private String rsaPrivateKey;

    /**
     * rsa签名公钥
     */
    private String rsaPublicKey;

    /**
     * 客服电话
     */
    private String serviceTel;

    /**
     * 客服服务时间
     */
    private String serviceTime;

    /**
     * 活动城市：ALL-全部、否则填城市行政区号，多个之间用英文逗号隔开
     */
    private String platformCity;

    /**
     * 默认支付商户
     */
    private Long merchantId;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

    /**
     * 部门id
     */
    private Long manangerDeptId;

    /**
     * 云闪付62会员权限：0-无权限，1-有权限
     */
    private String unionPayVip;
    /**
     * 支持端
     */
    private String supportChannel;
    /**
     * 供应商
     */
    private String supportSupplier;

    private List<PlatformChannelBo> platformChannel;

    /**
     * 首页瀑布流显示类型
     */
    private String indexShowType;

    /**
     * 分销：0-关闭，1-开启
     */
    private String sharePermission;
    /**
     * 核销之后，分销奖励多久到账，默认T+1,0为实时到账
     */
    private Integer shareUsedDate;
    /**
     * 分销奖励类型：0-云闪付红包
     */
    private String shareAwardType;
    /**
     * 分销奖励第三方产品编号,例如：云闪付红包活动编号(对应云闪付小程序开放平台配置：营销能力包-专享红包活动编码)
     */
    private String shareAwardProductId;
    /**
     * 机构账户代码，最大32位，对应云闪付小程序开放平台配置：营销能力包-红包接入方账户
     */
    private String shareAwardInsAcctId;
    /**
     * 同一用户每月可获奖励上限,0为不限制
     */
    private BigDecimal shareAwardMonthAmount;

    /**
     * 提现起始金额
     */
    private BigDecimal shareMinWithdrawDeposit;

    /**
     * 一级分销员(合作伙伴)奖励比例，利润的比例，例如订单利润1元，奖励比例50%,则奖励金额为：0.5元
     */
    @Min(value = 0, message = "一级分销员奖励比例不能小于0%", groups = {AddGroup.class, EditGroup.class})
    @Max(value = 1, message = "一级分销员奖励比例不能大于100%", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal shareOneAmount;

    /**
     * 二级分销员奖励比例，利润的比例，例如订单利润1元，奖励比例50%,则奖励金额为：0.5元
     */
    @Min(value = 0, message = "二级分销员奖励比例不能小于0%", groups = {AddGroup.class, EditGroup.class})
    @Max(value = 1, message = "二级分销员奖励比例不能大于100%", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal shareTwoAmount;

    private String showAllPerson;
    /**
     * 分销奖励是否展示给所有顾客
     */
    private String couponType;
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
     * 服务费率
     */
    private BigDecimal serviceTariffing;
    /**
     * 可分销用户：0-全部，1-白名单
     */
    private String shareUser;
    /**
     * 是否显示推广中心：0-显示，1-不显示
     */
    private String showShareIndex;
    /**
     * 分销设置：0-利润百分比，1-销售额百分比
     */
    private String shareAmountType;
}
