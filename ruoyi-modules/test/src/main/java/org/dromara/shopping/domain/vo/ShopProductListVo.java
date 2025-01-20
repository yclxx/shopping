package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * 门店视图对象
 *
 * @author yzgnet
 * @date 2023-09-16
 */
@Data
public class ShopProductListVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long shopId;

    /**
     * 商户ID
     */
    private Long commercialTenantId;

    /**
     * 门店名称
     */
    private String shopName;

    /**
     * 门店电话
     */
    private String shopTel;

    /**
     * 营业时间
     */
    private String businessHours;

    /**
     * 门店地址
     */
    private String address;

    /**
     * 结构化地址信息省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码
     */
    private String formattedAddress;

    /**
     * 经度,基于高德地图
     */
    private BigDecimal longitude;

    /**
     * 纬度,基于高德地图
     */
    private BigDecimal latitude;

    /**
     * 门店图片
     */
    private String shopImgs;

    /**
     * 门店logo
     */
    private String shopLogo;

    /**
     * 标签，多个之间用英文逗号隔开
     */
    private String shopTags;

    /**
     * 排序：从小到大
     */
    private Long sort;

    /**
     * 距离 千米
     */
    private BigDecimal distance;
    /**
     * 距离 千米 字符串 解决微信端展示问题
     */
    private String distanceString;
    /**
     * 产品信息
     */
    private ProductVo productVo;

    public void calculateDistance() {
        if (null != this.distance && this.distance.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal distance = this.distance.setScale(2, RoundingMode.DOWN);
            this.distanceString = distance.stripTrailingZeros().toPlainString();
        }
    }
}
