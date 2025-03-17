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
 * 领取记录业务对象
 *
 * @author yzg
 * @date 2023-06-06
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class EquityRecordBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 权益包ID
     */
    @NotNull(message = "权益包ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long equityId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片
     */
    private String productImg;

    /**
     * 商品价值
     */
    private BigDecimal productAmount;

    /**
     * 产品归属
     */
    @NotBlank(message = "产品归属不能为空", groups = { AddGroup.class, EditGroup.class })
    private String equityType;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 领取开始时间
     */
    private Date receiveStartDate;

    /**
     * 领取结束时间
     */
    private Date receiveEndDate;

    /**
     * 领取时间
     */
    @NotNull(message = "领取时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date receiveDate;

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long number;


}
