package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 黑白名单对象 t_platform_product_rule_list
 *
 * @author yzg
 * @date 2024-06-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_product_rule_list")
public class PlatformProductRuleList extends BaseEntity {

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
     * 商品id
     */
    private Long productId;
    /**
     * 类型
     */
    private String listType;

}
