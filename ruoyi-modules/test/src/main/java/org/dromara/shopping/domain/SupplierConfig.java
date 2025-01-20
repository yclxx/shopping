package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商参数配置对象 t_supplier_config
 *
 * @author yzg
 * @date 2023-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_supplier_config")
public class SupplierConfig extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 参数主键
     */
    @TableId(value = "config_id")
    private Long configId;
    /**
     * 供应商ID
     */
    private Long supplierId;
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
     * 备注
     */
    private String remark;

}
