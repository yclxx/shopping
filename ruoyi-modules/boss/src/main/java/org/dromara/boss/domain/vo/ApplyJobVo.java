package org.dromara.boss.domain.vo;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.boss.domain.ApplyJob;
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
 * 沟通任务视图对象 boss_apply_job
 *
 * @author xx
 * @date 2024-11-16
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApplyJob.class)
public class ApplyJobVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long applyJobId;

    /**
     * 期望工作
     */
    @ExcelProperty(value = "期望工作", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "encrypt_expect_type")
    private String encryptExpectId;

    /**
     * 执行页数
     */
    @ExcelProperty(value = "执行页数")
    private Long pages;

    /**
     * 跳过猎头
     */
    @ExcelProperty(value = "跳过猎头", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String skipHeadhunting;

    /**
     * 跳过兼职
     */
    @ExcelProperty(value = "跳过兼职", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_yes_no")
    private String skipPartTimeJob;

    /**
     * 最低薪资不可低于
     */
    @ExcelProperty(value = "最低薪资不可低于")
    private Long minSalary;

    /**
     * 最高薪资不可低于
     */
    @ExcelProperty(value = "最高薪资不可低于")
    private Long maxSalary;

    /**
     * 投递岗位
     */
    @ExcelProperty(value = "投递岗位", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "boss_position_name")
    private String positionName;

    /**
     * 活跃时间
     */
    @ExcelProperty(value = "活跃时间", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "boss_active_time_desc")
    private String activeTimeDesc;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "execute_status")
    private String status;

    /**
     * 沟通数量
     */
    @ExcelProperty(value = "沟通数量")
    private Long applyCount;

    /**
     * 完成时间
     */
    @ExcelProperty(value = "完成时间")
    private Date finishTime;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
