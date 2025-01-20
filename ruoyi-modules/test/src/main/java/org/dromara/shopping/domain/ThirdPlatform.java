package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 第三方平台信息配置对象 t_third_platform
 *
 * @author yzg
 * @date 2024-03-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_third_platform")
public class ThirdPlatform extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * appId
     */
    private String appId;
    /**
     * 密钥
     */
    private String secret;
    /**
     * 类型
     */
    private String type;
    /**
     * 平台名称
     */
    private String appName;
    /**
     * 状态：0-正常，1-禁用
     */
    private String status;

}
