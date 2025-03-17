package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;



/**
 * 商户端菜单管理视图对象
 *
 * @author yzg
 * @date 2024-11-26
 */
@Data
@ExcelIgnoreUnannotated
public class VerifierMenuVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long menuId;

    /**
     * 名称
     */
    @ExcelProperty(value = "名称")
    private String menuName;

    /**
     * 角标
     */
    @ExcelProperty(value = "角标")
    private String menuMark;

    /**
     * 图片
     */
    @ExcelProperty(value = "图片")
    private String menuImage;

    /**
     * 排序
     */
    @ExcelProperty(value = "排序")
    private Long menuRank;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 功能状态
     */
    @ExcelProperty(value = "功能状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_open_status")
    private String openStatus;

    /**
     * 显示页面
     */
    @ExcelProperty(value = "显示页面")
    private String pagePath;

    /**
     * 跳转类型
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
    @ExcelProperty(value = "生效时间")
    private Date startTime;

    /**
     * 失效时间
     */
    @ExcelProperty(value = "失效时间")
    private Date endTime;

    /**
     * 上级ID
     */
    @ExcelProperty(value = "上级ID")
    private Long parentId;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
