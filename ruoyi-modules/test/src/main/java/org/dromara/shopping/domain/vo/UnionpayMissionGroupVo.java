package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 银联任务组视图对象
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class UnionpayMissionGroupVo {

    private static final long serialVersionUID = 1L;

    /**
     * 任务组ID
     */
    @ExcelProperty(value = "任务组ID")
    private Long upMissionGroupId;

    /**
     * 任务组名称
     */
    @ExcelProperty(value = "任务组名称")
    private String upMissionGroupName;

    /**
     * 状态  0-正常  1-停用
     */
    @ExcelProperty(value = "状态  0-正常  1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
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
     * 银联任务组编号
     */
    @ExcelProperty(value = "银联任务组编号")
    private String upMissionGroupUpid;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 创建时间
     */
    private Date createTime;


}
