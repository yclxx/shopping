package org.dromara.shopping.base.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.shopping.base.domain.ProductAttrValue;
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
 * 商品属性值视图对象 t_product_attr_value
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ProductAttrValue.class)
public class ProductAttrValueVo implements Serializable {

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
     * 商品属性索引值 (attr_value|attr_value[|....])
     */
    @ExcelProperty(value = "商品属性索引值 (attr_value|attr_value[|....])")
    private String sku;

    /**
     * 图片
     */
    @ExcelProperty(value = "图片")
    private String img;

    /**
     * 图片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "img")
    private String imgUrl;
    /**
     * 售价
     */
    @ExcelProperty(value = "售价")
    private Long price;

    /**
     * 成本价
     */
    @ExcelProperty(value = "成本价")
    private Long cost;

    /**
     * 原价
     */
    @ExcelProperty(value = "原价")
    private Long otPrice;

    /**
     * 总库存
     */
    @ExcelProperty(value = "总库存")
    private Long totalStock;

    /**
     * 销量
     */
    @ExcelProperty(value = "销量")
    private Long sales;

    /**
     * 剩余库存
     */
    @ExcelProperty(value = "剩余库存")
    private Long stock;

    /**
     * attr_values 创建更新时的属性对应
     */
    @ExcelProperty(value = "attr_values 创建更新时的属性对应")
    private String attrValue;

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
