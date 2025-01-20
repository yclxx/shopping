package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 商品规则业务对象
 *
 * @author yzg
 * @date 2024-06-24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformProductRuleBo extends BaseEntity {

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { EditGroup.class })
    private Long platformKey;

    /**
     * 供应商
     */
    private String supportSupplier;

    /**
     * 类别
     */
    private String categoryIds;

    /**
     * 最低价
     */
    private BigDecimal productMinPrice;

    /**
     * 最高价
     */
    private BigDecimal productMaxPrice;

    /**
     * 最低利润率
     */
    private BigDecimal productMinRate;


}
