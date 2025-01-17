package org.dromara.shopping.enumd;

import lombok.Getter;
import org.dromara.common.core.utils.StringUtils;

/**
 * 平台
 */
@Getter
public enum PlatformEnumd {
    /**
     * 云闪付小程序
     */
    MP_YSF("mp-union", "0"),
    /**
     * 微信小程序
     */
    MP_WX("mp-weixin", "1"),

    /**
     * 民生银行app
     */
    MP_APP("mp-app","2"),
    /**
     *微信H5
     */
    MP_WXH5("mp-weixinh5","3"),
    /**
     * 非微信H5
     */
    MP_BROWSER("mp-browserh5","4"),
    /**
     * 银联分期
     */
    MP_STAGES("mp-stagesh5","5");

    /**
     * 平台
     */
    private String platformType;
    /**
     * 平台渠道，对应字典表中的渠道
     */
    private String channel;

    PlatformEnumd(String platformType, String channel) {
        this.platformType = platformType;
        this.channel = channel;
    }

    public static String getPlatformSupportChannel(PlatformEnumd platformEnumd) {
        if (MP_WX == platformEnumd) {
            return "1";
        } else {
            return "0";
        }
    }

    public static PlatformEnumd getPlatformEnumd(String str) {
        if (StringUtils.isBlank(str)) {
            return MP_YSF;
        }
        PlatformEnumd[] values = PlatformEnumd.values();
        for (PlatformEnumd value : values) {
            if (str.equals(value.getPlatformType())) {
                return value;
            }
        }
        return MP_YSF;
    }
}
