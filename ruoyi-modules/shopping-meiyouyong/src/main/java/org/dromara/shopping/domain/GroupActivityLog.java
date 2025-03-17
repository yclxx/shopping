package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 拼团活动记录对象 t_group_activity_log
 *
 * @author yzg
 * @date 2024-10-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_group_activity_log")
public class GroupActivityLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 编号ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 活动编号
     */
    private Long activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 商品名称
     */
    private String productName;
    /**
     * 商品销售价
     */
    private BigDecimal sellingPrice;
    /**
     * 拼团价
     */
    private BigDecimal groupPrice;
    /**
     * 成团人数
     */
    private Long groupCount;
    /**
     * 拼团人数
     */
    private Long attendCount;
    /**
     * 拼团状态
     */
    private String groupStatus;
    /**
     * 拼团到期时间
     */
    private Date groupTime;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

    /**
     * 交易状态
     */
    private String state;

    private Long number;

    private String groupNumber;
}
