package org.dromara.shopping.domain.bo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 常见问题业务对象
 *
 * @author yzg
 * @date 2025-04-02
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class QaBo extends BaseEntity {

    /**
     * ID
     */
    private Long id;

    /**
     * 问题描述
     */
    private String issue;

    /**
     * 解决方案
     */
    private String solution;


}
