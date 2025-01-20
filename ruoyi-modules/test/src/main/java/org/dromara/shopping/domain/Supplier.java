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
 * 供应商对象 t_supplier
 *
 * @author yzg
 * @date 2023-10-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_supplier")
public class Supplier extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "supplier_id")
    private Long supplierId;
    /**
     * 供应商名称
     */
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
    private String status;
    /**
     * 删除标志
     */
    @TableLogic
    private String delFlag;
    /**
     * 备注
     */
    private String remark;
    /**
     * 预警邮箱
     */
    private String warningEmail;
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
