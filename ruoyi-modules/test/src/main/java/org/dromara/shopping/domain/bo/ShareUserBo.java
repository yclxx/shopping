package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import com.ruoyi.common.core.web.domain.TreeEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 分销员业务对象
 *
 * @author yzg
 * @date 2023-11-09
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShareUserBo extends TreeEntity<ShareUserBo> {

    /**
     * 用户ID
     */
    @NotNull(message = "用户ID不能为空", groups = { EditGroup.class })
    private Long userId;

    /**
     * 商圈名称
     */
    private String businessDistrictName;

    /**
     * 品牌名称
     */
    private String commercialTenantName;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 云闪付手机号
     */
    private String upMobile;

    /**
     * 状态
     */
    private String status;

    /**
     * 审核状态
     */
    private String auditStatus;

    /**
     * 开始时间
     */
    private Date startTime;
    /**
     * 结束时间
     */
    private Date endTime;

    /**
     * 备注
     */
    private String remark;

    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 姓名
     */
    private String userName;

    /**
     * 性别
     */
    private String sex;

    /**
     * 年龄段
     */
    private String ageType;

    /**
     * 分销员等级 0 一级， 1 二级
     */
    private String shareUserGrade;

    /**
     * 扩展推广，0-不开启，1-开启
     */
    private String openExtend;

    /**
     * 页面标题
     */
    private String sharePageTitle;

    /**
     * 推广说明
     */
    private String description;

    /**
     * 商品ID
     */
    private Long productId;

    /**
     * 分销奖励：0-随平台，1-自定义比例
     */
    private String shareAmountType;

    /**
     * 分成比例，0只记录
     */
    private BigDecimal shareAmount;

    /**
     * 被动分销奖励
     */
    private BigDecimal passivityShareAmount;

    private String bankBranch;

    private String bankSubBranch;

    private String bankWebsite;
}
