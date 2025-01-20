package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UpUserCouponInfo implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 券状态	1：正常 2：已销户 3：冻结 4：已归并 5：已失效 6：已使用
     */
    private String acctSt;
    /**
     * 活动ID
     */
    private String activityId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 券数量
     */
    private String avlBalance;
    /**
     * 优惠券券码
     */
    private String couponCd;
    /**
     * 活动缩略图
     */
    private String couponThumbnailIm;
    /**
     * 券生效时间
     */
    private String validBeginTm;
    /**
     * 券失效时间
     */
    private String validEndTm;
}
