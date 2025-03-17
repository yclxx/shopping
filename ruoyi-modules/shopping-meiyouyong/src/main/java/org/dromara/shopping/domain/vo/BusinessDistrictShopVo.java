package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 商圈门店关联视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class BusinessDistrictShopVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 商圈ID
     */
    @ExcelProperty(value = "商圈ID")
    private Long businessDistrictId;

    /**
     * 门店ID
     */
    @ExcelProperty(value = "门店ID")
    private Long shopId;


}
