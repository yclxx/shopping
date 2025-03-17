package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 品牌管理对象 t_brand
 *
 * @author yzg
 * @date 2023-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_brand")
public class Brand extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 品牌ID
     */
    @TableId(value = "brand_id")
    private Long brandId;
    /**
     * 品牌名称
     */
    private String brandName;
    /**
     * 品牌logo
     */
    private String brandImg;
    /**
     * 状态
     */
    private String status;
    /**
     * 排序
     */
    private Long sort;

}
