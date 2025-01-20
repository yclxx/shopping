package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 用户地址业务对象
 *
 * @author yzg
 * @date 2023-09-15
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UserAddressBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long userAddressId;

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long userId;

    /**
     * 联系人
     */
    @NotBlank(message = "联系人不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 联系电话
     */
    @NotBlank(message = "联系电话不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tel;

    /**
     * 是否默认 0-默认 1-不默认
     */
    @NotBlank(message = "是否默认不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isDefault;

    /**
     * 地址中文，省市县等，用空格隔开
     */
    @NotBlank(message = "地址中文，省市县等，用空格隔开不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * 县市code
     */
    private String areaId;

    /**
     * 详细地址（街道门牌号啥的，全地址需要address+address_info）
     */
    private String addressInfo;


}
