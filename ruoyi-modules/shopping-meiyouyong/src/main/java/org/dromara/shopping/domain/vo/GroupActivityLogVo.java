package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团活动记录视图对象
 *
 * @author yzg
 * @date 2024-10-10
 */
@Data
@ExcelIgnoreUnannotated
public class GroupActivityLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 编号ID
     */
    @ExcelProperty(value = "编号ID")
    private Long id;

    /**
     * 活动编号
     */
    @ExcelProperty(value = "活动编号")
    private Long activityId;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String activityName;

    /**
     * 商品编号
     */
    @ExcelProperty(value = "商品编号")
    private Long productId;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品销售价
     */
    @ExcelProperty(value = "商品销售价")
    private BigDecimal sellingPrice;

    /**
     * 拼团价
     */
    @ExcelProperty(value = "拼团价")
    private BigDecimal groupPrice;

    /**
     * 成团人数
     */
    @ExcelProperty(value = "成团人数")
    private Long groupCount;

    /**
     * 拼团人数
     */
    @ExcelProperty(value = "拼团人数")
    private Long attendCount;

    /**
     * 拼团状态
     */
    @ExcelProperty(value = "拼团状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_group_status")
    private String groupStatus;

    /**
     * 拼团到期时间
     */
    @ExcelProperty(value = "拼团到期时间")
    private Date groupTime;

    private Date createTime;

    /**
     * 交易状态
     */
    private String state;

    private Long number;

    private String groupNumber;

    private String LastMobile;

    private String img;

    private Long groupMill;

    private String activityGroup;


}
