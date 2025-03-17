package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.encrypt.annotation.EncryptField;
import org.dromara.common.mybatis.core.domain.BaseEntity;

/**
 * 订单身份信息对象 t_order_idcard
 *
 * @author yzg
 * @date 2023-09-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_order_idcard")
public class OrderIdcard extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 此表ID
     */
    @TableId(value = "order_card_id")
    private Long orderCardId;
    /**
     * 订单号
     */
    private Long number;
    /**
     * 真实姓名
     */
    private String name;
    /**
     * 证件类型0-身份证 1-护照 2-港澳台居民居住证 3-户口簿
     */
    private String cardType;
    /**
     * 证件号
     */
    @EncryptField()
    private String idCard;
    /**
     * 与t_order表相同字段一致
     */
    private String orderType;

}
