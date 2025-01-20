package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 结算记录业务对象
 *
 * @author yzg
 * @date 2024-08-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SettlementBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long settlementId;

    /**
     * 结算名称
     */
    @NotBlank(message = "结算名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String reconciliationName;

    /**
     * 平台标识
     */
    @NotNull(message = "平台不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long platformKey;

    /**
     * 活动ID
     */
    @NotBlank(message = "活动ID不能为空", groups = {AddGroup.class, EditGroup.class})
    private String activityId;

    /**
     * 数据文件
     */
    private String settlementMyFile;

    /**
     * 数据开始时间
     */
    @NotNull(message = "数据开始时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date startTime;

    /**
     * 数据截止时间
     */
    @NotNull(message = "数据截止时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date endTime;

    /**
     * 结算时间
     */
    @NotNull(message = "结算时间不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date settlementTime;

    /**
     * 结算金额
     */
    private BigDecimal settlementAmount;

    /**
     * 服务费率
     */
    @NotNull(message = "服务费率不能为空", groups = {AddGroup.class, EditGroup.class})
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
     * 结算单类型：1-支付，2-退款
     */
    private String settlementType;

}
