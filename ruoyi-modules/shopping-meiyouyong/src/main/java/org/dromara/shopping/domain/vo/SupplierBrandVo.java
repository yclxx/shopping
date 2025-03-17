package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;



/**
 * 供应商品牌视图对象
 *
 * @author yzg
 * @date 2024-12-26
 */
@Data
@ExcelIgnoreUnannotated
public class SupplierBrandVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long brandId;

    /**
     * 供应商编号
     */
    @ExcelProperty(value = "供应商编号")
    private Long supplierId;

    /**
     * 品牌商标
     */
    @ExcelProperty(value = "品牌商标")
    private String brandTrademark;

    /**
     * 授权书
     */
    @ExcelProperty(value = "授权书")
    private String authorizeLetter;

    private Date createTime;


}
