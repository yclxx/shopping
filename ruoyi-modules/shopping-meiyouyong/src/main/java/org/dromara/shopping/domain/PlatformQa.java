package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;


/**
 * 平台常见问题对象 t_platform_qa
 *
 * @author yzg
 * @date 2025-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_qa")
public class PlatformQa extends BaseEntity {

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
     * 常见问题
     */
    private Long qaId;
    /**
     * 排序
     */
    private Long sort;

}
