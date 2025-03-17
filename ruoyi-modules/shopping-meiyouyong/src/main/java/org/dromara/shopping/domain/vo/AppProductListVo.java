package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 商品列表视图对象
 *
 * @author yzgnet
 * @date 2023-09-16
 */
@Data
public class AppProductListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long shopId;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 商户Id
     */
    private Long commercialTenantId;

    /**
     * 排序：从小到大
     */
    private Long sort;

    /**
     * 距离 千米
     */
    private BigDecimal distance;

    /**
     * 距离 千米 字符串 解决微信端展示问题
     */
    private String distanceString;

    /**
     * 数据类型： 0-门店，1-商品
     */
    private String columnType;

    /**
     * 产品信息
     */
    private ProductVo productVo;

    private void setDistance(BigDecimal distance) {
        this.distance = distance;
        this.calculateDistance();
    }

    public void calculateDistance() {
        if (null != this.distance && this.distance.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal distance = this.distance.setScale(2, RoundingMode.DOWN);
            this.distanceString = distance.stripTrailingZeros().toPlainString();
        }
    }
}
