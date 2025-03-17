package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 活动商户视图对象
 *
 * @author yzg
 * @date 2024-01-03
 */
@Data
@ExcelIgnoreUnannotated
public class ActivityFileShopVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long activityShopId;

    /**
     * 商户名称
     */
    @ExcelProperty(value = "商户名称")
    private String activityShopName;

    /**
     * 商户地址
     */
    @ExcelProperty(value = "商户地址")
    private String address;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态")
    private String status;

    /**
     * 结构化地址信息省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码
     */
    @ExcelProperty(value = "结构化地址信息省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码")
    private String formattedAddress;

    /**
     * 省份名
     */
    @ExcelProperty(value = "省份名")
    private String province;

    /**
     * 城市名
     */
    @ExcelProperty(value = "城市名")
    private String city;

    /**
     * 地址所在区
     */
    @ExcelProperty(value = "地址所在区")
    private String district;

    /**
     * 省份编码
     */
    @ExcelProperty(value = "省份编码")
    private String procode;

    /**
     * 城市编码
     */
    @ExcelProperty(value = "城市编码")
    private String citycode;

    /**
     * 区域编码（行政区号
     */
    @ExcelProperty(value = "区域编码", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "区域编码（行政区号")
    private String adcode;

    /**
     * 文件名称
     */
    @ExcelProperty(value = "文件名称")
    private String fileName;

    /**
     * 文件唯一标识
     */
    @ExcelProperty(value = "文件唯一标识")
    private String fileId;

    /**
     * 返回h5链接
     */
    @ExcelProperty(value = "返回h5链接")
    private String indexUrl;

    /**
     * 经度,基于高德地图
     */
    @ExcelProperty(value = "经度,基于高德地图")
    private BigDecimal longitude;

    /**
     * 纬度,基于高德地图
     */
    @ExcelProperty(value = "纬度,基于高德地图")
    private BigDecimal latitude;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序：从小到大")
    private Long sort;

    private Long typeId;

    /**
     * 距离 千米
     */
    private BigDecimal distance;

}
