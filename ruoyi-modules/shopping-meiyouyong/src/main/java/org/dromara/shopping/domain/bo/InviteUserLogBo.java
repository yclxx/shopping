package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 邀请记录业务对象
 *
 * @author yzg
 * @date 2023-08-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class InviteUserLogBo extends BaseEntity {

    /**
     * ID
     */
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
     * 支持端
     */
    private String supportChannel;

}
