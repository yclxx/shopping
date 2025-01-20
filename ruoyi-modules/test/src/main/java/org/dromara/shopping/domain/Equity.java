package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 权益包对象 t_equity
 *
 * @author yzg
 * @date 2023-06-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_equity")
public class Equity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 权益包ID
     */
    @TableId(value = "equity_id")
    private Long equityId;
    /**
     * 权益包名称
     */
    private String equityName;
    /**
     * 售卖价格
     */
    private BigDecimal sellAmount;
    /**
     * 展示开始时间
     */
    private Date showStartDate;
    /**
     * 展示结束时间
     */
    private Date showEndDate;
    /**
     * 售卖开始时间
     */
    private Date sellStartDate;
    /**
     * 售卖结束时间
     */
    private Date sellEndDate;
    /**
     * 购买后权益有效期
     */
    private Long expiryDate;
    /**
     * 每日售卖数量,0为不限制
     */
    private Long dayCount;
    /**
     * 状态
     */
    private String status;
    /**
     * 规则图片
     */
    private String equityImg;

    private Long platformKey;
}
