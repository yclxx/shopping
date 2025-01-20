package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商户账户明细对象 t_commercial_tenant_account_detail
 *
 * @author yzg
 * @date 2024-09-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_commercial_tenant_account_detail")
public class CommercialTenantAccountDetail extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商户ID
     */
    private Long commercialTenantId;
    /**
     * 商户名称
     */
    private String commercialTenantName;
    /**
     * 交易金额 单位:元，加钱正数，扣钱负数
     */
    private BigDecimal amount;
    /**
     * 交易类型
     */
    private String detailType;
    /**
     * 状态
     */
    private String status;
    /**
     * 提现流水号
     */
    private String pushNumber;
    /**
     * 提现失败原因
     */
    private String failReason;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
