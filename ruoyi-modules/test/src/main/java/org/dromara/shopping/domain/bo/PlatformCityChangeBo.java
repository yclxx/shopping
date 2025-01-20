package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 平台切换业务对象
 *
 * @author yzg
 * @date 2024-03-19
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformCityChangeBo extends BaseEntity {

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
     * 行政编码
     */
    @NotBlank(message = "行政编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String adcode;

    /**
     * 替换后平台
     */
    @NotNull(message = "替换后平台不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long changePlatformKey;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 生效时间
     */
    private Date startTime;

    /**
     * 失效时间
     */
    private Date endTime;


}
