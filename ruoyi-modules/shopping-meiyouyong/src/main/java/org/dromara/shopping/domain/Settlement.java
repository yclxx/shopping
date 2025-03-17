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
 * 结算记录对象 t_settlement
 *
 * @author yzg
 * @date 2024-08-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_settlement")
public class Settlement extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "settlement_id")
    private Long settlementId;
    /**
     * 结算名称
     */
    private String reconciliationName;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 活动ID
     */
    private String activityId;
    /**
     * 数据文件
     */
    private String settlementMyFile;
    /**
     * 数据开始时间
     */
    private Date startTime;
    /**
     * 数据截止时间
     */
    private Date endTime;
    /**
     * 结算时间
     */
    private Date settlementTime;
    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;
    /**
     * 服务费率
     */
    private BigDecimal serviceRate;
    /**
     * 服务费
     */
    private BigDecimal serviceAmount;
    /**
     * 结算状态
     */
    private String status;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

    /**
     * 结算单类型：1-支付，2-退款
     */
    private String settlementType;
}
