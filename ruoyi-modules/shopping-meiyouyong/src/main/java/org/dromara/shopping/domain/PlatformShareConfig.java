package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台分享配置对象 t_platform_share_config
 *
 * @author yzg
 * @date 2025-01-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_share_config")
public class PlatformShareConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 平台标识
     */
    @TableId(value = "platform_key", type = IdType.INPUT)
    private Long platformKey;
    /**
     * 分享标题
     */
    private String shareTitle;
    /**
     * 分享描述
     */
    private String shareName;
    /**
     * 分享图片
     */
    private String shareImage;

}
