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
 * 自定义首页业务对象
 *
 * @author yzg
 * @date 2023-08-07
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class PlatformCityIndexBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 行政编码
     */
    @NotBlank(message = "行政编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String adcode;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 跳转类型
     */
    @NotBlank(message = "跳转类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String toType;

    /**
     * 小程序ID
     */
    private String appId;

    /**
     * 页面地址
     */
    private String url;

    /**
     * 生效时间
     */
    private Date startTime;

    /**
     * 失效时间
     */
    private Date endTime;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 指定周几
     */
    @NotBlank(message = "指定周几不能为空", groups = { AddGroup.class, EditGroup.class })
    private String assignDate;

    /**
     * 周几能领
     */
    private String weekDate;


}
