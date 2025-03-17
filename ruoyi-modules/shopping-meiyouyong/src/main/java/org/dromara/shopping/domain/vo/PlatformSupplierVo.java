package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 平台供应商视图对象
 *
 * @author yzg
 * @date 2025-01-13
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformSupplierVo {

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
     * 供应商id
     */
    @ExcelProperty(value = "供应商id")
    private Long supplierId;

    /**
     * 售价最低价
     */
    @ExcelProperty(value = "售价最低价")
    private BigDecimal productMinPrice;

    /**
     * 售价最高价，小于等于0为不限制
     */
    @ExcelProperty(value = "售价最高价，小于等于0为不限制")
    private BigDecimal productMaxPrice;

    /**
     * 最低利润率
     */
    @ExcelProperty(value = "最低利润率")
    private BigDecimal productMinRate;

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
