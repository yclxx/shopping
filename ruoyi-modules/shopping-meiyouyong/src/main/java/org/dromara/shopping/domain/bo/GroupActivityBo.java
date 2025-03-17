package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团活动业务对象
 *
 * @author yzg
 * @date 2024-09-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupActivityBo extends BaseEntity {

    /**
     * 活动编号ID
     */
    @NotNull(message = "活动编号ID不能为空", groups = { EditGroup.class })
    private Long activityId;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityName;

    /**
     * 活动简称
     */
    private String activityShortName;

    /**
     * 活动开始时间
     */
    private Date activityStartDate;

    /**
     * 活动结束时间
     */
    private Date activityEndDate;

    /**
     * 活动状态
     */
    @NotBlank(message = "活动状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityStatus;

    /**
     * 拼团人数
     */
    @NotNull(message = "拼团人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityCount;

    /**
     * 拼团价格
     */
    @NotNull(message = "拼团价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal activityPrice;

    /**
     * 活动规则
     */
    @NotBlank(message = "活动规则不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityRule;

    /**
     * 分享标题
     */
    private String shareTitle;

    /**
     * 分享图片
     */
    private String shareImage;

    /**
     * 拼团有效期
     */
    @NotNull(message = "拼团有效期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long groupValid;

    /**
     * 商品编号
     */
    @NotNull(message = "商品编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 是否自动成团
     */
    @NotBlank(message = "是否自动成团不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isGroup;

    /**
     * 活动分享海报
     */
    private String sharePoster;

    /**
     * 海报文案
     */
    private String sharePosterTitle;

    /**
     * 拼团时间
     */
    @NotBlank(message = "拼团时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private String groupTime;

    private Long platformKey;


}
