package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Date;

/**
 * 巡检商户视图对象
 *
 * @author yzg
 * @date 2024-01-28
 */
@Data
@ExcelIgnoreUnannotated
public class ShopTourLocalhostExportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商户名称
     */
    @ExcelProperty(value = "商户名称")
    private String commercialTenantName;

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
     * 商圈名称
     */
    @ExcelProperty(value = "商圈名称")
    private String businessDistrictName;

    /**
     * BD
     */
    @ExcelProperty(value = "巡检人员")
    private String userName;

    /**
     * 巡检人电话
     */
    @ExcelProperty(value = "巡检人电话")
    private String mobile;

    /**
     * 巡检活动
     */
    @ExcelProperty(value = "巡检活动")
    private String tourActivityName;

    /**
     * 奖励金额
     */
    @ExcelProperty(value = "奖励金额(元)")
    private BigDecimal rewardAmount;

    /**
     * 巡检时间
     */
    @ExcelProperty(value = "巡检时间")
    private Date tourTime;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 审核意见
     */
    @ExcelProperty(value = "审核意见")
    private String checkRemark;

    /**
     * 巡检备注
     */
    @ExcelProperty(value = "巡检备注")
    private String tourRemark;

    /**
     * 巡检人员和门店合影
     */
    private String verifierImage;
    @ExcelProperty(value = "巡检人员和门店合影")
    private URL verifierImageUrl;

    /**
     * 物料照片
     */
    private String goodsImage;
    @ExcelProperty(value = "物料照片")
    private URL goodsImageUrl;

    /**
     * 门店照片
     */
    private String shopImage;

    @ExcelProperty(value = "门店照片")
    private URL shopImageUrl;
}
