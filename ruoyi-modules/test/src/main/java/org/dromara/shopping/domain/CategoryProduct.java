package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 栏目商品关联对象 t_category_product
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_category_product")
public class CategoryProduct extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 栏目ID
     */
    private Long categoryId;
    /**
     * 商品ID或商户ID，具体根据栏目内容类型决定
     */
    private Long productId;
    /**
     * 排序：从小到大
     */
    private Long sort;

}
