package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 领取记录对象 t_equity_record
 *
 * @author yzg
 * @date 2023-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_equity_record")
public class EquityRecord extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 权益包ID
     */
    private Long equityId;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品图片
     */
    private String productImg;
    /**
     * 商品价值
     */
    private BigDecimal productAmount;
    /**
     * 产品归属
     */
    private String equityType;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 状态
     */
    private String status;
    /**
     * 领取开始时间
     */
    private Date receiveStartDate;
    /**
     * 领取结束时间
     */
    private Date receiveEndDate;
    /**
     * 领取时间
     */
    private Date receiveDate;
    /**
     * 订单号
     */
    private Long number;

}
