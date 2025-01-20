package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 支行长视图对象
 *
 * @author yzg
 * @date 2024-04-28
 */
@Data
@ExcelIgnoreUnannotated
public class PresidentVo {

    private static final long serialVersionUID = 1L;

    /**
     * 支行长id
     */
    @ExcelProperty(value = "支行长id")
    private Long presidentId;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String name;

    /**
     * 手机号
     */
    @ExcelProperty(value = "手机号")
    private String mobile;

    /**
     * openId
     */
    @ExcelProperty(value = "openId")
    private String openId;

    /**
     * 银行
     */
    @ExcelProperty(value = "银行")
    private String bank;

    /**
     * 一支
     */
    @ExcelProperty(value = "一支")
    private String linkmanBranch;

    /**
     * 二支
     */
    @ExcelProperty(value = "二支")
    private String linkmanBranchSecond;

    /**
     * 状态  0-正常  1-停用
     */
    @ExcelProperty(value = "状态  0-正常  1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    private Long platformKey;

    private String identification;


}
