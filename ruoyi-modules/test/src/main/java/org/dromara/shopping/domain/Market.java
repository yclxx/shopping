package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 新用户营销对象 t_market
 *
 * @author yzg
 * @date 2023-10-18
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_market")
public class Market extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "market_id")
    private Long marketId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 名称
     */
    private String marketName;
    /**
     * 图片
     */
    private String marketImage;
    /**
     * 开始时间
     */
    private Date beginTime;
    /**
     * 结束时间
     */
    private Date endTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 指定时间
     */
    private Date dateSpecific;
    /**
     * 天数
     */
    private Long marketDay;
    /**
     * 奖励类型
     */
    private String rewardType;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 优惠券批次id
     */
    private Long actionId;
    /**
     * 支持端
     */
    private String supportChannel;

}
