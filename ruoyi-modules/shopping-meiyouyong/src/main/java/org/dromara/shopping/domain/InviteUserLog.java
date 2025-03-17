package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 邀请记录对象 t_invite_user_log
 *
 * @author yzg
 * @date 2023-08-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_invite_user_log")
public class InviteUserLog extends BaseEntity {

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
     * 被邀请用户ID
     */
    private Long inviteUserId;
    /**
     * 被邀请用户所在城市
     */
    private String inviteCityName;
    /**
     * 被邀请用户城市行政区号
     */
    private String inviteCityCode;
    /**
     * 订单号
     */
    private Long number;
    /**
     * 任务ID
     */
    private Long missionId;
    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

    /**
     * 支持端
     */
    private String supportChannel;
}
