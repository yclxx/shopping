package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 订单数据统计（每天）业务对象
 *
 * @author yzg
 * @date 2023-07-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductComputeDayBo extends BaseEntity {

    /**
     *
     */
    private Long id;

    /**
     * 统计时间
     */
    private Date dayTime;

    private String dayTimes;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 城市行政区号
     */
    private String cityCode;

    /**
     * 城市
     */
    private String cityName;

    /**
     * 每个城市用户人数
     */
    private Long cityUserNumber;

    /**
     * 每个城市订单数量
     */
    private Long cityOrderNumber;


}
