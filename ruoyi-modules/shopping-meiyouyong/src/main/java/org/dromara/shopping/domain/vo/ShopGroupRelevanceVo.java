package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 门店组门店关联视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class ShopGroupRelevanceVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 门店组ID
     */
    @ExcelProperty(value = "门店组ID")
    private Long shopGroupId;

    /**
     * 门店ID
     */
    @ExcelProperty(value = "门店ID")
    private Long shopId;


}
