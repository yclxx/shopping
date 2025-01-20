package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商结算对象 t_supplier_reconciliation
 *
 * @author yzg
 * @date 2024-12-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_supplier_reconciliation")
public class SupplierReconciliation extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "reconciliation_id")
    private Long reconciliationId;
    /**
     * 结算名称
     */
    private String reconciliationName;
    /**
     * 供应商ID
     */
    private Long supplierId;
    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 截止时间
     */
    private Date endTime;
    /**
     * 结算金额
     */
    private BigDecimal amount;
    /**
     * 结算时间
     */
    private Date settlementTime;
    /**
     * 结算状态
     */
    private String status;
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
