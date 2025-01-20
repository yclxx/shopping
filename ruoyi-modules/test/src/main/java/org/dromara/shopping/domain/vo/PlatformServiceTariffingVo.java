package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 类别服务费视图对象
 *
 * @author yzg
 * @date 2024-06-13
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformServiceTariffingVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 栏目id
     */
    @ExcelProperty(value = "栏目id")
    private Long categoryId;

    /**
     * 服务费率
     */
    @ExcelProperty(value = "服务费率")
    private BigDecimal serviceTariffing;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;


}
