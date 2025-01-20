package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 商户活动对象 t_commercial_activity
 *
 * @author yzg
 * @date 2024-03-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_commercial_activity")
public class CommercialActivity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 活动ID
     */
    @TableId(value = "activity_id")
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 活动说明
     */
    private String activityNote;
    /**
     * 活动银行,对应t_bank
     */
    private Long bankId;
    /**
     * 活动地区,存城市adcode,多个之间英文逗号隔开
     */
    private String activityArea;
    /**
     * 活动广告图片
     */
    private String activityImg;
    /**
     * 报名起始日期
     */
    private Date applyStartDate;
    /**
     * 报名截止日期
     */
    private Date applyEndDate;
    /**
     * 活动起始日期
     */
    private Date activityStartDate;
    /**
     * 活动截止日期
     */
    private Date activityEndDate;
    /**
     * 活动平台： 1-云闪付，2-微信，3-支付宝
     */
    private String activityPlatform;
    /**
     * 活动类型： 1-线下满减，2-线上收单
     */
    private String activityType;
    /**
     * 活动状态： 0-未开始，1-进行中，2-已结束
     */
    private String activityStatus;
    /**
     * 状态: 0-正常，1-停用
     */
    private String status;

}
