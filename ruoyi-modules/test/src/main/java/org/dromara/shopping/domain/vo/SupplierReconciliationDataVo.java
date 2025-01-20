package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class SupplierReconciliationDataVo {

    private Long number;

    private Long productId;

    private String productName;

    private Long userId;

    private String account;

    private Date payTime;

    private Date expireDate;

    private Date sendTime;

    private Date createTime;

    private BigDecimal totalAmount;

    private BigDecimal reducedPrice;

    private BigDecimal wantAmount;

    private BigDecimal outAmount;

    private BigDecimal itemPrice;
}
