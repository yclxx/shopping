package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;



/**
 * 演出票种视图对象
 *
 * @author yzg
 * @date 2023-09-12
 */
@Data
@ExcelIgnoreUnannotated
public class ProductTicketLineVo {
    private static final long serialVersionUID = 1L;
    /**
     * 票种id
     */
    @ExcelProperty(value = "票种id")
    private Long lineId;
    /**
     * 商品id
     */
    @ExcelProperty(value = "商品id")
    private Long productId;
    /**
     * 场次id
     */
    @ExcelProperty(value = "场次id")
    private Long sessionId;
    /**
     * 第三方id
     */
    @ExcelProperty(value = "第三方id")
    private String otherId;
    /**
     * 票种名称
     */
    @ExcelProperty(value = "票种名称")
    private String lineTitle;
    /**
     * 销售价格
     */
    @ExcelProperty(value = "销售价格")
    private BigDecimal linePrice;
    /**
     * 结算价格
     */
    @ExcelProperty(value = "销售价格")
    private BigDecimal lineSettlePrice;
    /**
     * 最大数量
     */
    @ExcelProperty(value = "最大数量")
    private Long lineNumber;
    /**
     * 单次购买上限
     */
    @ExcelProperty(value = "单次购买上限")
    private Long lineUpperLimit;
    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String lineStatus;
    /**
     * 说明
     */
    @ExcelProperty(value = "说明")
    private String lineDescription;


}
