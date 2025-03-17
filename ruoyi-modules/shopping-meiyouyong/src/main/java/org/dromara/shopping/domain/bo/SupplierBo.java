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
 * 供应商业务对象
 *
 * @author yzg
 * @date 2023-10-11
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class SupplierBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long supplierId;

    /**
     * 供应商名称
     */
    @NotBlank(message = "供应商名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String supplierName;

    /**
     * 供应商地址
     */
    private String supplierAddress;

    /**
     * 对公账户
     */
    private String corporateAccount;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系电话
     */
    private String mobile;

    /**
     * 签约日期
     */
    private Date contractDate;

    /**
     * 发票类型
     */
    private String invoiceType;

    /**
     * 是否预警
     */
    private String warning;

    /**
     * 预警金额
     */
    private BigDecimal warningAmount;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 备注
     */
    private String remark;

    /**
     * 预警邮箱
     */
    private String warningEmail;

    private String nickName;

    private String password;

    /**
     * 营业执照
     */
    private String businessLicense;
    /**
     * 身份证正面
     */
    private String headUrl;
    /**
     * 身份证反面
     */
    private String backUrl;
    /**
     * 结算账户
     */
    private String settlementAccount;
    /**
     * 协议pdf
     */
    private String agreement;
    /**
     * 商品价格
     */
    private String productPrice;

    private String supplierImage;

    private String supplierShortName;
}
