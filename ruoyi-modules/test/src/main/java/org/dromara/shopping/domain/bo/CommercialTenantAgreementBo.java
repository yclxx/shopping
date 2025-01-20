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
 * 商户合同业务对象
 *
 * @author yzg
 * @date 2024-09-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class CommercialTenantAgreementBo extends BaseEntity {

    /**
     * 采购商合同ID
     */
    private Long agreementId;

    /**
     * 合同名称
     */
    @NotBlank(message = "合同名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String agreementName;

    /**
     * 合同时间
     */
    private Date agreementTime;

    /**
     * 合同地址
     */
    private String agreementUrl;

    /**
     * 金额（单位：分）
     */
    private BigDecimal amount;

    /**
     * 服务费率  百分比
     */
    private String serviceRate;

    /**
     * 签约日期
     */
    private Date contractDate;

    /**
     * 签约开始日期
     */
    private Date contractDateStart;

    /**
     * 签约结束日期
     */
    private Date contractDateEnd;

    /**
     * 商户id
     */
    @NotNull(message = "商户id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long commercialTenantId;


}
