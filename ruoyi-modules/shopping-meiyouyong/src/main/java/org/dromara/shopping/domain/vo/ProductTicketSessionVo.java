package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 演出(场次)日期视图对象
 *
 * @author yzg
 * @date 2023-09-12
 */
@Data
@ExcelIgnoreUnannotated
public class ProductTicketSessionVo {

    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    @ExcelProperty(value = "数据id")
    private Long sessionId;

    /**
     * 商品id
     */
    @ExcelProperty(value = "商品id")
    private Long productId;

    /**
     * 场次名称
     */
    @ExcelProperty(value = "场次名称")
    private String session;

    /**
     * 状态 0正常 1停用
     */
    @ExcelProperty(value = "状态 0正常 1停用")
    private String status;

    /**
     * 日期
     */
    @ExcelProperty(value = "日期")
    private Date date;
    /**
     * 是否时间范围
     */
    private String isRange;
    /**
     * 开始时间
     */
    private Date beginDate;
    /**
     * 结束时间
     */
    private Date endDate;

    /**
     * 说明
     */
    @ExcelProperty(value = "说明")
    private String description;

    private List<ProductTicketLineVo> ticketLine;
}
