package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 巡检商户对象 t_shop_tour
 *
 * @author yzg
 * @date 2024-01-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_shop_tour")
public class ShopTour extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 门店id
     */
    private Long shopId;
    /**
     * 巡检人员id
     */
    private Long verifierId;
    /**
     * 奖励金额  单位：分
     */
    private Long rewardAmount;
    /**
     * 是否被预约  0-否  1-是
     */
    private String isReserve;
    /**
     * 门店状态  0-正常  1-异常
     */
    private String shopStatus;
    /**
     * 状态  0-待审核  1-审核通过  2-审核失败
     */
    private String status;
    /**
     * 审核意见
     */
    private String checkRemark;
    /**
     * 巡检人员和门店合影
     */
    private String verifierImage;
    /**
     * 物料照片
     */
    private String goodsImage;
    /**
     * 门店照片
     */
    private String shopImage;
    /**
     * 巡检备注
     */
    private String tourRemark;
    /**
     * 商户号（需变更的）
     */
    private String merchantNo;
    /**
     * 是否继续参与活动  0-不参与  1-参与
     */
    private String isActivity;
    /**
     * 门店是否关闭  0-关闭  1-不关闭
     */
    private String isClose;
    /**
     * 删除标志  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

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

    /**
     * 巡检活动id
     */
    private Long tourActivityId;

    private String oldMerchantNo;

    private String merchantType;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
