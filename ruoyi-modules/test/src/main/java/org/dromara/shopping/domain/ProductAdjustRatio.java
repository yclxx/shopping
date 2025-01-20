package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品调价比例对象 t_product_adjust_ratio
 *
 * @author yzg
 * @date 2024-05-30
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_adjust_ratio")
public class ProductAdjustRatio extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 调价比例id
     */
    @TableId(value = "ratio_id")
    private Long ratioId;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 调价类型  1-平台统一调价  2-分类调价
     */
    private String adjustType;
    /**
     * 栏目id   0为未分类
     */
    private Long categoryId;
    /**
     * 调价比例（%）
     */
    private BigDecimal adjustRatio;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
