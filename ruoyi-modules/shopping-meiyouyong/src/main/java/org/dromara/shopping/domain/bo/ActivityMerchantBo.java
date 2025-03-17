package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 活动商户号业务对象
 *
 * @author yzg
 * @date 2023-12-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityMerchantBo extends BaseEntity {

    /**
     * ID
     */
    //@NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 核销人员
     */
    //@NotNull(message = "核销人员不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    /**
     * 活动ID
     */
    //@NotNull(message = "活动ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

    /**
     * 商户号
     */
    //@NotBlank(message = "商户号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantNo;

    /**
     * 商户类型
     */
    //@NotBlank(message = "商户类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantType;

    /**
     * 状态
     */
    //@NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 结算方式
     */
    //@NotBlank(message = "结算方式不能为空", groups = { AddGroup.class, EditGroup.class })
    private String settlementWay;

    /**
     * 结算比例
     */
    //@NotBlank(message = "结算比例不能为空", groups = { AddGroup.class, EditGroup.class })
    private String settlement;

    /**
     * 备注
     */
    //@NotBlank(message = "备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String remark;

    /**
     * 收款方式（主扫1，2被扫）
     */
    //@NotBlank(message = "收款方式（主扫1，2被扫）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String paymentMethod;

    /**
     * 收单机构
     */
    //@NotBlank(message = "收单机构不能为空", groups = { AddGroup.class, EditGroup.class })
    private String acquirer;

    /**
     * 终端编号
     */
    //@NotBlank(message = "终端编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String terminalNo;


}
