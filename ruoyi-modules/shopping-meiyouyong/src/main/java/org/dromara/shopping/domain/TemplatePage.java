package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 落地页对象 t_template_page
 *
 * @author yzg
 * @date 2023-06-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_template_page")
public class TemplatePage extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "template_id")
    private Long templateId;
    /**
     * 落地页描述
     */
    private String templateName;
    /**
     * 显示标题
     */
    private String showTitle;
    /**
     * 页面标题
     */
    private String pageTitle;

}
