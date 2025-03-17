package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;
import java.util.List;


/**
 * 搜索彩蛋配置视图对象
 *
 * @author yzg
 * @date 2023-07-24
 */
@Data
@ExcelIgnoreUnannotated
public class SearchGroupVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long searchId;

    /**
     * 搜索内容
     */
    @ExcelProperty(value = "搜索内容")
    private String searchContent;

    /**
     * 商品编号
     */
    @ExcelProperty(value = "商品编号")
    private Long productId;

    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转，6-页面指定位置
     */
    @ExcelProperty(value = "跳转类型")
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
    @ExcelProperty(value = "开始时间")
    private Date startTime;

    /**
     * 失效时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 展示城市：ALL-全部、否则填城市行政区号，多个之间用英文逗号隔开
     */
    private String showCity;

    /**
     * 指定周几：0-不指定，1-指定周几
     */
    private String assignDate;

    /**
     * 周几能领：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    private String weekDate;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端")
    private String supportChannel;

    private List<ProductVo> productVoList;


}
