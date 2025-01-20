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
 * 广发用户业务对象
 *
 * @author yzg
 * @date 2024-05-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GfUserBo extends BaseEntity {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { EditGroup.class })
    private Long userId;

    /**
     * 手机号
     */
    @NotBlank(message = "手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String mobile;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 发放会员（0未发放 1已发放）
     */
    private String sendVip;

    /**
     * 发放会员时间
     */
    private Date sendVipTime;


}
