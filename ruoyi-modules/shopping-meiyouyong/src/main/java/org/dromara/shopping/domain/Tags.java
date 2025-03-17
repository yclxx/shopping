package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 标签对象 t_tags
 *
 * @author yzg
 * @date 2023-10-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_tags")
public class Tags extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "tags_id")
    private Long tagsId;
    /**
     * 名称
     */
    private String tagsName;
    /**
     * 类型
     */
    private String tagsType;

}
