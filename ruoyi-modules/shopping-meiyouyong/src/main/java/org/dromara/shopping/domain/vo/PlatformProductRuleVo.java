package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.util.StringUtils;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 商品规则视图对象
 *
 * @author yzg
 * @date 2024-06-24
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformProductRuleVo {

    private static final long serialVersionUID = 1L;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 供应商
     */
    @ExcelProperty(value = "供应商")
    private String supportSupplier;

    /**
     * 类别
     */
    @ExcelProperty(value = "类别")
    private String categoryIds;

    /**
     * 最低价
     */
    @ExcelProperty(value = "最低价")
    private BigDecimal productMinPrice;

    /**
     * 最高价
     */
    @ExcelProperty(value = "最高价")
    private BigDecimal productMaxPrice;

    /**
     * 最低利润率
     */
    @ExcelProperty(value = "最低利润率")
    private BigDecimal productMinRate;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

    private List<String> supportSupplierList;

    public List<String> getSupportSupplierList() {
        if (StringUtils.isBlank(this.supportSupplier) || this.supportSupplier.equalsIgnoreCase("ALL")) {
            return null;
        }

        String[] split = this.supportSupplier.split(",");
        return Arrays.stream(split).collect(Collectors.toList());
    }
}
