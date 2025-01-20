package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 结算记录视图对象
 *
 * @author yzg
 * @date 2024-08-21
 */
@Data
@ExcelIgnoreUnannotated
public class SettlementVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long settlementId;

    /**
     * 结算名称
     */
    @ExcelProperty(value = "结算名称")
    private String reconciliationName;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private String activityId;

    /**
     * 数据文件
     */
    @ExcelProperty(value = "数据文件")
    private String settlementMyFile;

    /**
     * 数据开始时间
     */
    @ExcelProperty(value = "数据开始时间")
    private Date startTime;

    /**
     * 数据截止时间
     */
    @ExcelProperty(value = "数据截止时间")
    private Date endTime;

    /**
     * 结算时间
     */
    @ExcelProperty(value = "结算时间")
    private Date settlementTime;

    /**
     * 结算金额
     */
    @ExcelProperty(value = "结算金额")
    private BigDecimal settlementAmount;

    /**
     * 服务费率
     */
    @ExcelProperty(value = "服务费率")
    private BigDecimal serviceRate;

    /**
     * 服务费
     */
    @ExcelProperty(value = "服务费")
    private BigDecimal serviceAmount;

    /**
     * 结算状态
     */
    @ExcelProperty(value = "结算状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_settlement_status")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

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
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    /**
     * 结算单类型：1-支付，2-退款
     */
    private String settlementType;
}
