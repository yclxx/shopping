package org.dromara.shopping.utils;

import java.math.BigDecimal;

/**
 * 地图工具类
 */
public class MapUtils {
    private final static double PI = 3.1415926535897932384626; // 圆周率
    private final static double R = 6371000.0; // 地球半径

    /**
     * 计算两个经纬度直接的距离
     *
     * @param lng1 第一个坐标的经度
     * @param lat1 第一个坐标的纬度
     * @param lng2 第二个坐标的经度
     * @param lat2 第二个坐标的纬度
     * @return 两个坐标之间的距离，米
     */
    public static double distance(double lng1, double lat1, double lng2, double lat2) {
        double distance = 0;
        try {
            distance = Math.round(R * 2
                    * Math.asin(Math.sqrt(Math.pow(Math.sin((lat1 * PI / 180 - lat2 * PI / 180) / 2), 2)
                    + Math.cos(lat1 * PI / 180) * Math.cos(lat2 * PI / 180)
                    * Math.pow(Math.sin((lng1 * PI / 180 - lng2 * PI / 180) / 2), 2))));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return distance;
    }

    /**
     * 计算两个经纬度直接的距离
     *
     * @param lng1 第一个坐标的经度
     * @param lat1 第一个坐标的纬度
     * @param lng2 第二个坐标的经度
     * @param lat2 第二个坐标的纬度
     * @return 两个坐标之间的距离，米
     */
    public static double distance(BigDecimal lng1, BigDecimal lat1, BigDecimal lng2, BigDecimal lat2) {
        return distance(lng1.doubleValue(), lat1.doubleValue(), lng2.doubleValue(), lat2.doubleValue());
    }
}
