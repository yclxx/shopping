package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 用户信息业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserBo extends BaseEntity {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { EditGroup.class })
    private Long userId;

    /**
     * 用户昵称
     */
    private String userName;

    /**
     * 头像
     */
    private String userImg;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * openId，第三方平台联登唯一标识
     */
    private String openId;

    /**
     * 状态（0正常 1停用）
     */
    private String status;

    /**
     * 信息授权（0需要 1不需要）
     */
    private String reloadUser;

    /**
     * 是否权益会员（0不是 1是）
     */
    private String vipUser;

    /**
     * 首次访问所在城市
     */
    private String registerCityName;

    /**
     * 首次访问所在城市行政区号
     */
    private String registerCityCode;

    /**
     * 小程序关注状态（0-未知 1-已关注，2-取消关注）
     */
    private String followStatus;

    /**
     * 最后登录IP
     */
    private String loginIp;

    /**
     * 最后登录时间
     */
    private Date loginDate;

    /**
     * 最后登录所在城市
     */
    private String loginCityName;

    /**
     * 最后登录所在城市行政区号
     */
    private String loginCityCode;

    /**
     * 平台标识
     */
    private Long platformKey;

    private String beginStartDate;

    private String endStartDate;

    /**
     * 权益会员失效时间
     */
    private Date vipExpiryDate;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;
    /**
     * 活动标记 0-正常用户 1-活动标记用户
     */
    private String activityFlag;
}
