package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 商户活动报名门店视图对象
 *
 * @author yzg
 * @date 2024-04-10
 */
@Data
@ExcelIgnoreUnannotated
public class CommercialActivityApplyShopVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 报名记录ID
     */
    @ExcelProperty(value = "报名记录ID")
    private Long applyId;

    /**
     * 活动ID
     */
    @ExcelProperty(value = "活动ID")
    private Long activityId;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String activityName;

    /**
     * 门店ID
     */
    @ExcelProperty(value = "门店ID")
    private Long shopId;

    /**
     * 门店名称
     */
    @ExcelProperty(value = "门店名称")
    private String shopName;

    /**
     * 商户号
     */
    @ExcelProperty(value = "商户号")
    private String merchantNo;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;


}
