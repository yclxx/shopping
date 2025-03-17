package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 平台销售利润对象 t_platform_sale_profit
 *
 * @author yzg
 * @date 2024-09-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_sale_profit")
public class PlatformSaleProfit extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 月份
     */
    private String month;
    /**
     * 销售额
     */
    private BigDecimal salePrice;
    /**
     * 结算额
     */
    private BigDecimal settlementPrice;
    /**
     * 已核销销售额
     */
    private BigDecimal usedSalePrice;
    /**
     * 已核销结算额
     */
    private BigDecimal usedSettlementPrice;
    /**
     * 利润率
     */
    private BigDecimal usedProfit;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
