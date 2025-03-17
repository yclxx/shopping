package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 商户申请审批视图对象
 *
 * @author yzg
 * @date 2023-10-19
 */
@Data
@ExcelIgnoreUnannotated
public class MerchantApprovalVo {

    private static final long serialVersionUID = 1L;

    private Long approvalId;
    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;
    /**
     * 活动任务id
     */
    @ExcelProperty(value = "活动任务id")
    private Long taskId;
    /**
     * 类型（1商户申请，2商户录入）
     */
    @ExcelProperty(value = "类型（1商户申请，2商户录入）")
    private String type;
    /**
     * 管理员手机号
     */
    @ExcelProperty(value = "管理员手机号")
    private String brandMobile;
    /**
     * 品牌名称
     */
    @ExcelProperty(value = "品牌名称")
    private String brandName;
    /**
     * 品牌logo
     */
    @ExcelProperty(value = "品牌logo")
    private String brandLogo;
    /**
     * 品牌商户简称
     */
    @ExcelProperty(value = "品牌商户简称")
    private String brandReferred;
    /**
     * 邮储商户
     */
    @ExcelProperty(value = "邮储商户")
    private String activityNature;
    /**
     * 进件者（拓展服务商）
     */
    @ExcelProperty(value = "进件者")
    private String extend;
    /**
     * 性质
     */
    @ExcelProperty(value = "性质")
    private String nature;
    /**
     * 门店名称
     */
    @ExcelProperty(value = "门店名称")
    private String shopName;
    /**
     * 门店地址
     */
    @ExcelProperty(value = "门店地址")
    private String shopAddress;
    /**
     * 门店地址详情
     */
    @ExcelProperty(value = "门店地址详情")
    private String shopAddressInfo;
    /**
     * 门店电话
     */
    @ExcelProperty(value = "门店电话")
    private String shopMobile;
    /**
     * 门店图片
     */
    @ExcelProperty(value = "门店图片")
    private String shopImage;
    /**
     * 门店类型
     */
    @ExcelProperty(value = "门店类型")
    private String shopType;
    /**
     * 营业执照
     */
    @ExcelProperty(value = "营业执照")
    private String businessLicense;
    /**
     * 营业周
     */
    @ExcelProperty(value = "营业周")
    private String businessWeek;
    /**
     * 每天营业开始时间
     */
    @ExcelProperty(value = "每天营业开始时间")
    private String businessBegin;
    /**
     * 每天营业结束时间
     */
    @ExcelProperty(value = "每天营业结束时间")
    private String businessEnd;
    /**
     * 节假日是否营业
     */
    @ExcelProperty(value = "节假日是否营业")
    private Boolean businessHoliday;
    /**
     * 参与营销活动类型
     */
    @ExcelProperty(value = "参与营销活动类型")
    private String activity;
    /**
     * 发票类型
     */
    @ExcelProperty(value = "发票类型")
    private String invoiceType;
    /**
     * 收款人
     */
    @ExcelProperty(value = "收款人")
    private String accountPayee;
    /**
     * 开户行
     */
    @ExcelProperty(value = "开户行")
    private String accountBank;
    /**
     * 收款账户
     */
    @ExcelProperty(value = "收款账户")
    private String account;
    /**
     * 商户所在平台
     */
    @ExcelProperty(value = "商户所在平台")
    private Long merchantPlatformKey;
    /**
     * 商户号信息
     */
    @ExcelProperty(value = "商户号信息")
    private String merchant;
    /**
     * 审批状态
     */
    @ExcelProperty(value = "审批状态")
    private String approvalStatus;
    /**
     * 拒绝原因
     */
    @ExcelProperty(value = "拒绝原因")
    private String rejectReason;
}
