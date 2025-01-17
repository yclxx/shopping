package org.dromara.shopping.utils;

import org.dromara.common.core.utils.StringUtils;
import org.dromara.shopping.base.domain.vo.ProductVo;

/**
 * 产品相关帮助类
 *
 * @author 25487
 */
public class ProductUtils {

    /**
     * 校验用户今日是否可购买
     *
     * @param productVo 商品信息
     * @param userId    用户ID
     */
    public static void checkUserCount(ProductVo productVo, Long userId) {
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
