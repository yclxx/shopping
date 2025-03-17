package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 联联市级城市业务对象
 *
 * @author yzg
 * @date 2023-09-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class LianlianCityBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long cityId;

    /**
     * 城市名称
     */
    @NotBlank(message = "城市名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cityName;

    /**
     * 城市区号
     */
    @NotBlank(message = "城市区号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cityCode;

    /**
     * 状态：0-可用，1-禁用
     */
    @NotBlank(message = "状态：0-可用，1-禁用不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;


}
