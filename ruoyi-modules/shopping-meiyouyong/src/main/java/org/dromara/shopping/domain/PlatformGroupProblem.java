package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户入群问题反馈对象 t_platform_group_problem
 *
 * @author yzg
 * @date 2024-02-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_group_problem")
public class PlatformGroupProblem extends BaseEntity {

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
     * 用户编号
     */
    private Long userId;
    /**
     * 反馈内容
     */
    private String content;

}
