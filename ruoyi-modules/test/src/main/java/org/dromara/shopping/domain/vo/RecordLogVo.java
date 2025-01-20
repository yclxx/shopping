package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 记录日志视图对象
 *
 * @author yzg
 * @date 2023-08-01
 */
@Data
@ExcelIgnoreUnannotated
public class RecordLogVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long recordId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 用户点击次数
     */
    @ExcelProperty(value = "用户点击次数")
    private Long userNumber;

    /**
     * 用户人数
     */
    @ExcelProperty(value = "用户人数")
    private Long userPeople;

    /**
     * 订单购买次数
     */
    @ExcelProperty(value = "订单购买次数")
    private Long orderBuyNumber;
    /**
     * 订单购买次数
     */
    @ExcelProperty(value = "用户购买人数")
    private Long orderBuyUser;

    /**
     * 记录日期
     */
    @ExcelProperty(value = "记录日期")
    private String recordDate;

    /**
     * 来源
     */
    @ExcelProperty(value = "来源", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "source_type")
    private String source;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端")
    private String supportChannel;

}
