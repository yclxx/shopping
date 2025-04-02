package org.dromara.shopping.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 平台常见问题业务对象
 *
 * @author yzg
 * @date 2025-04-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformQaBo extends BaseEntity {

    /**
     * ID
     */
    private Long id;

    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 常见问题
     */
    private Long qaId;

    /**
     * 排序
     */
    private Long sort;


}
