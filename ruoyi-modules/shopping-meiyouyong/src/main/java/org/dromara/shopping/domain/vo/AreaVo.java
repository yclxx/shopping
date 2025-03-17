package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;



/**
 * 行政区视图对象
 *
 * @author ruoyi
 * @date 2023-03-18
 */
@Data
@ExcelIgnoreUnannotated
public class AreaVo extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 行政编码
     */
    @ExcelProperty(value = "行政编码")
    private Long adcode;

    /**
     * 行政区名称
     */
    @ExcelProperty(value = "行政区名称")
    private String areaName;

    /**
     * 行政区划级别,country:国家,province:省份（直辖市会在province和city显示）,city:市（直辖市会在province和city显示）,district:区县,street:街道
     */
    private String level;

    /**
     * 首字母
     */
    @ExcelProperty(value = "首字母")
    private String firstLetter;

    /**
     * 上级区域编码,000000代表无上级
     */
    @ExcelProperty(value = "上级区域编码")
    private Long parentCode;


}
