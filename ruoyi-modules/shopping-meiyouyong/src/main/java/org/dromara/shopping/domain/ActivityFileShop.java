package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.dromara.common.mybatis.core.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 活动商户对象 t_activity_file_shop
 *
 * @author yzg
 * @date 2024-01-03
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_activity_file_shop")
public class ActivityFileShop extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "activity_shop_id")
    private Long activityShopId;
    /**
     * 商户名称
     */
    private String activityShopName;
    /**
     * 商户地址
     */
    private String address;
    /**
     * 状态
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
     * 省份编码
     */
    private String procode;
    /**
     * 城市编码
     */
    private String citycode;
    /**
     * 区域编码（行政区号
     */
    private String adcode;
    /**
     * 文件名称
     */
    private String fileName;
    /**
     * 文件唯一标识
     */
    private String fileId;
    /**
     * 返回h5链接
     */
    private String indexUrl;
    /**
     * 经度,基于高德地图
     */
    private BigDecimal longitude;
    /**
     * 纬度,基于高德地图
     */
    private BigDecimal latitude;
    /**
     * 排序：从小到大
     */
    private Long sort;

    private Long typeId;

}
