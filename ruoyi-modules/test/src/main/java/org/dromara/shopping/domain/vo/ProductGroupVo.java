package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 商品组规则配置视图对象
 *
 * @author yzg
 * @date 2024-01-16
 */
@Data
@ExcelIgnoreUnannotated
public class ProductGroupVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long productGroupId;

    /**
     * 商品组名称
     */
    @ExcelProperty(value = "商品组名称")
    private String productGroupName;

    /**
     * 用户端提醒
     */
    @ExcelProperty(value = "用户端提醒")
    private String productGroupTip;

    /**
     * 每日同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "每日同一用户限领数量，0为不限制")
    private Long dayUserCount;

    /**
     * 每周同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "每周同一用户限领数量，0为不限制")
    private Long weekUserCount;

    /**
     * 当月同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "当月同一用户限领数量，0为不限制")
    private Long monthUserCount;

    /**
     * 活动周期同一用户限领数量，0为不限制
     */
    @ExcelProperty(value = "活动周期同一用户限领数量，0为不限制")
    private Long totalUserCount;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_banner_status")
    private String status;


}
