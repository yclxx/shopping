package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 秒杀配置业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GrabPeriodBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String grabPeriodName;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 开始时间
     */
    @NotNull(message = "开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date startDate;

    /**
     * 结束时间
     */
    @NotNull(message = "结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endDate;

    /**
     * 时间上方背景图
     */
    private String topBjImg;

    /**
     * 几点开始领取格式：HH:mm:ss
     */
    private String sellStartTime;

    /**
     * 几点结束,格式：HH:mm:ss
     */
    private String sellEndTime;

    /**
     * 周期: 0-每天，1-每周，2-指定日期
     */
    @NotBlank(message = "周期不能为空", groups = { AddGroup.class, EditGroup.class })
    private String dateType;

    /**
     * date_type为1时：指定周几：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开；date_type为2时：指定日期格式yyyy-MM-dd，多个之间用英文逗号隔开
     */
    private String dateList;

    /**
     * 活动规则
     */
    private String description;

    /**
     * 平台标识
     */
    @NotNull(message = "平台不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;


}
