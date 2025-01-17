package org.dromara.shopping.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MobileUtils {
    /**
     * 验证手机号是否正确
     *
     * @param mobile 手机号
     * @return 手机号正确返回 true ，错误返回false
     */
    public static boolean isMobile(String mobile) {
        if (null == mobile || mobile.length() != 11) {
            return false;
        }
        // 验证手机号
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15([0-9]))|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}$");
        Matcher m = p.matcher(mobile);
        return m.matches();
    }

    /**
     * 查询符合的手机号码
     *
     * @param str 字符串
     * @return 返回手机号
     */
    public static String checkCellphone(String str) {
        // 将给定的正则表达式编译到模式中,号段增加了，有13,14,15,16,17,18,19打头的
        Pattern pattern = Pattern.compile("((13[0-9])|(14[0-9])|(15([0-9]))|(16[0-9])|(17[0-9])|(18[0-9])|(19[0-9]))\\d{8}");
        // 创建匹配给定输入与此模式的匹配器。
        Matcher matcher = pattern.matcher(str);
        //查找字符串中是否有符合的子字符串
        if (matcher.find()) {
            //查找到符合的即输出
            return matcher.group();
        }
        return null;
    }

    /**
     * 查询符合的固定电话
     *
     * @param str 字符串
     * @return 返回固定电话
     */
    public static String checkTelephone(String str) {
        str=str.replaceAll("\\s*|\t|\r|\n", "");
        str=str.replaceAll("\\<|\\>|\\(|\\)|\\（|\\）|\\[|\\]|\\【|\\】|\\{|\\}", "");
        // 将给定的正则表达式编译到模式中
        Pattern pattern = Pattern.compile("\\d{3,4}[-]\\d{7,8}|\\d{3}\\d{3,4}\\d{4}|\\d{3}[-]\\d{3,4}[-]\\d{4}");
        // 创建匹配给定输入与此模式的匹配器。
        Matcher matcher = pattern.matcher(str);
        //查找字符串中是否有符合的子字符串
        if (matcher.find()) {
            //查找到符合的即输出
            return matcher.group();
        }
        return null;
    }
}
