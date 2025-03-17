package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 到货清单视图对象
 *
 * @author yzg
 * @date 2024-05-08
 */
@Data
@ExcelIgnoreUnannotated
public class ProductArrivalListVo {

    private static final long serialVersionUID = 1L;

    /**
     * 库存id
     */
    //@ExcelProperty(value = "库存id")
    private Long arrivalListId;

    /**
     * 产品名称
     */
    @ExcelProperty(value = "产品名称")
    private String productName;
    /**
     * 日期
     */
    @ExcelProperty(value = "日期")
    private String listDateCopy;

    private Date listDate;

    /**
     * 数量
     */
    @ExcelProperty(value = "数量")
    private Long qty;

    /**
     * 发货时间
     */
    @ExcelProperty(value = "发货时间")
    private String sendTimeCopy;

    private Date sendTime;

    /**
     * 到货时间
     */
    @ExcelProperty(value = "到货时间")
    private String arriveTimeCopy;

    private Date arriveTime;

    /**
     * 确认到货  0-是  1-否
     */
    @ExcelProperty(value = "确认到货(是/否)", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_is_arrival")
    private String isArrive;

    private Long productId;

    private Long platformKey;


}
