package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 微信小程序二维码业务对象
 *
 * @author yzg
 * @date 2024-10-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class WxSceneBo extends BaseEntity {

    /**
     * scene内容，传给微信生成二维码的
     */
    @NotNull(message = "scene内容，传给微信生成二维码的不能为空", groups = { EditGroup.class })
    private Long sceneId;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 跳转页面
     */
    @NotBlank(message = "跳转页面不能为空", groups = { AddGroup.class, EditGroup.class })
    private String page;

    /**
     * 真实scene内容
     */
    @NotBlank(message = "真实scene内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String scene;


}
