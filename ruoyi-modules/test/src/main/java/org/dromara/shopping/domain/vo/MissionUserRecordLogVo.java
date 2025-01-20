package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 活动订单取码记录视图对象
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@ExcelIgnoreUnannotated
public class MissionUserRecordLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 活动记录ID
     */
    @ExcelProperty(value = "活动记录ID")
    private Long missionUserRecordId;

    /**
     * 取码(充值)订单号
     */
    @ExcelProperty(value = "取码(充值)订单号")
    private String pushNumber;

    /**
     * 供应商订单号
     */
    @ExcelProperty(value = "供应商订单号")
    private String externalOrderNumber;



    /**
     * 取码提交供应商产品编号（供应商提供）
     */
    @ExcelProperty(value = "取码提交供应商产品编号")
    private String externalProductId;

    /**
     * 机构账户（介入方代码）
     */
    private String accessCode;

    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    @ExcelProperty(value = "发放金额")
    private BigDecimal sendValue;

    /**
     * 订单状态 0-处理中 1-成功 2-失败
     */
    @ExcelProperty(value = "订单状态")
    private String status;

    /**
     * 交易失败原因
     */
    @ExcelProperty(value = "交易失败原因")
    private String remark;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
