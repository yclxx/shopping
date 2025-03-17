package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 分销关系绑定业务对象
 *
 * @author yzg
 * @date 2024-10-24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShareUserBindBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 分销员用户ID
     */
    @NotNull(message = "分销员用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 被分销用户ID
     */
    @NotNull(message = "被分销用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bindUserId;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 失效时间
     */
    @NotNull(message = "失效时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;


}
