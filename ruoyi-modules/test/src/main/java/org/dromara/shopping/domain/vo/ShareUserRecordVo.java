package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.ruoyi.common.core.annotation.Sensitive;
import com.ruoyi.common.core.enums.SensitiveStrategy;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分销记录视图对象
 *
 * @author yzg
 * @date 2023-11-09
 */
@Data
@ExcelIgnoreUnannotated
public class ShareUserRecordVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long recordId;

    /**
     * 平台ID
     */
    @ExcelProperty(value = "平台ID")
    private Long platformKey;

    /**
     * 分销员用户ID
     */
    @ExcelProperty(value = "分销员用户ID")
    private Long userId;

    /**
     * 分销员用户手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @ExcelProperty(value = "分销员用户手机号")
    private String userMobile;

    /**
     * 被分销用户ID
     */
    @ExcelProperty(value = "被分销用户ID")
    private Long inviteeUserId;

    /**
     * 被分销用户手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @ExcelProperty(value = "被分销用户手机号")
    private String inviteeUserMobile;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    @ExcelProperty(value = "支付方式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_pay_way")
    private String orderPayWay;

    /**
     * 订单核销时间
     */
    @ExcelProperty(value = "订单核销时间")
    private Date orderUsedTime;

    /**
     * 奖励金额
     */
    @ExcelProperty(value = "奖励金额")
    private BigDecimal awardAmount;

    /**
     * 奖励类型
     */
    private String awardType;

    /**
     * 分销状态
     */
    @ExcelProperty(value = "分销状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "invitee_status")
    private String inviteeStatus;

    /**
     * 奖励状态
     */
    @ExcelProperty(value = "奖励状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "award_status")
    private String awardStatus;

    /**
     * 奖励时间
     */
    @ExcelProperty(value = "奖励时间")
    private Date awardTime;

    /**
     * 奖励发放账号
     */
    @ExcelProperty(value = "奖励发放账号")
    private String awardAccount;

    /**
     * 奖励订单号
     */
    private String awardPushNumber;
    /**
     * 发放结果
     */
    private String pushRemake;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;
    /**
     * 实际发放金额
     */
    private BigDecimal actualReleasAmount;
    /**
     * 备注
     */
    private String remake;

    @ExcelProperty(value = "订单金额")
    private BigDecimal totalAmount;

    @ExcelProperty(value = "支付手续费")
    private BigDecimal payServiceCharge;

    private Integer orderCount;

    /**
     * 分行
     */
    @ExcelProperty(value = "分行")
    private String bankBranch;

    /**
     * 支行
     */
    @ExcelProperty(value = "支行")
    private String bankSubBranch;

    /**
     * 网点
     */
    @ExcelProperty(value = "网点")
    private String bankWebsite;
}
