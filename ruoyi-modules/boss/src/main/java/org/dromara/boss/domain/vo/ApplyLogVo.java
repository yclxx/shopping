package org.dromara.boss.domain.vo;

import org.dromara.boss.domain.ApplyLog;
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
 * 沟通记录视图对象 boss_apply_log
 *
 * @author xx
 * @date 2024-11-16
 */
@Data
@ExcelIgnoreUnannotated
@AutoMapper(target = ApplyLog.class)
public class ApplyLogVo implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long applyLogId;

    private Long applyJobId;

    /**
     * 岗位
     */
    @ExcelProperty(value = "岗位")
    private String jobName;

    /**
     * 薪资
     */
    @ExcelProperty(value = "薪资")
    private String salaryDesc;

    /**
     * 城市
     */
    @ExcelProperty(value = "城市")
    private String locationName;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String address;

    /**
     * boss名称
     */
    @ExcelProperty(value = "boss名称")
    private String bossName;

    /**
     * 公司名称
     */
    @ExcelProperty(value = "公司名称")
    private String brandName;

    /**
     * 工作职责
     */
    @ExcelProperty(value = "工作职责")
    private String postDescription;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

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
