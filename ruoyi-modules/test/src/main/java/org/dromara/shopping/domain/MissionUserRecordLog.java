package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 活动订单取码记录对象 t_mission_user_record_log
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_mission_user_record_log")
public class MissionUserRecordLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 活动记录ID
     */
    private Long missionUserRecordId;
    /**
     * 取码(充值)订单号
     */
    private String pushNumber;
    /**
     * 供应商订单号
     */
    private String externalOrderNumber;
    /**
     * 取码提交供应商产品编号（供应商提供）
     */
    private String externalProductId;

    /**
     * 机构账户（介入方代码）
     */
    private String accessCode;
    /**
     * 发放金额，（票券类奖品无需填写，红包填写发放的红包金额，积点填写发放的积点数量）
     */
    private BigDecimal sendValue;
    /**
     * 订单状态 0-处理中 1-成功 2-失败
     */
    private String status;
    /**
     * 交易失败原因
     */
    private String remark;

}
