package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 银联任务组对象 t_unionpay_mission_group
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_unionpay_mission_group")
public class UnionpayMissionGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 任务组ID
     */
    @TableId(value = "up_mission_group_id")
    private Long upMissionGroupId;
    /**
     * 任务组名称
     */
    private String upMissionGroupName;
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
     * 银联任务组编号
     */
    private String upMissionGroupUpid;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
