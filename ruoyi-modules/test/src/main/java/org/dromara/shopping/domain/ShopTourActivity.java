package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 巡检活动对象 t_shop_tour_activity
 *
 * @author yzg
 * @date 2024-03-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_shop_tour_activity")
public class ShopTourActivity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 巡检活动id
     */
    @TableId(value = "tour_activity_id")
    private Long tourActivityId;
    /**
     * 巡检活动名称
     */
    private String tourActivityName;
    /**
     * 开始时间
     */
    private Date startDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 状态  0-正常  1-停用
     */
    private String status;
    /**
     * 可巡检人员：0-不限制，1-白名单限制
     */
    private String openType;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

}
