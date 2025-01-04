package org.dromara.shopping.base.domain.vo;

import org.dromara.common.translation.annotation.Translation;
import org.dromara.common.translation.constant.TransConstant;
import org.dromara.shopping.base.domain.ProductImg;
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
 * 商品图片视图对象 t_product_img
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ProductImg.class)
public class ProductImgVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 商品
     */
    @ExcelProperty(value = "商品")
    private Long productId;

    /**
     * 图片
     */
    @ExcelProperty(value = "图片")
    private Long img;

    /**
     * 图片Url
     */
    @Translation(type = TransConstant.OSS_ID_TO_URL, mapper = "img")
    private String imgUrl;
    /**
     * 图片归属
     */
    @ExcelProperty(value = "图片归属", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_img_attribution")
    private String imgAttribution;

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
