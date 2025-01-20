package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 订单身份信息业务对象
 * @author yzg
 * @date 2023-09-22
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderIdcardBo extends BaseEntity {

    /**
     * 此表ID
     */
    @NotNull(message = "此表ID不能为空", groups = { EditGroup.class })
    private Long orderCardId;

    /**
     * 订单号
     */
    @NotNull(message = "订单号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long number;

    /**
     * 真实姓名
     */
    @NotBlank(message = "真实姓名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String name;

    /**
     * 证件类型0-身份证 1-护照 2-港澳台居民居住证 3-户口簿
     */
    @NotBlank(message = "证件类型0-身份证 1-护照 2-港澳台居民居住证 3-户口簿不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cardType;

    /**
     * 证件号
     */
    @NotBlank(message = "证件号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String idCard;

    /**
     * 与t_order表相同字段一致
     */
    @NotBlank(message = "与t_order表相同字段一致不能为空", groups = { AddGroup.class, EditGroup.class })
    private String orderType;


}
