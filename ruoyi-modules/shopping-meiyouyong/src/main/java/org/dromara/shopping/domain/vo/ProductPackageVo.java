package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 商品券包视图对象
 *
 * @author yzg
 * @date 2023-06-30
 */
@Data
@ExcelIgnoreUnannotated
public class ProductPackageVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long packageId;

    /**
     * 券包ID
     */
    @ExcelProperty(value = "券包ID")
    private Long productId;

    /**
     * 包内产品
     */
    @ExcelProperty(value = "包内产品")
    private Long extProductId;

    /**
     * 发放数量
     */
    @ExcelProperty(value = "发放数量")
    private Long sendCount;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;


}
