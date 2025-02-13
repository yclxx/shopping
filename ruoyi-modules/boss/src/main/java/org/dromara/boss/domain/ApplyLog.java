package org.dromara.boss.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serial;

/**
 * 沟通记录对象 boss_apply_log
 *
 * @author xx
 * @date 2024-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("boss_apply_log")
public class ApplyLog extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @TableId(value = "apply_log_id")
    private Long applyLogId;

    private Long applyJobId;

    /**
     * 岗位
     */
    private String jobName;

    /**
     * 薪资
     */
    private String salaryDesc;

    /**
     * 城市
     */
    private String locationName;

    /**
     * 地址
     */
    private String address;

    /**
     * boss名称
     */
    private String bossName;

    /**
     * 公司名称
     */
    private String brandName;

    /**
     * 工作职责
     */
    private String postDescription;

    /**
     * 状态
     */
    private String status;


}
