package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 平台供应商业务对象
 *
 * @author yzg
 * @date 2025-01-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformSupplierBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 供应商id
     */
    @NotNull(message = "供应商id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long supplierId;

    /**
     * 售价最低价
     */
    private BigDecimal productMinPrice;

    /**
     * 售价最高价，小于等于0为不限制
     */
    private BigDecimal productMaxPrice;

    /**
     * 最低利润率
     */
    private BigDecimal productMinRate;


}
