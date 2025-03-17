package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 类别服务费业务对象
 *
 * @author yzg
 * @date 2024-06-13
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformServiceTariffingBo extends BaseEntity {

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
     * 栏目id
     */
    @NotNull(message = "栏目id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long categoryId;

    /**
     * 服务费率
     */
    @NotNull(message = "服务费率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal serviceTariffing;


}
