package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 银联任务配置对象 t_unionpay_mission
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_unionpay_mission")
public class UnionpayMission extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 银联任务ID
     */
    @TableId(value = "up_mission_id")
    private Long upMissionId;
    /**
     * 银联任务组ID
     */
    private Long upMissionGroupId;
    /**
     * 银联任务名称
     */
    private String upMissionName;
    /**
     * 银联任务id
     */
    private String upMissionUpid;
    /**
     * 发放奖励产品id
     */
    private Long productId;
    /**
     * 状态  0-正常  1-停用
     */
    private String status;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 用户每日限参与次数
     */
    private Long userCountDay;
    /**
     * 用户每周限参与次数
     */
    private Long userCountWeek;
    /**
     * 用户每月限参与次数
     */
    private Long userCountMonth;
    /**
     * 用户活动期间限参与次数
     */
    private Long userCountActivity;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 备注说明
     */
    private String remark;

    /**
     * 交易类型
     */
    private String tranType;

    /**
     * 限制交易数量
     */
    private Long tranCount;

    /**
     * 单笔支付金额
     */
    private Long payAmount;

}
