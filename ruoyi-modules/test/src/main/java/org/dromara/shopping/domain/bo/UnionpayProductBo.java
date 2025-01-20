package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import java.util.Date;

/**
 * 银联活动业务对象
 *
 * @author yzg
 * @date 2023-12-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionpayProductBo extends BaseEntity {

    /**
     * 活动编号
     */
    @NotBlank(message = "活动编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityNo;

    /**
     * 活动名称
     */
    private String activityNm;

    /**
     * 活动类型
     */
    private String activityTp;

    /**
     * 开始时间
     */
    private Date beginTime;

    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 限制类型
     */
    private String limitTp;

    /**
     * 活动标识
     */
    private String activityMark;

    /**
     * 活动总次数
     */
    private Long allCount;

    /**
     * 活动剩余次数
     */
    private Long allRemainCount;

    /**
     * 当天总次数
     */
    private Long dayCount;

    /**
     * 当天剩余次数
     */
    private Long dayRemainCount;


}
