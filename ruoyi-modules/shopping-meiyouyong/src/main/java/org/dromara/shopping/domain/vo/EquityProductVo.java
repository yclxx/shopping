package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 权益包商品视图对象
 *
 * @author yzg
 * @date 2023-06-06
 */
@Data
@ExcelIgnoreUnannotated
public class EquityProductVo {

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
     * 可领数量
     */
    @ExcelProperty(value = "可领数量")
    private Long sendCount;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

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
