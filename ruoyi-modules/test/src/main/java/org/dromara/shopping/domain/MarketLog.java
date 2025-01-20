package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 奖励发放记录对象 t_market_log
 *
 * @author yzg
 * @date 2023-10-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_market_log")
public class MarketLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "log_id")
    private Long logId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     *
     */
    private Long marketId;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 状态
     */
    private String status;
    /**
     * 领取时间
     */
    private Date receiveDate;
    /**
     * 奖励类型
     */
    private String rewardType;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 优惠券id
     */
    private Long couponId;

    /**
     * 支持端
     */
    private String supportChannel;

}
