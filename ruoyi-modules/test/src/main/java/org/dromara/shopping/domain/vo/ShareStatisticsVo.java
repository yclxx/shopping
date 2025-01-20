package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;

/**
 * @author xiexi
 * @description
 * @date 2024/12/18 13:44
 */
@Data
@ExcelIgnoreUnannotated
public class ShareStatisticsVo {
    /**
     * 分销员ID
     */
    @ExcelProperty(value = "分销员ID")
    private Long userId;

    /**
     * 平台ID
     */
    @ExcelProperty(value = "平台ID")
    private Long platformKey;

    /**
     * 分销员手机号
     */
    @ExcelProperty(value = "分销员手机号")
    private String userMobile;

    /**
     * 分销订单状态
     */
    private String inviteeStatus;

    /**
     * 分销订单数量
     */
    @ExcelProperty(value = "总分销订单")
    private Long shareOrderCount = 0L;

    @ExcelProperty(value = "总金额")
    private BigDecimal shareAwardAmount = BigDecimal.ZERO;

    /**
     * 进行中的订单数量
     */
    @ExcelProperty(value = "进行中")
    private Long inviteeCount = 0L;
    @ExcelProperty(value = "进行中金额")
    private BigDecimal inviteeAwardAmount = BigDecimal.ZERO;

    /**
     * 成功数量
     */
    @ExcelProperty(value = "成功数量")
    private Long successCount = 0L;
    @ExcelProperty(value = "已获奖励")
    private BigDecimal successAwardAmount = BigDecimal.ZERO;

    /**
     * 退单数量
     */
    @ExcelProperty(value = "退单数量")
    private Long refundCount = 0L;
    @ExcelProperty(value = "退单扣除奖励")
    private BigDecimal refundAwardAmount = BigDecimal.ZERO;

    /**
     * 失效数量
     */
    @ExcelProperty(value = "失效数量")
    private Long cancelCount = 0L;
    @ExcelProperty(value = "失效扣除奖励")
    private BigDecimal cancelAwardAmount = BigDecimal.ZERO;
}
