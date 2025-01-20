package org.dromara.shopping.domain.vo;

import lombok.Data;

/**
 * 标签视图对象
 *
 * @author yzg
 * @date 2023-10-09
 */
@Data
public class TagsProductVo {
    private static final long serialVersionUID = 1L;
    private Long tagsId;
    private Long productId;
}
