package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 巡检奖励对象 t_shop_tour_reward
 *
 * @author yzg
 * @date 2024-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_shop_tour_reward")
public class ShopTourReward extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 巡检奖励id
     */
    @TableId(value = "tour_reward_id")
    private Long tourRewardId;
    /**
     * 巡检人员id
     */
    private Long verifierId;
    /**
     * 巡检次数
     */
    private Long count;
    /**
     * 巡检奖励  单位：分
     */
    private Long amount;
    /**
     * 发放状态  0-未发放  1-发放中  2-已发放
     */
    private String status;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
