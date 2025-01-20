package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户订阅对象 t_send_dy_info
 *
 * @author yzg
 * @date 2023-12-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_send_dy_info")
public class SendDyInfo extends BaseEntity {

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
     * 用户ID
     */
    private Long userId;
    /**
     * 用户openId
     */
    private String openId;
    /**
     * 订阅模板ID
     */
    private String tmplId;
    /**
     * 状态
     */
    private String status;
    /**
     * 次数
     */
    private Long dyCount;

}
