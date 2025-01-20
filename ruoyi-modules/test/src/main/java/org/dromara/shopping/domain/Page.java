package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 页面对象 t_page
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_page")
public class Page extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 页面标识
     */
    private String pagePath;
    /**
     * 页面标题
     */
    private String pageName;
    /**
     * 所属页面
     */
    private String pageRemake;
    /**
     * 导航栏(含状态栏)背景色及透明度。16进制，前2位(8F)透明度，后六位(FFFFFF)颜色，仅对当前页有效
     */
    private String navBackgroundColor;
    /**
     * 导航栏主题颜色：black-黑色主题，white-白色主题
     */
    private String appletStyle;
    /**
     * 标题栏是否显示。0不显示，1显示，默认显示
     */
    private String appletTitleBarVisible;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 平台标识
     */
    private Long platformKey;
}
