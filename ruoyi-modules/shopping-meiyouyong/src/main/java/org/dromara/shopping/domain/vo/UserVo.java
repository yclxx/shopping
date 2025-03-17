package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.sensitive.annotation.Sensitive;
import org.dromara.common.sensitive.core.SensitiveStrategy;

import java.util.Date;



/**
 * 用户信息视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class UserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 用户昵称
     */
    @ExcelProperty(value = "用户昵称")
    private String userName;

    /**
     * 头像
     */
    @ExcelProperty(value = "头像")
    private String userImg;

    /**
     * 手机号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @ExcelProperty(value = "手机号")
    private String mobile;

    /**
     * openId，第三方平台联登唯一标识
     */
    @ExcelProperty(value = "openId")
    private String openId;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_user_stauts")
    private String status;

    /**
     * 信息授权（0需要 1不需要）
     */
    @ExcelProperty(value = "信息授权", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_user_reload_user")
    private String reloadUser;

    /**
     * 是否权益会员（0不是 1是）
     */
    @ExcelProperty(value = "是否权益会员", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_user_vip")
    private String vipUser;

    /**
     * 首次访问所在城市
     */
    @ExcelProperty(value = "首次访问所在城市")
    private String registerCityName;

    /**
     * 首次访问所在城市行政区号
     */
    @ExcelProperty(value = "首次访问所在城市行政区号")
    private String registerCityCode;

    /**
     * 小程序关注状态（0-未知 1-已关注，2-取消关注）
     */
    @ExcelProperty(value = "小程序关注状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_user_follow_status")
    private String followStatus;

    /**
     * 最后登录IP
     */
    @ExcelProperty(value = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @ExcelProperty(value = "最后登录时间")
    private Date loginDate;

    /**
     * 最后登录所在城市
     */
    @ExcelProperty(value = "最后登录所在城市")
    private String loginCityName;

    /**
     * 最后登录所在城市行政区号
     */
    @ExcelProperty(value = "最后登录所在城市行政区号")
    private String loginCityCode;

    /**
     * 平台标识
     */
    private Long platformKey;

    @ExcelProperty(value = "平台")
    private String platformName;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 权益会员失效时间
     */
    private Date vipExpiryDate;

    /**
     * 上次登录时间
     */
    private Date lastLoginDate;

    /**
     * 活动标记 0-正常用户 1-活动标记用户
     */
    private String activityFlag;
}
