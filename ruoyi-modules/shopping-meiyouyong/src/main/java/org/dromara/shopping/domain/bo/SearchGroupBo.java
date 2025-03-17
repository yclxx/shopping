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
 * 搜索彩蛋配置业务对象
 *
 * @author yzg
 * @date 2023-07-24
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SearchGroupBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long searchId;

    /**
     * 搜索内容
     */
    @NotBlank(message = "搜索内容不能为空", groups = { AddGroup.class, EditGroup.class })
    private String searchContent;

    /**
     * 商品编号
     */
    private Long productId;

    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转，6-页面指定位置
     */
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
     * 展示城市：ALL-全部、否则填城市行政区号，多个之间用英文逗号隔开
     */
    private String showCity;

    /**
     * 指定周几：0-不指定，1-指定周几
     */
    private String assignDate;

    /**
     * 周几能领：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    private String weekDate;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态（0正常 1停用）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 支持端
     */
    private String supportChannel;
}
