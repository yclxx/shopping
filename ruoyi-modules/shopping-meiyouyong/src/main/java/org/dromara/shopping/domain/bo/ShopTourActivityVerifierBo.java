package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 巡检活动白名单业务对象
 *
 * @author yzg
 * @date 2024-04-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopTourActivityVerifierBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 巡检活动id
     */
    //@NotNull(message = "巡检活动id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tourActivityId;

    /**
     * 巡检人员id,bd
     */
    //@NotNull(message = "巡检人员id,bd不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    private Long verifierIds[];

    private String userName;


}
