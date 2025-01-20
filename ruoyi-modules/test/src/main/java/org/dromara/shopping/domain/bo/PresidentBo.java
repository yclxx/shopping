package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 支行长业务对象
 *
 * @author yzg
 * @date 2024-04-28
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PresidentBo extends BaseEntity {

    /**
     * 支行长id
     */
    @NotNull(message = "支行长id不能为空", groups = { EditGroup.class })
    private Long presidentId;

    /**
     * 姓名
     */
    //@NotBlank(message = "姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 手机号
     */
    //@NotBlank(message = "手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String mobile;

    /**
     * openId
     */
    //@NotBlank(message = "openId不能为空", groups = { AddGroup.class, EditGroup.class })
    private String openId;

    /**
     * 银行
     */
    //@NotBlank(message = "银行不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bank;

    /**
     * 一支
     */
    //@NotBlank(message = "一支不能为空", groups = { AddGroup.class, EditGroup.class })
    private String linkmanBranch;

    /**
     * 二支
     */
    //@NotBlank(message = "二支不能为空", groups = { AddGroup.class, EditGroup.class })
    private String linkmanBranchSecond;

    /**
     * 状态  0-正常  1-停用
     */
    //@NotBlank(message = "状态  0-正常  1-停用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    private Long platformKey;

    private String identification;


}
