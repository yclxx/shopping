package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.common.encrypt.annotation.EncryptField;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 活动记录对象 t_mission_user_record
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_history_mission_user_record")
public class HistoryMissionUserRecord extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "mission_user_record_id")
    private Long missionUserRecordId;
    /**
     * 任务用户ID
     */
    private Long missionUserId;
    /**
     * 任务组ID
     */
    private Long missionGroupId;
    /**
     * 任务ID
     */
    private Long missionId;
    /**
     * 状态（0正常 1已奖励(已抽奖) 2失效 3作废）
     */
    private String status;
    /**
     * 有效时间
     */
    private Date expiryTime;
    /**
     * 奖品ID
     */
    private Long drawId;
    /**
     * 奖品类型（0银联票券 1云闪付红包 2云闪付积点）
     */
    private String drawType;
    /**
     * 奖品名称
     */
    private String drawName;
    /**
     * 奖品图片
     */
    private String drawImg;
    /**
     * 状态（0未发放 1发放中 2发放成功 3发放失败）
     */
    private String sendStatus;
    /**
     * 发放账号
     */
    @EncryptField()
    private String sendAccount;
    /**
     * 取码(充值)订单号
     */
    private String pushNumber;
    /**
     * 领取时间(抽奖时间)
     */
    private Date drawTime;
    /**
     * 取码编号（比如发券接口所需的券ID之类的）
     */
    private String drawNo;

    /**
     * 机构账户（介入方代码）
     */
    private String accessCode;
    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    private BigDecimal sendValue;
    /**
     * 奖品额度(用来计算是否超出任务限制额度)
     */
    private BigDecimal drawQuota;
    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转
     */
    private String toType;
    /**
     * 小程序ID
     */
    private String appId;
    /**
     * 页面地址
     */
    private String url;
    /**
     * 奖品发放时间
     */
    private Date sendOkTime;
    /**
     * 失败原因
     */
    private String failReason;
    /**
     * 下单所在城市
     */
    private String orderCityName;
    /**
     * 下单所在城市行政区号
     */
    private String orderCityCode;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
