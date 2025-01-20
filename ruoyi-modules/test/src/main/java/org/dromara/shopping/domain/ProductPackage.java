package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品券包对象 t_product_package
 *
 * @author yzg
 * @date 2023-06-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_package")
public class ProductPackage extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "package_id")
    private Long packageId;
    /**
     * 券包ID
     */
    private Long productId;
    /**
     * 包内产品
     */
    private Long extProductId;
    /**
     * 发放数量
     */
    private Long sendCount;
    /**
     * 状态
     */
    private String status;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
