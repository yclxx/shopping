package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.math.BigDecimal;

/**
 * 商圈对象 t_business_district
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_business_district")
public class BusinessDistrict extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "business_district_id")
    private Long businessDistrictId;
    /**
     * 商圈名称
     */
    private String businessDistrictName;
    /**
     * 商圈图片
     */
    private String businessDistrictImg;
    /**
     * 电话
     */
    private String businessMobile;
    /**
     * 营业时间
     */
    private String businessHours;
    /**
     * 地址
     */
    private String address;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 结构化地址信息省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码
     */
    private String formattedAddress;
    /**
     * 省份名
     */
    private String province;
    /**
     * 城市名
     */
    private String city;
    /**
     * 地址所在区
     */
    private String district;
    /**
     * 省份编码（行政区号例如：浙江330000）
     */
    private String procode;
    /**
     * 城市编码（行政区号例如：杭州330100）
     */
    private String citycode;
    /**
     * 区域编码（行政区号例如：拱墅区330105）
     */
    private String adcode;
    /**
     * 经度,基于高德地图
     */
    private BigDecimal longitude;
    /**
     * 纬度,基于高德地图
     */
    private BigDecimal latitude;
    /**
     * 商圈自定义区域,基于高德地图,经纬度之间用,隔开 每组经纬度之间用;隔开 例如：经度,纬度;经度,纬度;经度,纬度;经度,纬度
     */
    private String businessDistrictScope;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;

}
