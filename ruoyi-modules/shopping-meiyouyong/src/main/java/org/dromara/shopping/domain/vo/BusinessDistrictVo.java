package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.math.BigDecimal;

/**
 * 商圈视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class BusinessDistrictVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long businessDistrictId;

    /**
     * 商圈名称
     */
    @ExcelProperty(value = "商圈名称")
    private String businessDistrictName;

    /**
     * 商圈图片
     */
    @ExcelProperty(value = "商圈图片")
    private String businessDistrictImg;

    /**
     * 电话
     */
    @ExcelProperty(value = "电话")
    private String businessMobile;

    /**
     * 营业时间
     */
    @ExcelProperty(value = "营业时间")
    private String businessHours;

    /**
     * 地址
     */
    @ExcelProperty(value = "地址")
    private String address;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
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
     * 省份编码（行政区号例如：浙江330000）
     */
    @ExcelProperty(value = "省份编码", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "行=政区号例如：浙江330000")
    private String procode;

    /**
     * 城市编码（行政区号例如：杭州330100）
     */
    @ExcelProperty(value = "城市编码", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "行=政区号例如：杭州330100")
    private String citycode;

    /**
     * 区域编码（行政区号例如：拱墅区330105）
     */
    @ExcelProperty(value = "区域编码", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "行=政区号例如：拱墅区330105")
    private String adcode;

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
     * 商圈自定义区域,基于高德地图,经纬度之间用,隔开 每组经纬度之间用;隔开 例如：经度,纬度;经度,纬度;经度,纬度;经度,纬度
     */
    @ExcelProperty(value = "商圈自定义区域,基于高德地图,经纬度之间用,隔开 每组经纬度之间用;隔开 例如：经度,纬度;经度,纬度;经度,纬度;经度,纬度")
    private String businessDistrictScope;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    private Long sysDeptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long sysUserId;


}
