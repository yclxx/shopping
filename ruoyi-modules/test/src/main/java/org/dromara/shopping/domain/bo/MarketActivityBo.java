package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 营销活动业务对象
 *
 * @author yzg
 * @date 2023-12-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MarketActivityBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long activityId;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long platformKey;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String name;

    /**
     * 图片
     */
    //@NotBlank(message = "图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String image;

    /**
     * 规则图片
     */
    //@NotBlank(message = "规则图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ruleImg;

    /**
     * 详情图片
     */
    //@NotBlank(message = "详情图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String detailsImg;

    /**
     * 支持端
     */
    @NotBlank(message = "支持端不能为空", groups = {AddGroup.class, EditGroup.class})
    private String channel;
    /**
     * 支持端
     */
    private Long sort;
}
