package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标签对象 t_tags
 * @author yzg
 * @date 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_tags_product")
public class TagsProduct extends BaseEntity {
    private static final long serialVersionUID=1L;
    private Long tagsId;
    private Long productId;
}
