package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;


/**
 * 常见问题对象 t_qa
 *
 * @author yzg
 * @date 2025-04-02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_qa")
public class Qa extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 问题描述
     */
    private String issue;
    /**
     * 解决方案
     */
    private String solution;

}
