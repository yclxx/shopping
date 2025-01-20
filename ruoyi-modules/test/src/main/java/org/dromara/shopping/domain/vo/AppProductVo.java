package org.dromara.shopping.domain.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品信息
 *
 * @author 25487
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class AppProductVo extends ProductVo {

    /**
     * 用户购买状态：0-可购买 1-已达抢购上限
     */
    private String userProductStatus = "0";

    /**
     * 商品售卖状态：0-正常售卖，1-未开始，2-已抢完，3-已结束 4-不在活动城市
     */
    private String productStatus = "0";

    /**
     * 排序 从小到大
     */
    private Long sort = 0L;

    /**
     * 今日已抢数量
     */
    private Long daySoldCount = 0L;
    /**
     * 本周已抢数量
     */
    private Long weekSoldCount = 0L;
    /**
     * 本月已抢数量
     */
    private Long monthSoldCount = 0L;
    /**
     * 累计已抢数量
     */
    private Long totalSoldCount = 0L;

    /**
     * 不可抢原因
     */
    private String tipMsg = "";
}
