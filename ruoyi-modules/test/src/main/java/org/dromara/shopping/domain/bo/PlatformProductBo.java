package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Map;

/**
 * 平台商品配置业务对象
 *
 * @author yzg
 * @date 2024-05-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformProductBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long platformProductId;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 平台名称
     */
    @NotBlank(message = "平台名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String platformName;

    /**
     * 产品ID
     */
    @NotNull(message = "产品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 配置状态（0未配置 1已配置）
     */
    @NotBlank(message = "配置状态（0未配置 1已配置）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    private String productType;

    private String supplierId;

    private String sellAmountBegin;

    private String sellAmountEnd;

    private String profitRateBegin;

    private String profitRateEnd;

    private String saleEndTime;

    private Map<String,Object> amountScaleList;

    private BigDecimal sellAmountScale;

    private String adjustPriceType;

    private BigDecimal otherAmountScale;

}
