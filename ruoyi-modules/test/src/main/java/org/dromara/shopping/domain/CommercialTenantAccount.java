package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.*;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商户账户对象 t_commercial_tenant_account
 *
 * @author yzg
 * @date 2024-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_commercial_tenant_account")
public class CommercialTenantAccount extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 商户ID
     */
    @TableId(value = "commercial_tenant_id", type = IdType.INPUT)
    private Long commercialTenantId;
    /**
     * 商户名称
     */
    private String commercialTenantName;
    /**
     * 可提金额 单位:元
     */
    private BigDecimal balance;
    /**
     * 冻结金额 单位:元
     */
    private BigDecimal frozenBalance;
    /**
     * 已提金额 单位:元
     */
    private BigDecimal withdrawBalance;
    /**
     * 总金额 单位:元(可提金额+冻结金额+已提金额)
     */
    private BigDecimal totalBalance;
    /**
     * 乐观锁版本号
     */
    @Version
    private Long version;
    /**
     * 状态
     */
    private String status;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
