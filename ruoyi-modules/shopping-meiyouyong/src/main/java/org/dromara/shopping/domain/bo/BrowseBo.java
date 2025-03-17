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
 * 浏览任务业务对象
 *
 * @author yzg
 * @date 2023-12-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BrowseBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long browseId;

    /**
     * 任务名称
     */
    @NotBlank(message = "任务名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String browseName;

    /**
     * 奖励说明
     */
    @NotBlank(message = "奖励说明不能为空", groups = { AddGroup.class, EditGroup.class })
    private String browseRemark;

    /**
     * 状态
     */
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
    @NotNull(message = "失效时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date endTime;

    /**
     * 展示城市
     */
    private String showCity;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 支持端
     */
    @NotBlank(message = "支持端不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supportChannel;

    /**
     * 指定周几
     */
    @NotBlank(message = "指定周几不能为空", groups = { AddGroup.class, EditGroup.class })
    private String assignDate;

    /**
     * 周几能领
     */
    private String weekDate;

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;


}
