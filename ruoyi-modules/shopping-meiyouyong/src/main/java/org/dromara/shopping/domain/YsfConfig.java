package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 云闪付参数配置对象 t_ysf_config
 *
 * @author yzg
 * @date 2023-07-31
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_ysf_config")
public class YsfConfig extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    @TableId(value = "config_id")
    private Long configId;
    /**
     * 平台id
     */
    private Long platformId;
    /**
     * 参数名称
     */
    private String configName;
    /**
     * 参数键名
     */
    private String configKey;
    /**
     * 参数键值
     */
    private String configValue;
    /**
     * 是否全局
     */
    private String isAll;
    /**
     * 备注
     */
    private String remark;

}
