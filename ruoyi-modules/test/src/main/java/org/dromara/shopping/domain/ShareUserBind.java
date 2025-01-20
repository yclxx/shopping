package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 分销关系绑定对象 t_share_user_bind
 *
 * @author yzg
 * @date 2024-10-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_share_user_bind")
public class ShareUserBind extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 分销员用户ID
     */
    private Long userId;
    /**
     * 被分销用户ID
     */
    private Long bindUserId;
    /**
     * 状态
     */
    private String status;
    /**
     * 失效时间
     */
    private Date endTime;
    /**
     * 平台标识
     */
    private Long platformKey;

}
