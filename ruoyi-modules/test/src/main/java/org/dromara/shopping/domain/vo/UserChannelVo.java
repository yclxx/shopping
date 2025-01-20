package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 用户渠道信息视图对象
 *
 * @author yzg
 * @date 2023-10-13
 */
@Data
@ExcelIgnoreUnannotated
public class UserChannelVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

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
     * openId
     */
    @ExcelProperty(value = "openId")
    private String openId;

    /**
     * 信息授权
     */
    @ExcelProperty(value = "信息授权", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_user_reload_user")
    private String reloadUser;

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
     * 关注状态
     */
    @ExcelProperty(value = "关注状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_user_follow_status")
    private String followStatus;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;

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
     * 上次登录时间
     */
    @ExcelProperty(value = "上次登录时间")
    private Date lastLoginDate;

    /**
     * 渠道
     */
    @ExcelProperty(value = "渠道", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "channel_type")
    private String channel;
    /**
     * 平台标识
     */
    private Long platformKey;

}
