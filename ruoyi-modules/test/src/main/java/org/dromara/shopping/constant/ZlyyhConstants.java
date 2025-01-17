package org.dromara.shopping.constant;

/**
 * 通用常量信息
 *
 * @author Lion Li
 */
public interface ZlyyhConstants {
    /**
     * 云闪付基础访问令牌缓存key
     */
    String BACKEND_TOKEN_REDIS_KEY = "BackendToken";
    /**
     * 用户真实定位城市，主要用于需要校验用户位置才能参与活动的需求
     */
    String AD_CODE = "adcode";
    /**
     * 用户真实定位城市名称
     */
    String CITY_NAME = "cityname";
    /**
     * 用户选择的城市，适用于根据城市查询对应数据
     */
    String CITY_CODE = "citycode";
    /**
     * 时间戳
     */
    String TIMESTAMP = "timestamp";
    /**
     * adcode与时间戳的签名
     */
    String SIGN ="signature";
    /**
     * 门店缓存key
     */
    String SHOP_GEO_CACHE_KEY = "shopGeoCacheKey";

    String SEND_COUPON_MQ_COUNT = "sendCouponMqCount";
    /**
     * 订单发券次数缓存
     */
    String SEND_COUPON_NUMBER = "OrderSendCouponNumber";
    /**
     * 订单失败次数缓存
     */
    String YSF_ORDER_ERROR_NUM = "ysfOrderErrorNum";
    /**
     * 邀请新用户判定时间天数
     */
    String INVITE_USER_DAY = "inviteUserDay";
    /**
     * 退款订单缓存key
     */
    String REFUND_ORDER_CACHE_KEY = "refundOrder:";

    /**
     * 上海外服支付商户号，固定-5
     */
    Long FSG_PAY_MERCHANT_ID = -5L;

    String BASE_PLATFORM_INFO_CHANNEL = "baseInfo";
}
