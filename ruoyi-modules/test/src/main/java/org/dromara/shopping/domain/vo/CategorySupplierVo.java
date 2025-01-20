package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 供应商产品分类视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class CategorySupplierVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 供应商编号（字典键值）
     */
    @ExcelProperty(value = "供应商编号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "字=典键值")
    private String supplierId;

    /**
     * 供应商名称
     */
    @ExcelProperty(value = "供应商名称")
    private String supplierName;

    /**
     * 供应商多级分类
     */
    @ExcelProperty(value = "供应商多级分类")
    private String fullName;

    /**
     * 分类Id(多个分类名称以英文逗号隔开)
     */
    @ExcelProperty(value = "分类Id(多个分类名称以英文逗号隔开)")
    private String categoryId;


}
