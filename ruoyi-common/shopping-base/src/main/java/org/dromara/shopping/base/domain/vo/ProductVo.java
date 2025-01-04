package org.dromara.shopping.base.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.shopping.base.domain.Product;
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
 * 商品信息视图对象 t_product
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = Product.class)
public class ProductVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

    /**
     * 商品名称
     */
    @ExcelProperty(value = "商品名称")
    private String productName;

    /**
     * 商品图片
     */
    @ExcelProperty(value = "商品图片")
    private String productImg;

    /**
     * 商品类型
     */
    @ExcelProperty(value = "商品类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_product_type")
    private String productType;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 被搜索
     */
    @ExcelProperty(value = "被搜索", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String searchStatus;

    /**
     * 显示首页
     */
    @ExcelProperty(value = "显示首页", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String showIndex;

    /**
     * 展示开始时间
     */
    @ExcelProperty(value = "展示开始时间")
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    @ExcelProperty(value = "展示结束时间")
    private Date showEndDate;

    /**
     * 售卖开始时间
     */
    @ExcelProperty(value = "售卖开始时间")
    private Date sellStartDate;

    /**
     * 售卖结束时间
     */
    @ExcelProperty(value = "售卖结束时间")
    private Date sellEndDate;

    /**
     * 售价
     */
    @ExcelProperty(value = "售价")
    private Long price;

    /**
     * 原价
     */
    @ExcelProperty(value = "原价")
    private Long otPrice;

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
