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
 * 用户渠道信息业务对象
 *
 * @author yzg
 * @date 2023-10-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserChannelBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 用户昵称
     */
    @NotBlank(message = "用户昵称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userName;

    /**
     * 头像
     */
    @NotBlank(message = "头像不能为空", groups = { AddGroup.class, EditGroup.class })
    private String userImg;

    /**
     * openId
     */
    @NotBlank(message = "openId不能为空", groups = { AddGroup.class, EditGroup.class })
    private String openId;

    /**
     * 信息授权
     */
    @NotBlank(message = "信息授权不能为空", groups = { AddGroup.class, EditGroup.class })
    private String reloadUser;

    /**
     * 首次访问所在城市
     */
    @NotBlank(message = "首次访问所在城市不能为空", groups = { AddGroup.class, EditGroup.class })
    private String registerCityName;

    /**
     * 首次访问所在城市行政区号
     */
    @NotBlank(message = "首次访问所在城市行政区号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String registerCityCode;

    /**
     * 关注状态
     */
    @NotBlank(message = "关注状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String followStatus;

    /**
     * 最后登录IP
     */
    @NotBlank(message = "最后登录IP不能为空", groups = { AddGroup.class, EditGroup.class })
    private String loginIp;

    /**
     * 最后登录时间
     */
    @NotNull(message = "最后登录时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date loginDate;

    /**
     * 最后登录所在城市
     */
    @NotBlank(message = "最后登录所在城市不能为空", groups = { AddGroup.class, EditGroup.class })
    private String loginCityName;

    /**
     * 最后登录所在城市行政区号
     */
    @NotBlank(message = "最后登录所在城市行政区号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String loginCityCode;

    /**
     * 上次登录时间
     */
    @NotNull(message = "上次登录时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date lastLoginDate;

    /**
     * 渠道
     */
    @NotBlank(message = "渠道不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channel;
    /**
     * 平台标识
     */
    private Long platformKey;

}
