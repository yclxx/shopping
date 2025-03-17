package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 领取记录视图对象
 *
 * @author yzg
 * @date 2023-06-06
 */
@Data
@ExcelIgnoreUnannotated
public class EquityRecordVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 权益包ID
     */
    @ExcelProperty(value = "权益包ID")
    private Long equityId;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品图片
     */
    @ExcelProperty(value = "商品图片")
    private String productImg;

    /**
     * 商品价值
     */
    @ExcelProperty(value = "商品价值")
    private BigDecimal productAmount;

    /**
     * 产品归属
     */
    @ExcelProperty(value = "产品归属", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "equity_type")
    private String equityType;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_equity_record_status")
    private String status;

    /**
     * 领取开始时间
     */
    @ExcelProperty(value = "领取开始时间")
    private Date receiveStartDate;

    /**
     * 领取结束时间
     */
    @ExcelProperty(value = "领取结束时间")
    private Date receiveEndDate;

    /**
     * 领取时间
     */
    @ExcelProperty(value = "领取时间")
    private Date receiveDate;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

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


}
