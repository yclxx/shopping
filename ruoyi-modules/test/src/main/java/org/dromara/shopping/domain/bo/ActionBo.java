package org.dromara.shopping.domain.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 优惠券批次业务对象
 *
 * @author yzg
 * @date 2023-10-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ActionBo extends BaseEntity {

    /**
     * 批次ID
     */
    @NotNull(message = "批次ID不能为空", groups = {EditGroup.class})
    private Long actionId;

    /**
     * 批次号
     */
    @NotBlank(message = "批次号不能为空", groups = {AddGroup.class, EditGroup.class})
    private String actionNo;

    /**
     * 批次描述
     */
    private String actionNote;

    /**
     * 优惠券名称
     */
    @NotBlank(message = "优惠券名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String couponName;

    /**
     * 优惠金额
     */
    //@NotNull(message = "优惠金额不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal couponAmount;

    /**
     * 最低消费金额
     */
    private BigDecimal minAmount;

    /**
     * 优惠券类型
     */
    @NotBlank(message = "优惠券类型不能为空", groups = {AddGroup.class, EditGroup.class})
    private String couponType;

    /**
     * 使用起始日期
     */
    private Date periodOfStart;

    /**
     * 使用有效截止日期
     */
    private Date periodOfValidity;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;

    /**
     * 优惠券数量
     */
    @NotNull(message = "优惠券数量不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long couponCount;

    /**
     * 优惠券描述
     */
    private String couponDescription;

    /**
     * 优惠券图片
     */
    private String couponImage;

    /**
     * 可兑换起始日期
     */
    private Date conversionStartDate;

    /**
     * 可兑换截止日期
     */
    private Date conversionEndDate;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long platformKey;

    private List<Long> productIds;

    /**
     * 是否自动支付
     */
    private String autoPay;

    /**
     * 落地页模板ID
     */
    private Long templateId;
    /**
     * 优惠券失效时间类型 0-指定失效时间，1-增加指定天数
     */
    private String couponExpiryType;
    /**
     * 优惠券失效时间类型为1（填写数字）
     */
    private String couponExpiryDate;
}
