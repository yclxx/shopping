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
 * 任务组对象 t_mission_group
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_mission_group")
public class MissionGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "mission_group_id")
    private Long missionGroupId;
    /**
     * 任务组名称
     */
    private String missionGroupName;
    /**
     * 状态
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
     * 规则图片
     */
    private String missionImg;
    /**
     * 默认背景图片
     */
    private String missionBgImg;
    /**
     * 规则按钮图片
     */
    private String regulationButton;
    /**
     * 任务组编号
     */
    private String missionGroupUpid;
    /**
     * 积点兑红包：0-不可兑换，1-可兑换
     */
    private String jdConvertRedPacket;

    private BigDecimal convertValue;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

    private String showCity;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
    /**
     *  是否开启每月专属活动日
     */
    private String startLuckyDay;
    /**
     * 专属活动日期 用;隔开
     */
    private String luckyDay;
    /**
     * 概率类型 0-正常 1-抽奖几次后变化 2-连续登录几天后变化
     */
    private String probabilityType;
    /**
     * 概率变化天数(或次数)
     */
    private String probabilityCount;
    /**
     * 概率类型不同时获取抽奖次数的商品id
     */
    private Long probabilityProductId;
}
