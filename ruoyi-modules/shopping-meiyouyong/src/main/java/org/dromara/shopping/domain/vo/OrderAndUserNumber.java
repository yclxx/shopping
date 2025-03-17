package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 自定义返回语句
 */
@Data
public class OrderAndUserNumber {
    private Long productId;
    private Long userNumber;
    private Long orderNumber;
    private BigDecimal money;
    private String cityCode;
    private String cityName;
}
