package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 内容分销内容方订单业务对象
 *
 * @author yzg
 * @date 2023-09-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class UnionPayContentOrderBo extends BaseEntity {

    /**
     * ID
     */
    private Long id;

    /**
     * 银联发券内容方AppId
     */
    private String unionPayAppId;

    /**
     * 银联发券订单号
     */
    private String unionPayOrderId;

    /**
     * 银联发券商品编号
     */
    private String unionPayProdId;

    /**
     * 银联发券交易时间
     */
    private String unionPayTxnTime;

    /**
     * 银联发券购买数量
     */
    private String unionPayPurQty;

    /**
     * 银联发券账号类型：0-手机号;1-qq号;2-微信号;3-其他类 型账号
     */
    private String unionPayProdAstIdTp;

    /**
     * 银联发券账号
     */
    private String unionPayProdAstId;

    /**
     * 银联发券状态
     */
    private String unionPayResultStatus;

    /**
     * 订单号
     */
    private Long number;


}
