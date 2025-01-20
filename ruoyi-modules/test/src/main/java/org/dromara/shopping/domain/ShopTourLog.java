package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 巡检记录对象 t_shop_tour_log
 *
 * @author yzg
 * @date 2024-03-06
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_shop_tour_log")
public class ShopTourLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 巡检记录ID
     */
    @TableId(value = "tour_log_id")
    private Long tourLogId;
    /**
     * 巡检ID
     */
    private Long tourId;
    /**
     * 操作人员ID
     */
    private Long verifierId;
    /**
     * 操作类型  1-预约  2-提交巡检  3-取消预约  4-取消巡检  5-审核通过 6-审核拒绝  7-预约过期
     */
    private String operType;
    /**
     * 门店ID
     */
    private Long shopId;
    /**
     * 门店名称
     */
    private String shopName;
    /**
     * 门店地址
     */
    private String address;
    /**
     * 商户管理员手机号
     */
    private String adminMobile;
    /**
     * 门店状态  0-正常  1-异常
     */
    private String shopStatus;
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
     * 原始商户号
     */
    private String oldMerchantNo;
    /**
     * 商户类型  0-微信  1-云闪付  2-支付宝
     */
    private String merchantType;
    /**
     * 商户号（变更）
     */
    private String merchantNo;
    /**
     * 是否继续参与活动  0-不参与  1-参与
     */
    private String isActivity;
    /**
     * 门店是否关闭  0-关闭  1-运营中
     */
    private String isClose;
    /**
     * 审核失败原因
     */
    private String checkFailReason;
    /**
     * 删除标志 0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

}
