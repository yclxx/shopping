package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;



/**
 * 银联任务进度视图对象
 *
 * @author yzg
 * @date 2024-02-22
 */
@Data
@ExcelIgnoreUnannotated
public class UnionpayMissionProgressVo {

    private static final long serialVersionUID = 1L;

    /**
     * 任务进度ID
     */
    @ExcelProperty(value = "任务进度ID")
    private Long progressId;

    /**
     * 银联任务ID
     */
    @ExcelProperty(value = "银联任务ID")
    private Long upMissionId;

    /**
     * 银联任务组ID
     */
    @ExcelProperty(value = "银联任务组ID")
    private Long upMissionGroupId;

    /**
     * 银联任务用户id
     */
    @ExcelProperty(value = "银联任务用户id")
    private Long upMissionUserId;

    /**
     * 当日进度
     */
    @ExcelProperty(value = "当日进度")
    private Long dayProgress;

    /**
     * 本周进度
     */
    @ExcelProperty(value = "本周进度")
    private Long weekProgress;

    /**
     * 本月进度
     */
    @ExcelProperty(value = "本月进度")
    private Long monthProgress;

    /**
     * 活动总进度
     */
    @ExcelProperty(value = "活动总进度")
    private Long activityProgress;

    private UnionpayMissionVo unionpayMissionVo;

    /**
     * 创建时间
     */
    private Date createTime;

    private UserVo userVo;

    /**
     * 交易进度
     */
    private String tranProgress;
}
