package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 热门搜索配置视图对象
 *
 * @author yzg
 * @date 2023-07-21
 */
@Data
@ExcelIgnoreUnannotated
public class HotNewsVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long newsId;

    /**
     * 显示名称
     */
    @ExcelProperty(value = "显示名称")
    private String newsName;

    /**
     * 排序，从小到大
     */
    @ExcelProperty(value = "排序，从小到大")
    private Long newsRank;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_notice_status")
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
     * 指定星期
     */
    @ExcelProperty(value = "指定星期")
    private String assignDate;

    /**
     * 展示城市
     */
    @ExcelProperty(value = "展示城市")
    private String showCity;

    /**
     * 展示星期
     */
    @ExcelProperty(value = "展示星期")
    private String weekDate;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端")
    private String supportChannel;


}
