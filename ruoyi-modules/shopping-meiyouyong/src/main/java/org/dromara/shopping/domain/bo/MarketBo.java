package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 新用户营销业务对象
 *
 * @author yzg
 * @date 2023-10-18
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MarketBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long marketId;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "指定时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date dateSpecific;

    /**
     * 天数
     */
    @NotNull(message = "天数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long marketDay;

    /**
     * 奖励类型
     */
    @NotBlank(message = "奖励类型不能为空", groups = { AddGroup.class, EditGroup.class })
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
