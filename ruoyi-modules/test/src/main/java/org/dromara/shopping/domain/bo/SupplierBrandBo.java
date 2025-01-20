package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 供应商品牌业务对象
 *
 * @author yzg
 * @date 2024-12-26
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierBrandBo extends BaseEntity {

    /**
     * ID
     */
    private Long brandId;

    /**
     * 供应商编号
     */
    private Long supplierId;

    /**
     * 品牌商标
     */
    private String brandTrademark;

    /**
     * 授权书
     */
    private String authorizeLetter;


}
