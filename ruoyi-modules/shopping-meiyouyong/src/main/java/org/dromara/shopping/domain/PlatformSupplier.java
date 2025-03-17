package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 平台供应商对象 t_platform_supplier
 *
 * @author yzg
 * @date 2025-01-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_supplier")
public class PlatformSupplier extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 售价最低价
     */
    private BigDecimal productMinPrice;
    /**
     * 售价最高价，小于等于0为不限制
     */
    private BigDecimal productMaxPrice;
    /**
     * 最低利润率
     */
    private BigDecimal productMinRate;

}
