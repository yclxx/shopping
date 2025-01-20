package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 门店商户号业务对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopMerchantBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 门店ID
     */
    @NotNull(message = "门店ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long shopId;

    /**
     * 商户号
     */
    private String merchantNo;

    /**
     * 终端号
     */
    private String terminalNo;

    /**
     * 商户类型（0-微信 1-云闪付 2-支付宝）
     */
    @NotBlank(message = "商户类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantType;

    /**
     * 收款方式
     */
    private String paymentMethod;
    /**
     * 收单机构
     */
    private String acquirer;
    /**
     * 结算方式
     */
    private String settlementWay;
    /**
     * 结算比例
     */
    private String settlement;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 商编截图
     */
    private String merchantImg;

    /**
     * 是否邮储商编
     */
    private String ycMerchant;

    private String isUpdate;

    private String oldMerchantNo;
}
