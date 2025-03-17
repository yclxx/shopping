package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 平台分享配置业务对象
 *
 * @author yzg
 * @date 2025-01-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformShareConfigBo extends BaseEntity {

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { EditGroup.class })
    private Long platformKey;

    /**
     * 分享标题
     */
    @NotBlank(message = "分享标题不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shareTitle;

    /**
     * 分享描述
     */
    @NotBlank(message = "分享描述不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shareName;

    /**
     * 分享图片
     */
    @NotBlank(message = "分享图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shareImage;


}
