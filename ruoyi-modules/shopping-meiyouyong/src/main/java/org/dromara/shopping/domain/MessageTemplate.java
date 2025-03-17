package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 消息模板对象 t_message_template
 *
 * @author yzg
 * @date 2023-11-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_message_template")
public class MessageTemplate extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     *
     */
    @TableId(value = "template_id")
    private Long templateId;
    /**
     * 状态
     */
    private String status;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 模板标识
     */
    private String templateKey;
    /**
     * 模板名称
     */
    private String templateName;
    /**
     * 支持端
     */
    private String channel;
    /**
     * 关键字
     */
    private String keyword;

}
