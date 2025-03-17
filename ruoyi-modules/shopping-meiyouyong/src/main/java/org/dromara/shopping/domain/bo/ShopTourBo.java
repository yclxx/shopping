package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 巡检商户业务对象
 *
 * @author yzg
 * @date 2024-01-28
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopTourBo extends BaseEntity {

    /**
     * id
     */
    @NotNull(message = "id不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 门店id
     */
    //@NotNull(message = "门店id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long shopId;

    /**
     * 巡检人员id
     */
    //@NotNull(message = "巡检人员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    /**
     * 奖励金额  单位：分
     */
    //@NotNull(message = "奖励金额  单位：分不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long rewardAmount;

    /**
     * 是否被预约  0-否  1-是
     */
    //@NotBlank(message = "是否被预约  0-否  1-是不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isReserve;

    /**
     * 门店状态  0-正常  1-异常
     */
    //@NotBlank(message = "门店状态  0-正常  1-异常不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shopStatus;

    /**
     * 状态  0-待审核  1-审核通过  2-审核失败
     */
    //@NotBlank(message = "状态  0-待审核  1-审核通过  2-审核失败不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 审核意见
     */
    //@NotBlank(message = "审核意见不能为空", groups = { AddGroup.class, EditGroup.class })
    private String checkRemark;

    /**
     * 巡检人员和门店合影
     */
    //@NotBlank(message = "巡检人员和门店合影不能为空", groups = { AddGroup.class, EditGroup.class })
    private String verifierImage;

    /**
     * 物料照片
     */
    //@NotBlank(message = "物料照片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String goodsImage;

    /**
     * 门店照片
     */
    //@NotBlank(message = "门店照片不能为空", groups = { AddGroup.class, EditGroup.class })
    private String shopImage;

    /**
     * 巡检备注
     */
    //@NotBlank(message = "巡检备注不能为空", groups = { AddGroup.class, EditGroup.class })
    private String tourRemark;

    /**
     * 商户号（需变更的）
     */
    //@NotBlank(message = "商户号（需变更的）不能为空", groups = { AddGroup.class, EditGroup.class })
    private String merchantNo;

    /**
     * 是否继续参与活动  0-不参与  1-参与
     */
    //@NotBlank(message = "是否继续参与活动  0-不参与  1-参与不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isActivity;

    /**
     * 门店是否关闭  0-关闭  1-不关闭
     */
    //@NotBlank(message = "门店是否关闭  0-关闭  1-不关闭不能为空", groups = { AddGroup.class, EditGroup.class })
    private String isClose;

    private String cityCode;

    private String districtCode;

    private String businessDistrictId;

    /**
     * 经度,基于高德地图
     */
    private BigDecimal longitude;

    /**
     * 纬度,基于高德地图
     */
    private BigDecimal latitude;

    /**
     * 预约有效期
     */
    private Date reserveValidity;

    /**
     * 预约时间
     */
    private Date reserveDate;

    /**
     * 不参与活动处理方式
     */
    private String noActivityRemark;

    /**
     * 门店关闭处理方式
     */
    private String closeRemark;

    private String shopName;

    private String address;

    private String adminMobile;

    private Long[] shopIds;

    /**
     * 巡检活动id
     */
    private Long tourActivityId;

    private List<Long> shopsIds;

    private List<Long> activityIds;

    private String oldMerchantNo;

    private String merchantType;

    private String tourType;

    private String dateType;

    private String dateValue;

    private List<Long> tourIds;

    private String sendEmail;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
