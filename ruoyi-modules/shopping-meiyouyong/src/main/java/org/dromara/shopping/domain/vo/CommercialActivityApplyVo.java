package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 商户活动报名视图对象
 *
 * @author yzg
 * @date 2024-04-10
 */
@Data
@ExcelIgnoreUnannotated
public class CommercialActivityApplyVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
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
     * 商户ID
     */
    @ExcelProperty(value = "商户ID")
    private Long commercialTenantId;

    /**
     * 品牌名称
     */
    @ExcelProperty(value = "品牌名称")
    private String commercialTenantName;

    /**
     * 商户名称
     */
    @ExcelProperty(value = "商户名称")
    private String commercialTenantTitle;

    /**
     * 结算方式
     */
    @ExcelProperty(value = "结算方式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "means_of_payments")
    private String meansOfPayments;

    /**
     * 结算比例
     */
    @ExcelProperty(value = "结算比例")
    private Integer meansOfRatio;

    /**
     * 宣传形式
     */
    @ExcelProperty(value = "宣传形式", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "publicity_type")
    private String publicity;

    /**
     * 报名状态
     */
    @ExcelProperty(value = "报名状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "activity_apply_status")
    private String applyStatus;

    /**
     * 拒绝理由
     */
    @ExcelProperty(value = "拒绝理由")
    private String rejectCause;

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

    private List<CommercialActivityApplyShopVo> commercialActivityApplyShopVos;
}
