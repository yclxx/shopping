package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

/**
 * 广告管理业务对象
 *
 * @author ruoyi
 * @date 2023-03-21
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class BannerBo extends BaseEntity {

    /**
     * ID
     */
    @NotNull(message = "ID不能为空", groups = { EditGroup.class })
    private Long bannerId;

    /**
     * 显示名称
     */
    private String bannerName;

    /**
     * 角标
     */
    private String bannerMark;

    /**
     * banner图
     */
    //@NotBlank(message = "banner图不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bannerImage;

    /**
     * 排序，从小到大
     */
    private Long bannerRank;

    /**
     * 状态（0正常 1停用）
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
    private String status;

    /**
     * banner类型：0-顶部广告,1-icon,2-长图轮播，3-腰部广告(两张)，4-腰部广告(三张)，5-浮框，6-弹窗，7-银行专属优惠
     */
    @NotBlank(message = "banner类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String bannerType;

    /**
     * 显示页面，对应t_page中page_path字段
     */
    @NotBlank(message = "显示页面不能为空", groups = { AddGroup.class, EditGroup.class })
    private String pagePath;

    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转，6-页面指定位置
     */
    private String toType;

    /**
     * 小程序ID
     */
    private String appId;

    /**
     * 页面地址
     */
    private String url;

    /**
     * 生效时间
     */
    private Date startTime;

    /**
     * 失效时间
     */
    private Date endTime;

    /**
     * 展示维度：0-全部、1-新用户、2-老用户
     */
    private String showDimension;

    /**
     * 展示城市：ALL-全部、否则填城市行政区号，多个之间用英文逗号隔开
     */
    private String showCity;

    /**
     * 平台标识
     */
    @NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 指定周几: 0-不指定，1-指定周几
     */
    private String assignDate;
    /**
     * 周几能领：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    private String weekDate;
    /**
     * 支持端
     */
    private String supportChannel;
    /**
     * 点击是否发放奖品
     */
    private String sendProduct;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 商铺id
     */
    private Long sellerId;
}
