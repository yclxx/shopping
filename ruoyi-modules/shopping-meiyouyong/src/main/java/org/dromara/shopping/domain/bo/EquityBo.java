package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 权益包业务对象
 *
 * @author yzg
 * @date 2023-06-06
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EquityBo extends BaseEntity {

    /**
     * 权益包ID
     */
    private Long equityId;

    /**
     * 权益包名称
     */
    @NotNull(message = "权益包名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String equityName;

    /**
     * 售卖价格
     */
    @NotNull(message = "售卖价格不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal sellAmount;

    /**
     * 展示开始时间
     */
    @NotNull(message = "展示开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    @NotNull(message = "展示结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date showEndDate;

    /**
     * 售卖开始时间
     */
    @NotNull(message = "售卖开始时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sellStartDate;

    /**
     * 售卖结束时间
     */
    @NotNull(message = "售卖结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sellEndDate;

    /**
     * 购买后权益有效期
     */
    @NotNull(message = "购买后权益有效期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long expiryDate;

    /**
     * 每日售卖数量,0为不限制
     */
    private Long dayCount;

    /**
     * 状态
     */
    private String status;

    /**
     * 规则图片
     */
    private String equityImg;

    private Long platformKey;
}
