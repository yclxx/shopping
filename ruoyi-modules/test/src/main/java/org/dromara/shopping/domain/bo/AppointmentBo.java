package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 预约信息业务对象
 *
 * @author yzg
 * @date 2024-06-28
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class AppointmentBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 套餐ID
     */
    private Long productSkuId;

    /**
     * 可预约时间
     */
    private Date appointmentDate;

    /**
     * 可预约数量
     */
    private Long appointmentCount;

    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 截止时间
     */
    private String jzTime;


}
