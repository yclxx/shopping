package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import org.dromara.common.sensitive.annotation.Sensitive;
import org.dromara.common.sensitive.core.SensitiveStrategy;

import java.math.BigDecimal;
import java.util.Date;



/**
 * 活动记录视图对象
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@ExcelIgnoreUnannotated
public class MissionUserRecordVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
//    @ExcelProperty(value = "ID")
    private Long missionUserRecordId;

    /**
     * 任务用户ID
     */
    @ExcelProperty(value = "任务用户ID")
    private Long missionUserId;

    /**
     * 任务组ID
     */
//    @ExcelProperty(value = "任务组ID")
    private Long missionGroupId;

    /**
     * 任务ID
     */
//    @ExcelProperty(value = "任务ID")
    private Long missionId;

    /**
     * 状态（0正常 1已奖励(已抽奖) 2失效 3作废）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "mission_user_record_status")
    private String status;

    /**
     * 有效时间
     */
    @ExcelProperty(value = "有效时间")
    private Date expiryTime;

    /**
     * 奖品ID
     */
    @ExcelProperty(value = "奖品ID")
    private Long drawId;

    /**
     * 奖品类型（0银联票券 1云闪付红包 2云闪付积点）
     */
    @ExcelProperty(value = "奖品类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "draw_type")
    private String drawType;

    /**
     * 奖品名称
     */
    @ExcelProperty(value = "奖品名称")
    private String drawName;

    /**
     * 奖品图片
     */
//    @ExcelProperty(value = "奖品图片")
    private String drawImg;

    /**
     * 状态（0未发放 1发放中 2发放成功 3发放失败）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_send_status")
    private String sendStatus;

    /**
     * 发放账号
     */
    @Sensitive(strategy = SensitiveStrategy.PHONE)
    @ExcelProperty(value = "发放账号")
    private String sendAccount;

    /**
     * 机构账户（介入方代码）
     */
    private String accessCode;

    /**
     * 取码(充值)订单号
     */
//    @ExcelProperty(value = "取码(充值)订单号")
    private String pushNumber;

    /**
     * 领取时间(抽奖时间)
     */
    @ExcelProperty(value = "领取时间(抽奖时间)")
    private Date drawTime;

    /**
     * 取码编号（比如发券接口所需的券ID之类的）
     */
//    @ExcelProperty(value = "取码编号")
    private String drawNo;

    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    @ExcelProperty(value = "发放金额，")
    private BigDecimal sendValue;

    /**
     * 奖品额度(用来计算是否超出任务限制额度)
     */
//    @ExcelProperty(value = "奖品额度")
    private BigDecimal drawQuota;

    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转
     */
//    @ExcelProperty(value = "跳转类型")
    private String toType;

    /**
     * 小程序ID
     */
//    @ExcelProperty(value = "小程序ID")
    private String appId;

    /**
     * 页面地址
     */
//    @ExcelProperty(value = "页面地址")
    private String url;

    /**
     * 奖品发放时间
     */
    @ExcelProperty(value = "奖品发放时间")
    private Date sendOkTime;

    /**
     * 失败原因
     */
//    @ExcelProperty(value = "失败原因")
    private String failReason;

    /**
     * 下单所在城市
     */
//    @ExcelProperty(value = "下单所在城市")
    private String orderCityName;

    /**
     * 下单所在城市行政区号
     */
//    @ExcelProperty(value = "下单所在城市行政区号")
    private String orderCityCode;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
//    @ExcelProperty(value = "更新时间")
    private Date updateTime;


}
