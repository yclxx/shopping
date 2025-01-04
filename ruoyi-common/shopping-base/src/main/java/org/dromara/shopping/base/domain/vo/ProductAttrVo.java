package org.dromara.shopping.base.domain.vo;

import org.dromara.shopping.base.domain.ProductAttr;
import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;



/**
 * 商品属性视图对象 t_product_attr
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ProductAttr.class)
public class ProductAttrVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ExcelProperty(value = "主键")
    private Long id;

    /**
     * 商品
     */
    @ExcelProperty(value = "商品")
    private Long productId;

    /**
     * 属性名
     */
    @ExcelProperty(value = "属性名")
    private String attrName;

    /**
     * 属性值
     */
    @ExcelProperty(value = "属性值")
    private String attrValues;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

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
