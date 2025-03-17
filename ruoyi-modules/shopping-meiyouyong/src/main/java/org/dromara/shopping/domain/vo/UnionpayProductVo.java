package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 银联活动视图对象
 *
 * @author yzg
 * @date 2023-12-08
 */
@Data
@ExcelIgnoreUnannotated
public class UnionpayProductVo {

    private static final long serialVersionUID = 1L;

    /**
     * 活动编号
     */
    @ExcelProperty(value = "活动编号")
    private String activityNo;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String activityNm;

    /**
     * 活动类型
     */
    @ExcelProperty(value = "活动类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_unionpay_product_activity_tp")
    private String activityTp;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date beginTime;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endTime;

    /**
     * 限制类型
     */
    @ExcelProperty(value = "限制类型", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_unionpay_product_limit_tp")
    private String limitTp;

    /**
     * 活动标识
     */
    @ExcelProperty(value = "活动标识", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_unionpay_product_activity_mark")
    private String activityMark;

    /**
     * 活动总次数
     */
    @ExcelProperty(value = "活动总次数")
    private Long allCount;

    /**
     * 活动剩余次数
     */
    @ExcelProperty(value = "活动剩余次数")
    private Long allRemainCount;

    /**
     * 当天总次数
     */
    @ExcelProperty(value = "当天总次数")
    private Long dayCount;

    /**
     * 当天剩余次数
     */
    @ExcelProperty(value = "当天剩余次数")
    private Long dayRemainCount;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;


}
