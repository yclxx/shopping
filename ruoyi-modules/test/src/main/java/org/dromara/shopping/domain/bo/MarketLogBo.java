package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 奖励发放记录业务对象
 *
 * @author yzg
 * @date 2023-10-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MarketLogBo extends BaseEntity {
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
     * 支持端
     */
    private String supportChannel;


}
