package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 平台分享海报对象 t_platform_share_poster
 *
 * @author yzg
 * @date 2025-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_share_poster")
public class PlatformSharePoster extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 分享海报
     */
    private String sharePoster;
    /**
     * 状态
     */
    private String status;

    /**
     * 描述
     */
    private String shareTitle;
    /**
     * 排序
     */
    private Long sort;
}
