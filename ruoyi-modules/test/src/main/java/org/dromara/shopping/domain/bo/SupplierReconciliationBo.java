package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.format.annotation.DateTimeFormat;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 供应商结算业务对象
 *
 * @author yzg
 * @date 2024-12-24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierReconciliationBo extends BaseEntity {

    /**
     * ID
     */
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
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /**
     * 截止时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
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

    private Long[] numbers;


}
