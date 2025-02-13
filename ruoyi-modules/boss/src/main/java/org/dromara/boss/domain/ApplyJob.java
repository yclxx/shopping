package org.dromara.boss.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serial;

/**
 * 沟通任务对象 boss_apply_job
 *
 * @author xx
 * @date 2024-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("boss_apply_job")
public class ApplyJob extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "apply_job_id")
    private Long applyJobId;

    /**
     * 期望工作
     */
    private String encryptExpectId;

    /**
     * 执行页数
     */
    private Long pages;

    /**
     * 跳过猎头
     */
    private String skipHeadhunting;

    /**
     * 跳过兼职
     */
    private String skipPartTimeJob;

    /**
     * 最低薪资不可低于
     */
    private Long minSalary;

    /**
     * 最高薪资不可低于
     */
    private Long maxSalary;

    /**
     * 投递岗位
     */
    private String positionName;

    /**
     * 活跃时间
     */
    private String activeTimeDesc;

    /**
     * 状态
     */
    private String status;

    /**
     * 沟通数量
     */
    private Long applyCount;

    /**
     * 完成时间
     */
    private Date finishTime;


}
