package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 平台分享海报业务对象
 *
 * @author yzg
 * @date 2025-01-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformSharePosterBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 分享海报
     */
    @NotBlank(message = "分享海报不能为空", groups = { AddGroup.class, EditGroup.class })
    private String sharePoster;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 描述
     */
    private String shareTitle;
    /**
     * 排序
     */
    private Long sort;
}
