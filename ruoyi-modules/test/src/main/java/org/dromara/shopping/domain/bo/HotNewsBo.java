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
 * 热门搜索配置业务对象
 *
 * @author yzg
 * @date 2023-07-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class HotNewsBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long newsId;

    /**
     * 显示名称
     */
    @NotBlank(message = "显示名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String newsName;

    /**
     * 排序，从小到大
     */
    @NotNull(message = "排序，从小到大不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long newsRank;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long platformKey;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;

    /**
     * 生效时间
     */
    @NotNull(message = "生效时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date startTime;

    /**
     * 失效时间
     */
    @NotNull(message = "失效时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date endTime;

    /**
     * 指定星期
     */
    @NotBlank(message = "指定星期不能为空", groups = {AddGroup.class, EditGroup.class})
    private String assignDate;

    /**
     * 展示城市
     */
    @NotBlank(message = "展示城市不能为空", groups = {AddGroup.class, EditGroup.class})
    private String showCity;

    /**
     * 展示星期
     */
    @NotBlank(message = "展示星期不能为空")
    private String weekDate;

    /**
     * 支持端
     */
    private String supportChannel;


}
