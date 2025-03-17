package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 商户合同对象 t_commercial_tenant_agreement
 *
 * @author yzg
 * @date 2024-09-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_commercial_tenant_agreement")
public class CommercialTenantAgreement extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 采购商合同ID
     */
    @TableId(value = "agreement_id")
    private Long agreementId;
    /**
     * 合同名称
     */
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
     * 金额（单位：元）
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
    private Long commercialTenantId;

}
