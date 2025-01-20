package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 秒杀配置对象 t_grab_period
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_grab_period")
public class GrabPeriod extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 活动名称
     */
    private String grabPeriodName;
    /**
     * 状态（0正常 1停用）
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
     * 时间上方背景图
     */
    private String topBjImg;
    /**
     * 几点开始领取格式：HH:mm:ss
     */
    private String sellStartTime;
    /**
     * 几点结束,格式：HH:mm:ss
     */
    private String sellEndTime;
    /**
     * 周期: 0-每天，1-每周，2-指定日期
     */
    private String dateType;
    /**
     * date_type为1时：指定周几：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开；date_type为2时：指定日期格式yyyy-MM-dd，多个之间用英文逗号隔开
     */
    private String dateList;
    /**
     * 活动规则
     */
    private String description;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
