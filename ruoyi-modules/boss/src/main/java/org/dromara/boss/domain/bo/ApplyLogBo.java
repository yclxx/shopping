package org.dromara.boss.domain.bo;

import org.dromara.boss.domain.ApplyLog;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;

/**
 * 沟通记录业务对象 boss_apply_log
 *
 * @author xx
 * @date 2024-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = ApplyLog.class, reverseConvertGenerate = false)
public class ApplyLogBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long applyLogId;

    private Long applyJobId;

    /**
     * 岗位
     */
    @NotBlank(message = "岗位不能为空", groups = { AddGroup.class, EditGroup.class })
    private String jobName;

    /**
     * 薪资
     */
    @NotBlank(message = "薪资不能为空", groups = { AddGroup.class, EditGroup.class })
    private String salaryDesc;

    /**
     * 城市
     */
    @NotBlank(message = "城市不能为空", groups = { AddGroup.class, EditGroup.class })
    private String locationName;

    /**
     * 地址
     */
    @NotBlank(message = "地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * boss名称
     */
    @NotBlank(message = "boss名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bossName;

    /**
     * 公司名称
     */
    @NotBlank(message = "公司名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String brandName;

    /**
     * 工作职责
     */
    @NotBlank(message = "工作职责不能为空", groups = { AddGroup.class, EditGroup.class })
    private String postDescription;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
