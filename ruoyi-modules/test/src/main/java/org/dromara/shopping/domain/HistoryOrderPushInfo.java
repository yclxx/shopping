package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 历史订单取码记录对象 t_history_order_push_info
 *
 * @author yzg
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_history_order_push_info")
public class HistoryOrderPushInfo extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 订单号
     */
    private Long number;
    /**
     * 取码(充值)订单号
     */
    private String pushNumber;
    /**
     * 供应商订单号
     */
    private String externalOrderNumber;
    /**
     * 取码提交供应商产品编号（供应商提供）如遇面值类的，填面值
     */
    private String externalProductId;
    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    private BigDecimal externalProductSendValue;
    /**
     * 订单状态 0-处理中 1-成功 2-失败
     */
    private String status;
    /**
     * 交易失败原因
     */
    private String remark;

}
