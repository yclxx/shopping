package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团活动对象 t_group_activity
 *
 * @author yzg
 * @date 2024-09-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_group_activity")
public class GroupActivity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 活动编号ID
     */
    @TableId(value = "activity_id")
    private Long activityId;
    /**
     * 活动名称
     */
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
     * 活动状态：0-未发布
     */
    private String activityStatus;
    /**
     * 拼团人数
     */
    private Long activityCount;
    /**
     * 拼团价格
     */
    private BigDecimal activityPrice;
    /**
     * 活动规则
     */
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
    private Long groupValid;
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 是否自动成团
     */
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
    private String groupTime;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

    private Long platformKey;

}
