package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;


/**
 * 巡检商户视图对象
 *
 * @author yzg
 * @date 2024-01-28
 */
@Data
@ExcelIgnoreUnannotated
public class ShopTourVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 门店id
     */
    @ExcelProperty(value = "门店id")
    private Long shopId;

    /**
     * 巡检人员id
     */
    @ExcelProperty(value = "巡检人员id")
    private Long verifierId;

    /**
     * 奖励金额  单位：分
     */
    @ExcelProperty(value = "奖励金额  单位：分")
    private Long rewardAmount;

    /**
     * 是否被预约  0-否  1-是
     */
    @ExcelProperty(value = "是否被预约  0-否  1-是", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_is_reserve")
    private String isReserve;

    /**
     * 门店状态  0-正常  1-异常
     */
    @ExcelProperty(value = "门店状态  0-正常  1-异常", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_shop_status")
    private String shopStatus;

    /**
     * 状态  0-待审核  1-审核通过  2-审核失败
     */
    @ExcelProperty(value = "状态  0-待审核  1-审核通过  2-审核失败", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_status")
    private String status;

    /**
     * 审核意见
     */
    @ExcelProperty(value = "审核意见")
    private String checkRemark;

    /**
     * 巡检人员和门店合影
     */
    @ExcelProperty(value = "巡检人员和门店合影")
    private String verifierImage;

    /**
     * 物料照片
     */
    @ExcelProperty(value = "物料照片")
    private String goodsImage;

    /**
     * 门店照片
     */
    @ExcelProperty(value = "门店照片")
    private String shopImage;

    /**
     * 巡检备注
     */
    @ExcelProperty(value = "巡检备注")
    private String tourRemark;

    /**
     * 商户号（需变更的）
     */
    @ExcelProperty(value = "商户号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "需=变更的")
    private String merchantNo;

    /**
     * 是否继续参与活动  0-不参与  1-参与
     */
    @ExcelProperty(value = "是否继续参与活动  0-不参与  1-参与", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_is_activity")
    private String isActivity;

    /**
     * 门店是否关闭  0-关闭  1-不关闭
     */
    @ExcelProperty(value = "门店是否关闭  0-关闭  1-不关闭", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_is_close")
    private String isClose;

    private String shopName;

    private String address;

    /**
     * 距离 千米
     */
    private BigDecimal distance;

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

    private String adminMobile;

    private String verifierMobile;

    private BigDecimal longitude;

    private BigDecimal latitude;

    /**
     * 巡检活动id
     */
    private Long tourActivityId;

    private String oldMerchantNo;

    private String merchantType;

    private String tourActivityName;

    private ShopTourLogVo shopTourLogVo;

    private String redisFlag;

    private Date checkPassTime;

    private Date checkFailTime;

    private Date submitTime;

    private ShopTourActivityVo shopTourActivityVo;

    private ShopTourLogVo recentShopTourLogVo;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
