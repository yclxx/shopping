package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;


/**
 * 活动核销人员关联视图对象
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@ExcelIgnoreUnannotated
public class VerifierActivityVo {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @ExcelProperty(value = "")
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 活动id
     */
    @ExcelProperty(value = "活动id")
    private Long activityId;

    /**
     * 核销人员id
     */
    @ExcelProperty(value = "核销人员id")
    private Long verifierId;

    /**
     * 活动名称
     */
    @ExcelProperty(value = "活动名称")
    private String activityName;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "channel_type")
    private String channel;


}
