package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 多平台栏目商品关联对象 t_category_platform_product
 *
 * @author yzg
 * @date 2024-02-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_category_platform_product")
public class CategoryPlatformProduct extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 多平台栏目ID
     */
    private Long categoryPlatformId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 排序：从小到大
     */
    private Long sort;

}
