package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 多平台类别对象 t_category_platform
 *
 * @author yzg
 * @date 2024-02-27
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_category_platform")
public class CategoryPlatform extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 类别名称
     */
    private String name;
    /**
     * 类别ID，对应category表，多个之间用逗号隔开
     */
    private String categoryIds;

}
