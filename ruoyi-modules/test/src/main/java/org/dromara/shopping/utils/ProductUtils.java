package org.dromara.shopping.utils;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.exception.ServiceException;
import com.ruoyi.common.core.utils.BeanCopyUtils;
import com.ruoyi.common.core.utils.DateUtils;
import com.ruoyi.common.core.utils.StringUtils;
import com.ruoyi.common.redis.utils.RedisUtils;
import com.ruoyi.common.satoken.utils.LoginHelper;
import com.ruoyi.zlyyh.domain.Order;
import com.ruoyi.zlyyh.domain.vo.AppProductVo;
import com.ruoyi.zlyyh.domain.vo.ProductGroupVo;
import com.ruoyi.zlyyh.domain.vo.ProductVo;
import com.ruoyi.zlyyh.enumd.DateType;
import com.ruoyi.zlyyh.utils.ZlyyhUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

/**
 * 产品相关帮助类
 *
 * @author 25487
 */
@Slf4j
public class ProductUtils {

    /**
     * 校验用户今日是否可购买
     *
     * @param productVo 商品信息
     * @param userId    用户ID
     */
    public static void checkUserCount(ProductVo productVo, Long userId) {
        // 校验用户是否达到参与上限
        if (productVo.getDayUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductIdRedisKey(productVo.getPlatformKey(), userId, productVo.getProductId(), DateType.DAY));
            if (count >= productVo.getDayUserCount()) {
                throw new ServiceException("今日已参与");
            }
        }
        if (productVo.getWeekUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductIdRedisKey(productVo.getPlatformKey(), userId, productVo.getProductId(), DateType.WEEK));
            if (count >= productVo.getWeekUserCount()) {
                throw new ServiceException("本周已达上限");
            }
        }
        if (productVo.getMonthUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductIdRedisKey(productVo.getPlatformKey(), userId, productVo.getProductId(), DateType.MONTH));
            if (count >= productVo.getMonthUserCount()) {
                throw new ServiceException("本月已达上限");
            }
        }
        if (productVo.getTotalUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductIdRedisKey(productVo.getPlatformKey(), userId, productVo.getProductId(), DateType.TOTAL));
            if (count >= productVo.getTotalUserCount()) {
                throw new ServiceException("活动累计上限");
            }
        }
    }

    /**
     * 校验商品组商品 用户今日是否可以购买
     */
    public static void checkProductGroupUserCount(ProductGroupVo productGroup, Long platformId, Long userId) {
        if (ObjectUtil.isEmpty(productGroup)) {
            return;
        }
        // 校验用户商品组选择是否达到参与上限
        String msgTip = productGroup.getProductGroupTip();
        if (productGroup.getDayUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductGroupIdRedisKey(platformId, userId, productGroup.getProductGroupId(), DateType.DAY));
            if (count >= productGroup.getDayUserCount()) {
                if (ObjectUtil.isNotEmpty(msgTip)) {
                    throw new ServiceException("今日" + msgTip);
                } else {
                    throw new ServiceException("今日已达参与上限");
                }

            }
        }
        if (productGroup.getWeekUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductGroupIdRedisKey(platformId, userId, productGroup.getProductGroupId(), DateType.WEEK));
            if (count >= productGroup.getWeekUserCount()) {
                if (ObjectUtil.isNotEmpty(msgTip)) {
                    throw new ServiceException("本周" + msgTip);
                } else {
                    throw new ServiceException("本周已达参与上限");
                }
            }
        }
        if (productGroup.getMonthUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductGroupIdRedisKey(platformId, userId, productGroup.getProductGroupId(), DateType.MONTH));
            if (count >= productGroup.getMonthUserCount()) {
                if (ObjectUtil.isNotEmpty(msgTip)) {
                    throw new ServiceException("本月" + msgTip);
                } else {
                    throw new ServiceException("本月已达参与上限");
                }
            }
        }
        if (productGroup.getTotalUserCount() > 0) {
            long count = RedisUtils.getAtomicValue(countByUserIdAndProductGroupIdRedisKey(platformId, userId, productGroup.getProductGroupId(), DateType.TOTAL));
            if (count >= productGroup.getTotalUserCount()) {
                if (ObjectUtil.isNotEmpty(msgTip)) {
                    throw new ServiceException(msgTip);
                } else {
                    throw new ServiceException("已达参与上限");
                }
            }
        }
    }

    /**
     * 校验用户今日是否可购买
     *
     * @param productVo 商品信息
     */
    public static R<ProductVo> checkProduct(ProductVo productVo, String cityCode) {
        AppProductVo appProductVo = null;
        if (productVo instanceof AppProductVo) {
            appProductVo = (AppProductVo) productVo;
        }
        if (null != productVo.getSellStartDate() && DateUtils.compare(productVo.getSellStartDate()) > 0) {
            if (null != appProductVo) {
                appProductVo.setProductStatus("1");
            }
            return R.fail("开始时间:" + DateUtils.parseDateToStr("yyyy-MM-dd HH:mm:ss", productVo.getSellStartDate()), null == appProductVo ? productVo : appProductVo);
        }
        if (null != productVo.getSellEndDate() && DateUtils.compare(productVo.getSellEndDate()) < 0) {
            if (null != appProductVo) {
                appProductVo.setProductStatus("3");
            }
            return R.fail("已下架", null == appProductVo ? productVo : appProductVo);
        }
        if (StringUtils.isNotBlank(productVo.getSellTime()) && productVo.getSellTime().contains("-")) {
            String[] split = productVo.getSellTime().split("-");
            if (split.length == 2) {
                String str1 = split[0];
                if (StringUtils.isNotBlank(str1) && str1.length() > 4) {
                    // 开始时间
                    String startTime = DateUtil.today() + " " + str1;
                    if (DateUtils.compare(DateUtil.parse(startTime)) > 0) {
                        if (null != appProductVo) {
                            appProductVo.setProductStatus("1");
                        }
                        return R.fail("开始时间：" + str1, null == appProductVo ? productVo : appProductVo);
                    }
                }
                String str2 = split[1];
                if (StringUtils.isNotBlank(str2) && str2.length() > 4) {
                    // 结束时间
                    String endTime = DateUtil.today() + " " + str2;
                    if (DateUtils.compare(DateUtil.parse(endTime)) < 0) {
                        if (null != appProductVo) {
                            appProductVo.setProductStatus("3");
                        }
                        return R.fail("今日已结束", null == appProductVo ? productVo : appProductVo);
                    }
                }
            }
        }
        if ("1".equals(productVo.getAssignDate()) && StringUtils.isNotBlank(productVo.getWeekDate()) && !productVo.getWeekDate().contains(DateUtil.dayOfWeek(new Date()) + "")) {
            String sendDate = sendWeek(productVo.getWeekDate());
            if (null != appProductVo) {
                appProductVo.setProductStatus("1");
            }
            return R.fail("每周" + sendDate + "开始", null == appProductVo ? productVo : appProductVo);
        }

        String dayCount = RedisUtils.getCacheObject(ZlyyhUtils.getProductDayCount() + productVo.getProductId());
        if (StringUtils.isNotBlank(dayCount) && "1".equals(dayCount)) {
            if (null != appProductVo) {
                appProductVo.setProductStatus("2");
            }
            return R.fail("今日名额已抢完", null == appProductVo ? productVo : appProductVo);
        }
        if ("0".equals(productVo.getProductAffiliation())) {
            if (productVo.getDayCount() > 0 || null != appProductVo) {
                long count = RedisUtils.getAtomicValue(countByProductIdRedisKey(productVo.getPlatformKey(), productVo.getProductId(), DateType.DAY));
                if (null != appProductVo) {
                    appProductVo.setDaySoldCount(count);
                }
                //TODO 需要新增活动日的判断
                if (ObjectUtil.isNotEmpty(productVo.getStartLuckyDay()) && "1".equals(productVo.getStartLuckyDay()) && ObjectUtil.isNotEmpty(productVo.getLuckyDay()) && ObjectUtil.isNotEmpty(productVo.getLuckyDayCount())){
                    //查询今日是否为活动日
                    Boolean daysContain = DateUtils.daysContain(productVo.getLuckyDay());
                    if (daysContain){
                        //如果是活动日将dayCount修改为活动日的数量
                        if (productVo.getLuckyDayCount() > 0 && count >= productVo.getLuckyDayCount()){
                            if (null != appProductVo) {
                                appProductVo.setProductStatus("2");
                            }
                            return R.fail("今日名额已抢完", null == appProductVo ? productVo : appProductVo);

                        }
                    } else if (productVo.getDayCount() > 0 && count >= productVo.getDayCount()) {
                        //不是活动日的时候按照原本判断
                        if (null != appProductVo) {
                            appProductVo.setProductStatus("2");
                        }
                        return R.fail("今日名额已抢完", null == appProductVo ? productVo : appProductVo);
                    }
                } else if (productVo.getDayCount() > 0 && count >= productVo.getDayCount()) {
                    //非活动日商品判断
                    if (null != appProductVo) {
                        appProductVo.setProductStatus("2");
                    }
                    return R.fail("今日名额已抢完", null == appProductVo ? productVo : appProductVo);
                }
            }
            if (productVo.getWeekCount() > 0 || null != appProductVo) {
                long count = RedisUtils.getAtomicValue(countByProductIdRedisKey(productVo.getPlatformKey(), productVo.getProductId(), DateType.WEEK));
                if (null != appProductVo) {
                    appProductVo.setWeekSoldCount(count);
                }
                if (productVo.getWeekCount() > 0 && count >= productVo.getWeekCount()) {
                    if (null != appProductVo) {
                        appProductVo.setProductStatus("2");
                    }
                    return R.fail("本周名额已抢完", null == appProductVo ? productVo : appProductVo);
                }
            }
            if (productVo.getMonthCount() > 0 || null != appProductVo) {
                long count = RedisUtils.getAtomicValue(countByProductIdRedisKey(productVo.getPlatformKey(), productVo.getProductId(), DateType.MONTH));
                if (null != appProductVo) {
                    appProductVo.setMonthSoldCount(count);
                }
                if (productVo.getMonthCount() > 0 && count >= productVo.getMonthCount()) {
                    if (null != appProductVo) {
                        appProductVo.setProductStatus("2");
                    }
                    return R.fail("本月名额已抢完", null == appProductVo ? productVo : appProductVo);
                }
            }
            if (productVo.getTotalCount() > 0 || null != appProductVo) {
                long count = RedisUtils.getAtomicValue(countByProductIdRedisKey(productVo.getPlatformKey(), productVo.getProductId(), DateType.TOTAL));
                if (null != appProductVo) {
                    appProductVo.setTotalSoldCount(count);
                }
                if (productVo.getTotalCount() > 0 && count >= productVo.getTotalCount()) {
                    if (null != appProductVo) {
                        appProductVo.setProductStatus("2");
                    }
                    return R.fail("已抢完", null == appProductVo ? productVo : appProductVo);
                }
            }
        }
        if ("1".equals(productVo.getCheckPayCity()) && StringUtils.isNotBlank(productVo.getShowCity()) && !"ALL".equalsIgnoreCase(productVo.getShowCity())) {
            if (StringUtils.isBlank(cityCode)) {
                if (null != appProductVo) {
                    appProductVo.setProductStatus("4");
                }
                return R.fail("未获取到您的位置信息,请确认是否开启定位服务!", null == appProductVo ? productVo : appProductVo);
            }
            if (!productVo.getShowCity().contains(cityCode)) {
                if (null != appProductVo) {
                    appProductVo.setProductStatus("4");
                }
                Long userId = null;
                try {
                    userId = LoginHelper.getUserId();
                } catch (Exception ignored) {
                }
                log.info("用户Id：{},定位城市:{},活动参与城市:{}", userId, cityCode, productVo.getShowCity());
                return R.fail("您当前所在位置不在活动参与范围!", null == appProductVo ? productVo : appProductVo);
            }
        }
        return R.ok(null == appProductVo ? productVo : appProductVo);
    }

    /**
     * 校验产品是否已参与
     *
     * @param pv          产品信息
     * @param userId      用户ID
     * @param platformKey 平台标识
     * @return 校验后产品
     */
    public static ProductVo getProductVoCheck(ProductVo pv, Long userId, Long platformKey, String cityCode, boolean returnAppProduct) {
        // 校验是否抢完
        AppProductVo appProductVo = null;
        if (returnAppProduct) {
            if (!(pv instanceof AppProductVo)) {
                appProductVo = BeanCopyUtils.copy(pv, AppProductVo.class);
            }
        }
        R<ProductVo> checkProductCountResult;
        if (null != appProductVo) {
            checkProductCountResult = ProductUtils.checkProduct(appProductVo, cityCode);
        } else {
            checkProductCountResult = ProductUtils.checkProduct(pv, cityCode);
        }
        ProductVo data = checkProductCountResult.getData();
        if (data instanceof AppProductVo) {
            appProductVo = (AppProductVo) data;
            if ("0".equals(appProductVo.getProductStatus()) && null != userId && userId > 0) {
                // 校验是否有订单，有订单直接返回
                String cacheObject = RedisUtils.getCacheObject(OrderCacheUtils.getUsreOrderOneCacheKey(platformKey, userId, appProductVo.getProductId()));
                ProductUtils.checkOrder(userId, appProductVo, cacheObject);
            }
            return appProductVo;
        }
        return data;
    }

    public static void checkOrder(Long userId, AppProductVo appProductVo, String cacheObject) {
        if (StringUtils.isNotBlank(cacheObject)) {
            Order order = RedisUtils.getCacheObject(cacheObject);
            if (null != order && "0".equals(order.getStatus())) {
                appProductVo.setUserProductStatus("0");
            } else {
                try {
                    ProductUtils.checkUserCount(appProductVo, userId);
                } catch (Exception e) {
                    appProductVo.setUserProductStatus("1");
                    if (e instanceof ServiceException) {
                        appProductVo.setTipMsg(e.getMessage());
                    }
                }
            }
        } else {
            try {
                ProductUtils.checkUserCount(appProductVo, userId);
            } catch (Exception e) {
                appProductVo.setUserProductStatus("1");
                if (e instanceof ServiceException) {
                    appProductVo.setTipMsg(e.getMessage());
                }
            }
        }
    }

    /**
     * 获取产品购买次数缓存key
     *
     * @param platformKey 平台标识
     * @param productId   产品ID
     * @param dateType    时间类型
     * @return redis缓存key
     */
    public static String countByProductIdRedisKey(Long platformKey, Long productId, DateType dateType) {
        return "orderLimitCache:product:" + platformKey + ":" + productId + ":" + ZlyyhUtils.getDateCacheKey(dateType);
    }

    /**
     * 获取用户购买次数缓存key
     *
     * @param platformKey 平台标识
     * @param userId      用户ID
     * @param productId   产品ID
     * @param dateType    时间类型
     * @return redis缓存key
     */
    public static String countByUserIdAndProductIdRedisKey(Long platformKey, Long userId, Long productId, DateType dateType) {
        return "orderLimitCache:user:" + platformKey + ":" + productId + ":" + ZlyyhUtils.getDateCacheKey(dateType) + ":" + userId;
    }

    /**
     * 获取用户商品组购买次数缓存key
     *
     * @param platformKey    平台标识
     * @param userId         用户ID
     * @param productGroupId 产品组ID
     * @param dateType       时间类型
     * @return redis缓存key
     */
    public static String countByUserIdAndProductGroupIdRedisKey(Long platformKey, Long userId, Long productGroupId, DateType dateType) {
        return "orderProductGroupLimitCache:user:" + platformKey + ":" + productGroupId + ":" + ZlyyhUtils.getDateCacheKey(dateType) + ":" + userId;
    }

    public static String sendWeek(String type) {
        String sendDate = "";
        if (StringUtils.isBlank(type)) {
            return sendDate;
        }
        String[] split = type.split(",");
        for (String s : split) {
            if ("5".equals(s)) {
                sendDate = "四、";
            } else if ("4".equals(s)) {
                sendDate = "三、";
            } else if ("3".equals(s)) {
                sendDate = "二、";
            } else if ("2".equals(s)) {
                sendDate = "一、";
            } else if ("1".equals(s)) {
                sendDate = "日、";
            } else if ("6".equals(s)) {
                sendDate = "五、";
            } else if ("7".equals(s)) {
                sendDate = "六、";
            }
        }
        return sendDate.substring(0, sendDate.length() - 1);
    }
}
