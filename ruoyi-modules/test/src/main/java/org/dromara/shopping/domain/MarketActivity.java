package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 营销活动对象 t_market_activity
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_market_activity")
public class MarketActivity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "activity_id")
    private Long activityId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 规则图片
     */
    private String ruleImg;
    /**
     * 详情图片
     */
    private String detailsImg;
    /**
     * 支持端
     */
    private String channel;
    /**
     * 排序
     */
    private Long sort;

}
