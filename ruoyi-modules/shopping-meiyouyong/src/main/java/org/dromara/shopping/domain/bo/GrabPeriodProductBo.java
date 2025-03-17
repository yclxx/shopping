package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 秒杀商品配置业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class GrabPeriodProductBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 秒杀配置ID
     */
    @NotNull(message = "秒杀配置ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long grabPeriodId;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 排序：从小到大
     */
    private Long sort;

    private String productIds;


}
