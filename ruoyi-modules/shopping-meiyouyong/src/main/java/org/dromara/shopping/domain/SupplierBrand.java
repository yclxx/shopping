package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 供应商品牌对象 t_supplier_brand
 *
 * @author yzg
 * @date 2024-12-26
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_supplier_brand")
public class SupplierBrand extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "brand_id")
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
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
