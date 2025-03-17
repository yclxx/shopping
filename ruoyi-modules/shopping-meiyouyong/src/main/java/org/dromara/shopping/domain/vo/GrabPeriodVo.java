package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 秒杀配置视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class GrabPeriodVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String grabPeriodName;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_grab_period_status")
    private String status;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endDate;

    /**
     * 时间上方背景图
     */
    @ExcelProperty(value = "时间上方背景图")
    private String topBjImg;

    /**
     * 几点开始领取格式：HH:mm:ss
     */
    @ExcelProperty(value = "几点开始领取")
    private String sellStartTime;

    /**
     * 几点结束,格式：HH:mm:ss
     */
    @ExcelProperty(value = "几点结束")
    private String sellEndTime;

    /**
     * 周期: 0-每天，1-每周，2-指定日期
     */
    @ExcelProperty(value = "周期", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_grab_period_date_type")
    private String dateType;

    /**
     * date_type为1时：指定周几：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开；date_type为2时：指定日期格式yyyy-MM-dd，多个之间用英文逗号隔开
     */
    @ExcelProperty(value = "指定周几")
    private String dateList;

    /**
     * 活动规则
     */
    @ExcelProperty(value = "活动规则")
    private String description;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
