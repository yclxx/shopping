package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;



/**
 * 联联市级城市视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class LianlianCityVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long cityId;

    /**
     * 城市名称
     */
    @ExcelProperty(value = "城市名称")
    private String cityName;

    /**
     * 城市区号
     */
    @ExcelProperty(value = "城市区号")
    private String cityCode;

    /**
     * 状态：0-可用，1-禁用
     */
    @ExcelProperty(value = "状态：0-可用，1-禁用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;


}
