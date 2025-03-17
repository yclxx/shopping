package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品规则对象 t_platform_product_rule
 *
 * @author yzg
 * @date 2024-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_product_rule")
public class PlatformProductRule extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 平台标识
     */
    @TableId(value = "platform_key")
    private Long platformKey;
    /**
     * 供应商
     */
    private String supportSupplier;
    /**
     * 类别
     */
    private String categoryIds;
    /**
     * 最低价
     */
    private BigDecimal productMinPrice;
    /**
     * 最高价
     */
    private BigDecimal productMaxPrice;
    /**
     * 最低利润率
     */
    private BigDecimal productMinRate;

}
