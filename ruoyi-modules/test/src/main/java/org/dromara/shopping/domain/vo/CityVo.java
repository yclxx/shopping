package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.List;


/**
 * 行政区视图对象
 *
 * @author yzg
 * @date 2024-01-12
 */
@Data
@ExcelIgnoreUnannotated
public class CityVo {

    private static final long serialVersionUID = 1L;

    /**
     * 区域编码
     */
    @ExcelProperty(value = "区域编码")
    private Long adcode;

    /**
     * 城市编码
     */
    @ExcelProperty(value = "城市编码")
    private String citycode;

    /**
     * 行政区名称
     */
    @ExcelProperty(value = "行政区名称")
    private String areaName;

    /**
     * 行政区划级别  country:国家  province:省份（直辖市会在province显示）  city:市（直辖市会在province显示）  district:区县  street:街道
     */
    @ExcelProperty(value = "行政区划级别  country:国家  province:省份", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_city_level")
    private String level;

    /**
     * 上级区域编码  000000-代表无上级
     */
    @ExcelProperty(value = "上级区域编码  000000-代表无上级")
    private Long parentCode;

    /**
     * 首字母
     */
    @ExcelProperty(value = "首字母")
    private String firstLetter;

    private List<CityVo> areas;


}
