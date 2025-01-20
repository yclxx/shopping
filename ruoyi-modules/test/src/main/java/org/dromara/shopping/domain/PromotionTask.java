package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 推广任务对象 t_promotion_task
 *
 * @author yzg
 * @date 2023-11-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_promotion_task")
public class PromotionTask extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "task_id")
    private Long taskId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 活动性质
     */
    private String activityNature;
    /**
     * 状态
     */
    private String status;
    /**
     * 规则图片
     */
    private String ruleImage;
    /**
     * 推广图片
     */
    private String image;
    /**
     * 背景图片
     */
    private String backgroundImage;
    /**
     * 展示城市
     */
    private String showCity;
    /**
     * 排序
     */
    private String sort;

}
