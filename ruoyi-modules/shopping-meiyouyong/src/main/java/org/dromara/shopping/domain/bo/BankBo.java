package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 银行业务对象
 *
 * @author yzg
 * @date 2024-04-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BankBo extends BaseEntity {

    /**
     * 银行ID
     */
    @NotNull(message = "银行ID不能为空", groups = { EditGroup.class })
    private Long bankId;

    /**
     * 银行名称
     */
    @NotBlank(message = "银行名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankName;

    /**
     * 银行logo
     */
    @NotBlank(message = "银行logo不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankLogo;

    /**
     * 简称
     */
    @NotBlank(message = "简称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bankShortName;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 英文简称
     */
    private String englishAbbreviation;
}
