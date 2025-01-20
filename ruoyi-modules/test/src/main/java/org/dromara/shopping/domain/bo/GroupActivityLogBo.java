package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团活动记录业务对象
 *
 * @author yzg
 * @date 2024-10-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GroupActivityLogBo extends BaseEntity {

    /**
     * 编号ID
     */
    @NotNull(message = "编号ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 活动编号
     */
    @NotNull(message = "活动编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

    /**
     * 活动名称
     */
    @NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityName;

    /**
     * 商品编号
     */
    @NotNull(message = "商品编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 商品销售价
     */
    @NotNull(message = "商品销售价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal sellingPrice;

    /**
     * 拼团价
     */
    @NotNull(message = "拼团价不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal groupPrice;

    /**
     * 成团人数
     */
    @NotNull(message = "成团人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long groupCount;

    /**
     * 拼团人数
     */
    @NotNull(message = "拼团人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long attendCount;

    /**
     * 拼团状态
     */
    @NotBlank(message = "拼团状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String groupStatus;

    /**
     * 拼团到期时间
     */
    @NotNull(message = "拼团到期时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date groupTime;


    /**
     * 交易状态
     */
    private String state;

    private Long number;

    private String groupNumber;

}
