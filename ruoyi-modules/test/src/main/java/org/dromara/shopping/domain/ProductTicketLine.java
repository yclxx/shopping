package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 演出票种对象 t_product_ticket_line
 *
 * @author yzg
 * @date 2023-09-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_ticket_line")
public class ProductTicketLine extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 票种id
     */
    @TableId(value = "line_id")
    private Long lineId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 日期id
     */
    private Long sessionId;
    /**
     * 第三方id
     */
    private String otherId;
    /**
     * 票种名称
     */
    private String lineTitle;
    /**
     * 销售价格
     */
    private BigDecimal linePrice;
    /**
     * 结算价格
     */
    private BigDecimal lineSettlePrice;
    /**
     * 最大数量
     */
    private Long lineNumber;
    /**
     * 单次购买上限
     */
    private Long lineUpperLimit;
    /**
     * 状态
     */
    private String lineStatus;
    /**
     * 说明
     */
    private String lineDescription;
}
