package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 巡检记录视图对象
 *
 * @author yzg
 * @date 2024-03-06
 */
@Data
@ExcelIgnoreUnannotated
public class ShopTourLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 巡检记录ID
     */
    @ExcelProperty(value = "巡检记录ID")
    private Long tourLogId;

    /**
     * 巡检ID
     */
    @ExcelProperty(value = "巡检ID")
    private Long tourId;

    /**
     * 操作人员ID
     */
    @ExcelProperty(value = "操作人员ID")
    private Long verifierId;

    /**
     * 操作类型  1-预约  2-提交巡检  3-取消预约  4-取消巡检  5-审核通过 6-审核拒绝  7-预约过期
     */
    @ExcelProperty(value = "操作类型  1-预约  2-提交巡检  3-取消预约  4-取消巡检  5-审核通过 6-审核拒绝  7-预约过期", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_oper_type")
    private String operType;

    /**
     * 门店ID
     */
    @ExcelProperty(value = "门店ID")
    private Long shopId;

    /**
     * 门店名称
     */
    @ExcelProperty(value = "门店名称")
    private String shopName;

    /**
     * 门店地址
     */
    @ExcelProperty(value = "门店地址")
    private String address;

    /**
     * 商户管理员手机号
     */
    @ExcelProperty(value = "商户管理员手机号")
    private String adminMobile;

    /**
     * 门店状态  0-正常  1-异常
     */
    @ExcelProperty(value = "门店状态  0-正常  1-异常", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_shop_status")
    private String shopStatus;

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
     * 原始商户号
     */
    @ExcelProperty(value = "原始商户号")
    private String oldMerchantNo;

    /**
     * 商户类型  0-微信  1-云闪付  2-支付宝
     */
    @ExcelProperty(value = "商户类型  0-微信  1-云闪付  2-支付宝", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_shop_merchant_type")
    private String merchantType;

    /**
     * 商户号（变更）
     */
    @ExcelProperty(value = "商户号", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "变=更")
    private String merchantNo;

    /**
     * 是否继续参与活动  0-不参与  1-参与
     */
    @ExcelProperty(value = "是否继续参与活动  0-不参与  1-参与", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_is_activity")
    private String isActivity;

    /**
     * 门店是否关闭  0-关闭  1-运营中
     */
    @ExcelProperty(value = "门店是否关闭  0-关闭  1-运营中", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_tour_is_close")
    private String isClose;

    /**
     * 审核失败原因
     */
    @ExcelProperty(value = "审核失败原因")
    private String checkFailReason;

    private ShopVo shopVo;

    private String tourActivityName;

    private Date createTime;

}
