package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.util.List;

/**
 * 巡检记录业务对象
 *
 * @author yzg
 * @date 2024-03-06
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopTourLogBo extends BaseEntity {

    /**
     * 巡检记录ID
     */
    @NotNull(message = "巡检记录ID不能为空", groups = { EditGroup.class })
    private Long tourLogId;

    /**
     * 巡检ID
     */
    @NotNull(message = "巡检ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long tourId;

    /**
     * 操作人员ID
     */
    @NotNull(message = "操作人员ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    /**
     * 操作类型  1-预约  2-提交巡检  3-取消预约  4-取消巡检  5-审核通过 6-审核拒绝  7-预约过期
     */
    @NotBlank(message = "操作类型  1-预约  2-提交巡检  3-取消预约  4-取消巡检  5-审核通过 6-审核拒绝  7-预约过期不能为空", groups = { AddGroup.class, EditGroup.class })
    private String operType;

    /**
     * 门店ID
     */
    @NotNull(message = "门店ID不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long shopId;

    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shopName;

    /**
     * 门店地址
     */
    @NotBlank(message = "门店地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * 商户管理员手机号
     */
    @NotBlank(message = "商户管理员手机号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String adminMobile;

    /**
     * 门店状态  0-正常  1-异常
     */
    @NotBlank(message = "门店状态  0-正常  1-异常不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shopStatus;

    /**
     * 巡检人员和门店合影
     */
    @NotBlank(message = "巡检人员和门店合影不能为空", groups = { AddGroup.class, EditGroup.class })
    private String verifierImage;

    /**
     * 物料照片
     */
    @NotBlank(message = "物料照片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsImage;

    /**
     * 门店照片
     */
    @NotBlank(message = "门店照片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shopImage;

    /**
     * 巡检备注
     */
    @NotBlank(message = "巡检备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tourRemark;

    /**
     * 原始商户号
     */
    @NotBlank(message = "原始商户号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String oldMerchantNo;

    /**
     * 商户类型  0-微信  1-云闪付  2-支付宝
     */
    @NotBlank(message = "商户类型  0-微信  1-云闪付  2-支付宝不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantType;

    /**
     * 商户号（变更）
     */
    @NotBlank(message = "商户号（变更）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantNo;

    /**
     * 是否继续参与活动  0-不参与  1-参与
     */
    @NotBlank(message = "是否继续参与活动  0-不参与  1-参与不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isActivity;

    /**
     * 门店是否关闭  0-关闭  1-运营中
     */
    @NotBlank(message = "门店是否关闭  0-关闭  1-运营中不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isClose;

    /**
     * 审核失败原因
     */
    @NotBlank(message = "审核失败原因不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkFailReason;

    private List<Long> shopsIds;

    private String beginDate;

    private String endDate;

}
