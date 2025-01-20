package org.dromara.shopping.domain.vo;

import lombok.Data;

/**
 * 62会员实体
 * @author 25487
 */
@Data
public class MemberVipBalanceVo {
    /**
     * 首开标识
     * 00-从未开通过会员
     * 01-开通过会员且已过期
     * 02-开通过会员且未到期
     * 03-开通过会员且已退款
     */
    private String newMember;
    /**
     * 年卡是否达到续费上限
     * 0-未达到 1-已达到
     */
    private String yearValid;
    /**
     * 季卡是否达到续费上限
     * 0-未达到 1-已达到
     */
    private String seasonValid;
    /**
     * 首次开通会员时间
     * yyyyMMddHHmmss
     */
    private String beginTime;
    /**
     * 会员到期时间
     * 说明：末笔会员订单到期时间/用户退款时间
     * yyyyMMddHHmmss
     */
    private String endTime;
    /**
     * 会员类型
     * 说明：与用户有效订单（包括使用中和未生效订单）的最长有效期订单一致。如用户当前季卡（生效中），续费了年卡（未生效），则用户会员类型为年卡会员（03）。
     * 00-试用
     * 01-月卡
     * 02-季卡
     * 03-年卡
     * 04-普通用户
     */
    private String memberType;
    /**
     * 月卡是否达到续费上限
     * 0-未达到 1-已达到
     */
    private String monthValid;
    /**
     * 用户当前会员状态
     * 00-普通用户
     * 01-会员用户
     * 02-会员冻结用户
     * 03-试用用户
     */
    private String status;
}
