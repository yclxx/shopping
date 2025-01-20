package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台商品配置对象 t_platform_product
 *
 * @author yzg
 * @date 2024-05-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_product")
public class PlatformProduct extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "platform_product_id")
    private Long platformProductId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 平台名称
     */
    private String platformName;
    /**
     * 产品ID
     */
    private Long productId;
    /**
     * 配置状态（0未配置 1已配置）
     */
    private String status;

}
