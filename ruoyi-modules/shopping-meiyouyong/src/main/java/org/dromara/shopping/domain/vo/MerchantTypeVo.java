package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 商户门店类别视图对象
 *
 * @author yzg
 * @date 2024-01-04
 */
@Data
@ExcelIgnoreUnannotated
public class MerchantTypeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 商户类别id
     */
    @ExcelProperty(value = "商户类别id")
    private Long merchantTypeId;

    /**
     * 类别名称
     */
    @ExcelProperty(value = "类别名称")
    private String typeName;

    /**
     * 状态  0-正常  1-停用
     */
    @ExcelProperty(value = "状态  0-正常  1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
