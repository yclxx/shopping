package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 核销员供应商关联对象 t_verifier_supplier
 *
 * @author yzg
 * @date 2024-03-25
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_verifier_supplier")
public class VerifierSupplier extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 核销员id
     */
    private Long verifierId;
    /**
     * 供应商id
     */
    private Long supplierId;
    /**
     * 删除标志  0-存在  1-删除
     */
    @TableLogic
    private String delFlag;

}
