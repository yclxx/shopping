package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 类别服务费对象 t_platform_service_tariffing
 *
 * @author yzg
 * @date 2024-06-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_service_tariffing")
public class PlatformServiceTariffing extends BaseEntity {

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
     * 栏目id
     */
    private Long categoryId;
    /**
     * 服务费率
     */
    private BigDecimal serviceTariffing;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
