package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;

/**
 * 广发用户视图对象
 *
 * @author yzg
 * @date 2024-05-22
 */
@Data
@ExcelIgnoreUnannotated
public class GfUserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String mobile;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=正常,1=停用")
    private String status;

    /**
     * 发放会员（0未发放 1已发放）
     */
    @ExcelProperty(value = "发放会员", converter = ExcelDictConvert.class)
    @ExcelDictFormat(readConverterExp = "0=未发放,1=已发放")
    private String sendVip;

    /**
     * 发放会员时间
     */
    @ExcelProperty(value = "发放会员时间")
    private Date sendVipTime;


}
