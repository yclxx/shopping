package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 任务组背景业务对象
 *
 * @author yzg
 * @date 2024-03-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayMissionGroupBgBo extends BaseEntity {

    /**
     * 任务组背景id
     */
    @NotNull(message = "任务组背景id不能为空", groups = { EditGroup.class })
    private Long missionBgId;

    /**
     * 任务组ID
     */
    //@NotNull(message = "任务组ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long missionGroupId;

    /**
     * 背景图片
     */
    //@NotBlank(message = "背景图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bgImg;

    /**
     * 背景类型  1-首页(报名前)  2-首页(报名后)  3-记录页
     */
    //@NotBlank(message = "背景类型  1-首页(报名前)  2-首页(报名后)  3-记录页不能为空", groups = { AddGroup.class, EditGroup.class })
    private String imgType;

    /**
     * 平台标识
     */
    //@NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 状态  0-正常  1-停用
     */
    //@NotBlank(message = "状态  0-正常  1-停用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 排序 从小到大 默认99
     */
    //@NotNull(message = "排序 从小到大 默认99不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;

    private String bgName;

    private String isToLink;

    private String toUrl;

}
