package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

/**
 * 消息模板业务对象
 *
 * @author yzg
 * @date 2023-11-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class MessageTemplateBo extends BaseEntity {
    private Long templateId;
    private String status;
    private Long platformKey;
    private String templateKey;
    private String templateName;
    private String channel;
    private String keyword;
    private MultipartFile file;
}
