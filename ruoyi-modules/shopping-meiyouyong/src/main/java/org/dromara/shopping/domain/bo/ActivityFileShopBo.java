package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

/**
 * 活动商户业务对象
 *
 * @author yzg
 * @date 2024-01-03
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ActivityFileShopBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long activityShopId;

    /**
     * 商户名称
     */
    @NotBlank(message = "商户名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityShopName;

    /**
     * 商户地址
     */
    @NotBlank(message = "商户地址不能为空", groups = { AddGroup.class, EditGroup.class })
    private String address;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * 结构化地址信息省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码
     */
    //@NotBlank(message = "结构化地址信息省份＋城市＋区县＋城镇＋乡村＋街道＋门牌号码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String formattedAddress;

    /**
     * 省份名
     */
    //@NotBlank(message = "省份名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String province;

    /**
     * 城市名
     */
    //@NotBlank(message = "城市名不能为空", groups = { AddGroup.class, EditGroup.class })
    private String city;

    /**
     * 地址所在区
     */
    //@NotBlank(message = "地址所在区不能为空", groups = { AddGroup.class, EditGroup.class })
    private String district;

    /**
     * 省份编码
     */
    //@NotBlank(message = "省份编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String procode;

    /**
     * 城市编码
     */
    //@NotBlank(message = "城市编码不能为空", groups = { AddGroup.class, EditGroup.class })
    private String citycode;

    /**
     * 区域编码（行政区号
     */
    //@NotBlank(message = "区域编码（行政区号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String adcode;

    /**
     * 文件名称
     */
    //@NotBlank(message = "文件名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileName;

    /**
     * 文件唯一标识
     */
    //@NotBlank(message = "文件唯一标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private String fileId;

    /**
     * 返回h5链接
     */
    //@NotBlank(message = "返回h5链接不能为空", groups = { AddGroup.class, EditGroup.class })
    private String indexUrl;

    /**
     * 经度,基于高德地图
     */
    //@NotNull(message = "经度,基于高德地图不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal longitude;

    /**
     * 纬度,基于高德地图
     */
    //@NotNull(message = "纬度,基于高德地图不能为空", groups = { AddGroup.class, EditGroup.class })
    private BigDecimal latitude;

    /**
     * 排序：从小到大
     */
    //@NotNull(message = "排序：从小到大不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long sort;

    private Long typeId;

    private String cityName;
}
