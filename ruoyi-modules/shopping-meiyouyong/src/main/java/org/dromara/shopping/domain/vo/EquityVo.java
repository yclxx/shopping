package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 权益包视图对象
 *
 * @author yzg
 * @date 2023-06-06
 */
@Data
@ExcelIgnoreUnannotated
public class EquityVo {

    private static final long serialVersionUID = 1L;

    /**
     * 权益包ID
     */
    @ExcelProperty(value = "权益包ID")
    private Long equityId;

    /**
     * 权益包名称
     */
    @ExcelProperty(value = "权益包名称")
    private String equityName;

    /**
     * 售卖价格
     */
    @ExcelProperty(value = "售卖价格")
    private BigDecimal sellAmount;

    /**
     * 展示开始时间
     */
    @ExcelProperty(value = "展示开始时间")
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    @ExcelProperty(value = "展示结束时间")
    private Date showEndDate;

    /**
     * 售卖开始时间
     */
    @ExcelProperty(value = "售卖开始时间")
    private Date sellStartDate;

    /**
     * 售卖结束时间
     */
    @ExcelProperty(value = "售卖结束时间")
    private Date sellEndDate;

    /**
     * 购买后权益有效期
     */
    @ExcelProperty(value = "购买后权益有效期")
    private Long expiryDate;

    /**
     * 每日售卖数量,0为不限制
     */
    @ExcelProperty(value = "每日售卖数量,0为不限制")
    private Long dayCount;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 规则图片
     */
    @ExcelProperty(value = "规则图片")
    private String equityImg;

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

    private Long platformKey;
}
