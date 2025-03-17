package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.Date;

/**
 * 到货清单业务对象
 *
 * @author yzg
 * @date 2024-05-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductArrivalListBo extends BaseEntity {

    /**
     * 库存id
     */
    @NotNull(message = "库存id不能为空", groups = { EditGroup.class })
    private Long arrivalListId;

    /**
     * 产品名称
     */
    //@NotBlank(message = "产品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 日期
     */
    //@NotNull(message = "日期不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date listDate;

    /**
     * 数量
     */
    //@NotNull(message = "数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long qty;

    /**
     * 发货时间
     */
    //@NotNull(message = "发货时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sendTime;

    /**
     * 到货时间
     */
    //@NotNull(message = "到货时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date arriveTime;

    /**
     * 确认到货  0-是  1-否
     */
    //@NotBlank(message = "确认到货  0-是  1-否不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isArrive;

    private Long productId;

    private Long platformKey;

}
