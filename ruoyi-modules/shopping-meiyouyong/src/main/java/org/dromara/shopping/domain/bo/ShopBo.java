package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 门店业务对象
 *
 * @author yzgnet
 * @date 2023-09-16
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ShopBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = {EditGroup.class})
    private Long shopId;

    /**
     * 商户ID
     */
    private Long commercialTenantId;

    /**
     * 门店名称
     */
    @NotBlank(message = "门店名称不能为空", groups = {AddGroup.class, EditGroup.class})
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
     * 平台标识
     */
    //@NotNull(message = "平台标识不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long platformKey;

    /**
     * 展示开始时间
     */
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    private Date showEndDate;

    /**
     * 指定周几: 0-不指定，1-指定周几
     */
    private String assignDate;

    /**
     * 周几展示：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    private String weekDate;

    /**
     * 几点开始展示,几点结束,格式：HH:mm:ss-HH:mm:ss
     */
    private String sellTime;
    /**
     * 节假日是否营业
     */
    private Boolean holiday;
    /**
     * 门店图片
     */
    private String shopImgs;

    /**
     * 门店logo
     */
    private String shopLogo;

    /**
     * 排序：从小到大
     */
    private Long sort;

    private List<Long> commercialTenantIds;

    /**
     * 标签，多个之间用英文逗号隔开
     */
    private String shopTags;

    /**
     * 门店类型：0-餐饮，1-便利店，2-茶饮，3-酒店，4-景点，5-演出，6-电影院
     */
    private String shopType;

    private Long productId;

    /**
     * 商户拓展服务商表
     */
    private Long extensionServiceProviderId;

    /**
     * 推荐类型：0-不推荐，1-商圈精选，2-品牌精选
     */
    private String pushType;
    /**
     * 是否共享
     */
    private String isShare;
    /**
     * 供应商（与supplierShopId无关系）
     */
    private String supplier;
    /**
     * 营业执照
     */
    private String license;
    /**
     * 性质
     */
    private String nature;
    /**
     * 发票类型
     */
    private String invoice;
    /**
     * 收款账户
     */
    private String account;
    /**
     * 开户行
     */
    private String accountBank;
    /**
     * 收款人
     */
    private String accountPayee;
    /**
     * 活动类型
     */
    private String activity;

    /**
     * 标签
     */
    private List<Long> tagsList;
    /**
     * 支持端
     */
    private String supportChannel;
    /**
     * 供应商门店id
     */
    private String supplierShopId;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;

    private String businessDistrictId;


    /**
     * 最大查询数量
     */
    private Integer pageSize;

    private Long categoryId;

    /**
     * 商品类型
     */
    private String productType;

    /**
     * 自动匹配商圈
     */
    private String autoBusiness;
    /**
     * 商户是否审核
     */
    private String examineVerifier;

    private Long verifierId;

    private String dateValue;

    private String beginDate;

    private String endDate;

    private String merchantNo;

    private List<Long> shopIds;

    private String commercialTenantName;

    private List<Long> noShopIds;

    private Long tourActivityId;
}
