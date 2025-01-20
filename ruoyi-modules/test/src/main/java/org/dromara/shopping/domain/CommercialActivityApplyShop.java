package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商户活动报名门店对象 t_commercial_activity_apply_shop
 *
 * @author yzg
 * @date 2024-04-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_commercial_activity_apply_shop")
public class CommercialActivityApplyShop extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 报名记录ID
     */
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
     * 门店ID
     */
    private Long shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 状态
     */
    private String status;

}
