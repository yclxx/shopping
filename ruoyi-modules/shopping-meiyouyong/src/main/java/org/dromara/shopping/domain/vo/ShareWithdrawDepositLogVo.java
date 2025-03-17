package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.sensitive.annotation.Sensitive;
import org.dromara.common.sensitive.core.SensitiveStrategy;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 提现审核视图对象
 *
 * @author yzg
 * @date 2024-10-31
 */
@Data
@ExcelIgnoreUnannotated
public class ShareWithdrawDepositLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 分销员用户ID
     */
    @ExcelProperty(value = "分销员用户ID")
    private Long userId;

    /**
     * 分销员手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @ExcelProperty(value = "分销员手机号")
    private String userMobile;

    /**
     * 提现金额
     */
    @ExcelProperty(value = "提现金额")
    private BigDecimal amount;

    /**
     * 审核状态
     */
    @ExcelProperty(value = "审核状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "audit_status")
    private String status;

    /**
     * 发放状态
     */
    @ExcelProperty(value = "发放状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_send_status")
    private String sendStatus;

    /**
     * 发放时间
     */
    @ExcelProperty(value = "发放时间")
    private Date sendTime;

    /**
     * 发放账号
     */
    @ExcelProperty(value = "发放账号")
    private String sendAccount;

    /**
     * 发放订单号
     */
    @ExcelProperty(value = "发放订单号")
    private String pushNumber;

    /**
     * 发放结果
     */
    @ExcelProperty(value = "发放结果")
    private String pushRemake;

    /**
     * 拒绝原因
     */
    @ExcelProperty(value = "拒绝原因")
    private String remake;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 回滚金额：0-未回滚，1-已回滚
     */
    private String callbackAmount;

}
