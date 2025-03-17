package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商品价格配置对象 t_product_amount
 *
 * @author yzg
 * @date 2023-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_amount")
public class ProductAmount extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "amount_id")
    private Long amountId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    private BigDecimal externalProductSendValue;
    /**
     * 中奖概率
     */
    private BigDecimal drawProbability;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
