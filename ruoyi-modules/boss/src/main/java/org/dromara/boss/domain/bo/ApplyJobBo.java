package org.dromara.boss.domain.bo;

import io.github.linpeilie.annotations.AutoMapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.boss.domain.ApplyJob;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.util.Date;

/**
 * 沟通任务业务对象 boss_apply_job
 *
 * @author xx
 * @date 2024-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApplyJob.class, reverseConvertGenerate = false)
public class ApplyJobBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long applyJobId;

    /**
     * 期望工作
     */
    @NotBlank(message = "期望工作不能为空", groups = { AddGroup.class, EditGroup.class })
    private String encryptExpectId;

    /**
     * 执行页数
     */
    @NotNull(message = "执行页数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long pages;

    /**
     * 跳过猎头
     */
    @NotBlank(message = "跳过猎头不能为空", groups = { AddGroup.class, EditGroup.class })
    private String skipHeadhunting;

    /**
     * 跳过兼职
     */
    @NotBlank(message = "跳过兼职不能为空", groups = { AddGroup.class, EditGroup.class })
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
