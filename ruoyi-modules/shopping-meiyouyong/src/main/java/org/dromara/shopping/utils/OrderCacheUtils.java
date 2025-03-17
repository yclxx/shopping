package org.dromara.shopping.utils;

/**
 * 订单缓存帮助类
 *
 * @author 25487
 */
public class OrderCacheUtils {

    /**
     * 获取用户未支付订单redisKey
     *
     * @param platformKey 平台标识
     * @param userId      用户ID
     * @param productId   产品ID
     * @return 缓存key
     */
    public static String getUsreOrderOneCacheKey(Long platformKey, Long userId, Long productId) {
        return "userOrders:" + platformKey + ":" + userId + ":" + productId;
    }


    /**
     * 获取用户未支付订单redisKey 补差订单
     *
     * @param platformKey 平台标识
     * @param userId      用户ID
     * @param number   囤券的订单ID
     * @return 缓存key
     */
    public static String getUserBxOrderOneCacheKey(Long platformKey, Long userId, Long number) {
        return "userOrders:" + platformKey + ":" + userId + ":" + number;
    }
}
