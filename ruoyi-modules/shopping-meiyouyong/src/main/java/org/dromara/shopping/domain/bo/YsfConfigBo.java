package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 云闪付参数配置业务对象
 *
 * @author yzg
 * @date 2023-07-31
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class YsfConfigBo extends BaseEntity {

    /**
     * 参数主键
     */
    @NotNull(message = "参数主键不能为空", groups = {EditGroup.class})
    private Long configId;

    /**
     * 平台id
     */
    @NotNull(message = "平台id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long platformId;

    /**
     * 参数名称
     */
    @NotBlank(message = "参数名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String configName;

    /**
     * 参数键名
     */
    @NotBlank(message = "参数键名不能为空", groups = {AddGroup.class, EditGroup.class})
    private String configKey;

    /**
     * 参数键值
     */
    @NotBlank(message = "参数键值不能为空", groups = {AddGroup.class, EditGroup.class})
    private String configValue;

    @NotBlank(message = "是否全局不能为空", groups = {AddGroup.class, EditGroup.class})
    private String isAll;


    /**
     * 备注
     */
    //@NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;
}
