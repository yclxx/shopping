package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import com.ruoyi.common.core.web.domain.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 商户端菜单管理业务对象
 *
 * @author yzg
 * @date 2024-11-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class VerifierMenuBo extends TreeEntity<VerifierMenuBo> {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long menuId;

    /**
     * 名称
     */
    @NotBlank(message = "名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String menuName;

    /**
     * 角标
     */
    private String menuMark;

    /**
     * 图片
     */
    private String menuImage;

    /**
     * 排序
     */
    private Long menuRank;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 功能状态
     */
    @NotBlank(message = "功能状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String openStatus;

    /**
     * 显示页面
     */
    @NotBlank(message = "显示页面不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pagePath;

    /**
     * 跳转类型
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

}
