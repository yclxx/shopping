package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 优惠券批次对象 t_action
 *
 * @author yzg
 * @date 2023-10-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_action")
public class Action extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 批次ID
     */
    @TableId(value = "action_id")
    private Long actionId;
    /**
     * 批次号
     */
    private String actionNo;
    /**
     * 批次描述
     */
    private String actionNote;
    /**
     * 优惠券名称
     */
    private String couponName;
    /**
     * 优惠金额
     */
    private BigDecimal couponAmount;
    /**
     * 最低消费金额
     */
    private BigDecimal minAmount;
    /**
     * 优惠券类型
     */
    private String couponType;
    /**
     * 优惠券可使用起始日期
     */
    private Date periodOfStart;
    /**
     * 使用有效截止日期
     */
    private Date periodOfValidity;
    /**
     * 状态
     */
    private String status;
    /**
     * 优惠券数量
     */
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
    private Long platformKey;
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
