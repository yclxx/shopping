package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 银联活动对象 t_unionpay_product
 *
 * @author yzg
 * @date 2023-12-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_unionpay_product")
public class UnionpayProduct extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    @TableId(value = "activity_no", type = IdType.INPUT)
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
