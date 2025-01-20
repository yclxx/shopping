package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 银联任务奖励发放记录视图对象
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class UnionpayMissionUserLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 奖励记录ID
     */
    @ExcelProperty(value = "奖励记录ID")
    private Long upMissionUserLog;

    /**
     * 银联任务用户ID
     */
    @ExcelProperty(value = "银联任务用户ID")
    private Long upMissionUserId;

    /**
     * 银联任务组ID
     */
    @ExcelProperty(value = "银联任务组ID")
    private Long upMissionGroupId;

    /**
     * 银联任务ID
     */
    @ExcelProperty(value = "银联任务ID")
    private Long upMissionId;

    /**
     * 奖励产品ID
     */
    @ExcelProperty(value = "奖励产品ID")
    private Long productId;

    /**
     * 状态  0-未发放  1-发放中  2-发放成功  3-发放失败
     */
    @ExcelProperty(value = "状态  0-未发放  1-发放中  2-发放成功  3-发放失败", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_unionpay_mission_log_status")
    private String status;

    /**
     * 发放账号
     */
    @ExcelProperty(value = "发放账号")
    private String account;

    /**
     * 发放时间
     */
    @ExcelProperty(value = "发放时间")
    private Date sendTime;

    /**
     * 金额
     */
    @ExcelProperty(value = "金额")
    private BigDecimal amount;

    /**
     * 发放数量
     */
    @ExcelProperty(value = "发放数量")
    private Long sendCount;

    /**
     * 失败原因
     */
    @ExcelProperty(value = "失败原因")
    private String failReason;

    /**
     * 订单号
     */
    private Long number;

    private OrderVo orderVo;

    private ProductVo productVo;

    /**
     * 创建时间
     */
    private Date createTime;

    private UserVo userVo;

    private String productName;

    private String sendStatus;

}
