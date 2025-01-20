package org.dromara.shopping.domain.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author 25487
 */
@Data
public class QueryShopProductBo {
    /** 纬度 */
    private BigDecimal latitude;
    /** 经度 */
    private BigDecimal longitude;
    /** 平台Id */
    private Long platformKey;
    /** 城市code */
    private String cityCode;
    /** 渠道 */
    private String supportChannel;
    /** 产品类别 */
    private Long categoryId;
    /** 排序方式 0-推荐排序 1-距离最近 */
    private String orderByType;
    /** 是否为 今日新品*/
    private String newFlag;
    /** 销售金额阈值*/
    private Long sellThreshold;
    /** 折扣阈值*/
    private Long discountThreshold;

}
