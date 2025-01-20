package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 内容分销内容方退券订单对象 t_union_pay_content_refund_order
 *
 * @author yzg
 * @date 2023-09-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_union_pay_content_refund_order")
public class UnionPayContentRefundOrder extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 银联分销内容方AppID
     */
    private String unionPayAppId;
    /**
     * 银联退券订单号
     */
    private String unionPayOrderId;
    /**
     * 银联退券商品编号
     */
    private String unionPayProdId;
    /**
     * 银联退券交易时间
     */
    private String unionPayTxnTime;
    /**
     * 银联退券状态：00-退券成功，20-退券中，10-退券失败
     */
    private String unionPayResultStatus;
    /**
     * 银联退券券码
     */
    private String unionPayBondNo;
    /**
     * 删除标志（0代表存在 2代表删除）
     */
    @TableLogic
    private String delFlag;

}
