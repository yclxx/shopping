package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 城市商户视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class CityMerchantVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 行政编码
     */
    @ExcelProperty(value = "行政编码")
    private String adcode;

    /**
     * 行政区名称
     */
    @ExcelProperty(value = "行政区名称")
    private String areaName;

    /**
     * 商户ID
     */
    @ExcelProperty(value = "商户ID")
    private Long merchantId;


}
