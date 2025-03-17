package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 广告管理视图对象
 *
 * @author ruoyi
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class BannerVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long bannerId;

    /**
     * 显示名称
     */
    @ExcelProperty(value = "显示名称")
    private String bannerName;

    /**
     * 角标
     */
    @ExcelProperty(value = "角标")
    private String bannerMark;

    /**
     * banner图
     */
    @ExcelProperty(value = "banner图")
    private String bannerImage;

    /**
     * 排序，从小到大
     */
    @ExcelProperty(value = "排序")
    private Long bannerRank;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_banner_status")
    private String status;

    /**
     * banner类型：0-顶部广告,1-icon,2-长图轮播，3-腰部广告(两张)，4-腰部广告(三张)，5-浮框，6-弹窗，7-银行专属优惠
     */
    @ExcelProperty(value = "类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_banner_type")
    private String bannerType;

    /**
     * 显示页面，对应t_page中page_path字段
     */
    @ExcelProperty(value = "显示页面")
    private String pagePath;

    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转，6-页面指定位置
     */
    @ExcelProperty(value = "跳转类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_banner_to_type")
    private String toType;

    /**
     * 小程序ID
     */
    @ExcelProperty(value = "小程序ID")
    private String appId;

    /**
     * 页面地址
     */
    @ExcelProperty(value = "页面地址")
    private String url;

    /**
     * 生效时间
     */
    @ExcelProperty(value = "生效时间")
    private Date startTime;

    /**
     * 失效时间
     */
    @ExcelProperty(value = "失效时间")
    private Date endTime;

    /**
     * 展示维度：0-全部、1-新用户、2-老用户
     */
    @ExcelProperty(value = "展示维度", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_banner_show_dimension")
    private String showDimension;

    /**
     * 展示城市：ALL-全部、否则填城市行政区号，多个之间用英文逗号隔开
     */
    @ExcelProperty(value = "展示城市")
    private String showCity;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 指定周几: 0-不指定，1-指定周几
     */
    @ExcelProperty(value = "指定周几")
    private String assignDate;
    /**
     * 周几能领：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    @ExcelProperty(value = "周几显示")
    private String weekDate;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端")
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
