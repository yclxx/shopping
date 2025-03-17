package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 银联任务用户对象 t_unionpay_mission_user
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_unionpay_mission_user")
public class UnionpayMissionUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 银联任务用户ID
     */
    @TableId(value = "up_mission_user_id")
    private Long upMissionUserId;
    /**
     * 银联任务组ID
     */
    private Long upMissionGroupId;
    /**
     * 用户Id
     */
    private Long userId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 状态  0-正常  1-停用
     */
    private String status;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
