package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户活动报名对象 t_commercial_activity_apply
 *
 * @author yzg
 * @date 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_commercial_activity_apply")
public class CommercialActivityApply extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "apply_id")
    private Long applyId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 商户ID
     */
    private Long commercialTenantId;
    /**
     * 品牌名称
     */
    private String commercialTenantName;
    /**
     * 商户名称
     */
    private String commercialTenantTitle;
    /**
     * 结算方式
     */
    private String meansOfPayments;
    /**
     * 结算比例
     */
    private Integer meansOfRatio;
    /**
     * 宣传形式
     */
    private String publicity;
    /**
     * 报名状态
     */
    private String applyStatus;
    /**
     * 拒绝理由
     */
    private String rejectCause;
    /**
     * 状态
     */
    private String status;

}
