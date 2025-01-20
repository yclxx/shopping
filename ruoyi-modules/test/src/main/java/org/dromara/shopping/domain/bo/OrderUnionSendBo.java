package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 银联分销订单卡券业务对象
 *
 * @author yzg
 * @date 2023-08-08
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class OrderUnionSendBo extends BaseEntity {

    /**
     * 订单号
     */
    private Long number;

    /**
     * 商品订单号
     */
    private String prodTn;

    /**
     * 卡券类型 0-仅券码;1-券码+券密;2-短链;3-直充
     */
    private String prodTp;

    /**
     * 券码流水号
     */
    private String bondSerlNo;

    /**
     * 券码
     */
    private String bondNo;

    /**
     * 券密
     */
    private String bondEncNo;

    /**
     * 生效时间
     */
    private Date effectDtTm;

    /**
     * 过期时间
     */
    private Date exprDtTm;

    /**
     * 购买须知
     */
    private String cusIstr;

    /**
     * 退券订单号
     */
    private String rfdTn;

    /**
     * 券状态
     */
    private String bondSt;


}
