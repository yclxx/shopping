package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;



/**
 * 页面配置视图对象
 *
 * @author yzgnet
 * @date 2023-03-21
 */
@Data
@ExcelIgnoreUnannotated
public class PageSettingVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 页面标识
     */
    @ExcelProperty(value = "页面标识")
    private String pagePath;

    /**
     * 支持功能：0-顶部广告,1-icon,2-长图轮播，3-腰部广告(两张)，4-腰部广告(三张)，5-浮框，6-弹窗，7-银行专属优惠，99-自定义版块
     */
    @ExcelProperty(value = "支持功能", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_page_setting_banner_type")
    private String bannerType;

    /**
     * 版块模板编号，自定义版块时必填
     */
    @ExcelProperty(value = "版块模板编号")
    private Long blockId;

    /**
     * 版块字段值，JSON字符串，格式Map：key=版块模板字段中JAVA字段名，value=对应值
     */
    @ExcelProperty(value = "版块字段值")
    private String blockColumnValue;

    /**
     * 板块模版主键字段
     */
    @ExcelProperty(value = "板块模版主键字段")
    private String mainField;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_page_setting_status")
    private String status;

    /**
     * 排序，从小到大
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    private String platformName;

    private String blockName;


}
