package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 平台用户推广码业务对象
 *
 * @author yzg
 * @date 2024-05-27
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformPromotionCodeBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 推广码
     */
    private String promotionCode;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 姓名
     */
    private String name;

    /**
     * 所属机构
     */
    private String institution;

    /**
     * 备注
     */
    private String remark;
}
