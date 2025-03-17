package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 巡检商户号临时业务对象
 *
 * @author yzg
 * @date 2024-03-10
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopTourLsMerchantBo extends BaseEntity {

    /**
     * 巡检商户号临时表ID
     */
    @NotNull(message = "巡检商户号临时表ID不能为空", groups = { EditGroup.class })
    private Long tourMerchantLsId;

    /**
     * 巡检ID
     */
    @NotNull(message = "巡检ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tourId;

    /**
     * 巡检记录ID
     */
    @NotNull(message = "巡检记录ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tourLogId;

    /**
     * 巡检人员ID
     */
    @NotNull(message = "巡检人员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    /**
     * 门店ID
     */
    @NotNull(message = "门店ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long shopId;

    /**
     * 商户号
     */
    @NotBlank(message = "商户号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantNo;

    /**
     * 商户号类型  0-微信  1-云闪付  2-支付宝
     */
    @NotBlank(message = "商户号类型  0-微信  1-云闪付  2-支付宝不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantType;

    /**
     * 收款方式  1-住扫  2被扫
     */
    @NotBlank(message = "收款方式  1-住扫  2被扫不能为空", groups = { AddGroup.class, EditGroup.class })
    private String paymentMethod;

    /**
     * 收单机构
     */
    @NotBlank(message = "收单机构不能为空", groups = { AddGroup.class, EditGroup.class })
    private String acquirer;

    /**
     * 终端编号
     */
    @NotBlank(message = "终端编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String terminalNo;

    /**
     * 商编截图
     */
    @NotBlank(message = "商编截图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantImg;

    /**
     * 是否邮储商编  0-是  1-否
     */
    @NotBlank(message = "是否邮储商编  0-是  1-否不能为空", groups = { AddGroup.class, EditGroup.class })
    private String ycMerchant;

    /**
     * 是否修改  0-是  1-不是
     */
    @NotBlank(message = "是否修改  0-是  1-不是不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isUpdate;

    private List<ShopMerchantBo> shopMerchantBos;

    private String oldMerchantNo;

    private List<String> isUpdateList;

}
