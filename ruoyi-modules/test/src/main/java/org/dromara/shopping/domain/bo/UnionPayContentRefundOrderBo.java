package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;

/**
 * 内容分销内容方退券订单业务对象
 *
 * @author yzg
 * @date 2023-09-23
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionPayContentRefundOrderBo extends BaseEntity {

    /**
     * ID
     */
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


}
