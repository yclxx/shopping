package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 平台切换视图对象
 *
 * @author yzg
 * @date 2024-03-19
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformCityChangeVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 行政编码
     */
    @ExcelProperty(value = "行政编码")
    private String adcode;

    /**
     * 替换后平台
     */
    @ExcelProperty(value = "替换后平台")
    private Long changePlatformKey;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 生效时间
     */
    @ExcelProperty(value = "生效时间")
    private Date startTime;

    /**
     * 失效时间
     */
    @ExcelProperty(value = "失效时间")
    private Date endTime;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
