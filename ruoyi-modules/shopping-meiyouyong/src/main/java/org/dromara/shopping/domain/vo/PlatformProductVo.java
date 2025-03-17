package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 平台商品配置视图对象
 *
 * @author yzg
 * @date 2024-05-23
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformProductVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long platformProductId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 平台名称
     */
    @ExcelProperty(value = "平台名称")
    private String platformName;

    /**
     * 产品ID
     */
    @ExcelProperty(value = "产品ID")
    private Long productId;

    /**
     * 配置状态（0未配置 1已配置）
     */
    @ExcelProperty(value = "配置状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_platform_product_status")
    private String status;

    private String productName;

    private String productType;

    private String pickupMethod;

    private BigDecimal sellAmount;

    private BigDecimal originalAmount;

    private BigDecimal adjustPrice;

}
