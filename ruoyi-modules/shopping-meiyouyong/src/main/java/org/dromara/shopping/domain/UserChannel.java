package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 用户渠道信息对象 t_user_channel
 *
 * @author yzg
 * @date 2023-10-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_user_channel")
public class UserChannel extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 用户ID
     */
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
     * openId
     */
    private String openId;
    /**
     * 信息授权
     */
    private String reloadUser;
    /**
     * 首次访问所在城市
     */
    private String registerCityName;
    /**
     * 首次访问所在城市行政区号
     */
    private String registerCityCode;
    /**
     * 关注状态
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
     * 上次登录时间
     */
    private Date lastLoginDate;
    /**
     * 渠道
     */
    private String channel;
    /**
     * 平台标识
     */
    private Long platformKey;
}
