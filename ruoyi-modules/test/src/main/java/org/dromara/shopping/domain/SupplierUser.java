package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商用户关联对象 t_supplier_user
 *
 * @author yzg
 * @date 2024-12-20
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_supplier_user")
public class SupplierUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 供应商编号
     */
    private Long supplierId;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
