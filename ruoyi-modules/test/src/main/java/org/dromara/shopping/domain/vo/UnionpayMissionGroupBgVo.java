package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 任务组背景视图对象
 *
 * @author yzg
 * @date 2024-03-02
 */
@Data
@ExcelIgnoreUnannotated
public class UnionpayMissionGroupBgVo {

    private static final long serialVersionUID = 1L;

    /**
     * 任务组背景id
     */
    @ExcelProperty(value = "任务组背景id")
    private Long missionBgId;

    /**
     * 任务组ID
     */
    @ExcelProperty(value = "任务组ID")
    private Long missionGroupId;

    /**
     * 背景图片
     */
    @ExcelProperty(value = "背景图片")
    private String bgImg;

    /**
     * 背景类型  1-首页(报名前)  2-首页(报名后)  3-记录页
     */
    @ExcelProperty(value = "背景类型  1-首页(报名前)  2-首页(报名后)  3-记录页", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_unmission_group_bg_type")
    private String imgType;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 状态  0-正常  1-停用
     */
    @ExcelProperty(value = "状态  0-正常  1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 排序 从小到大 默认99
     */
    @ExcelProperty(value = "排序 从小到大 默认99")
    private Long sort;

    private String bgName;

    private String isToLink;

    private String toUrl;

}
