package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 银行费率业务对象
 *
 * @author yzg
 * @date 2024-05-29
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BankRateBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long bankRateId;

    /**
     * 银行ID
     */
    @NotNull(message = "银行ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long bankId;

    /**
     * 费率
     */
    @NotNull(message = "费率不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal rate;

    /**
     * 期数
     */
    private String stagesNumber;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    private String installmentBank;


}
