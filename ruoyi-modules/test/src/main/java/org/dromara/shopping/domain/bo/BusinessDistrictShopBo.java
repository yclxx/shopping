package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.util.List;

/**
 * 商圈门店关联业务对象
 *
 * @author yzg
 * @date 2023-09-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BusinessDistrictShopBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 商圈ID
     */
    @NotNull(message = "商圈ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long businessDistrictId;

    /**
     * 门店ID
     */
    @NotNull(message = "门店ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long shopId;

    private List<Long> shopIds;
    private List<Long> businessDistrictIds;


}
