package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 第三方平台信息配置业务对象
 *
 * @author yzg
 * @date 2024-03-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ThirdPlatformBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * appId
     */
    @NotNull(message = "appId不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appId;

    /**
     * 密钥
     */
    @NotBlank(message = "密钥不能为空", groups = { AddGroup.class, EditGroup.class })
    private String secret;

    /**
     * 类型
     */
    @NotBlank(message = "类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String type;

    /**
     * 平台名称
     */
    @NotBlank(message = "平台名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String appName;

    /**
     * 状态：0-正常，1-禁用
     */
    @NotBlank(message = "状态：0-正常，1-禁用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
