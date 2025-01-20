package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 活动商户号对象 t_activity_merchant
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_activity_merchant")
public class ActivityMerchant extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 核销人员
     */
    private Long verifierId;
    /**
     * 活动ID
     */
    private Long activityId;
    /**
     * 商户号
     */
    private String merchantNo;
    /**
     * 商户类型
     */
    private String merchantType;
    /**
     * 状态
     */
    private String status;
    /**
     * 结算方式
     */
    private String settlementWay;
    /**
     * 结算比例
     */
    private String settlement;
    /**
     * 备注
     */
    private String remark;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;
    /**
     * 收款方式（主扫1，2被扫）
     */
    private String paymentMethod;
    /**
     * 收单机构
     */
    private String acquirer;
    /**
     * 终端编号
     */
    private String terminalNo;

}
