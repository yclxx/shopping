package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * 分销员导入视图对象
 *
 * @author yzg
 * @date 2023-11-09
 */
@Data
@ExcelIgnoreUnannotated
public class ShareUserImportVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 姓名
     */
    @ExcelProperty(value = "用户姓名")
    private String userName;

    /**
     * 用户手机号
     */
    @ExcelProperty(value = "用户手机号")
    private String userMobile;

    /**
     * 分销等级
     */
    @ExcelProperty(value = "分销等级")
    private String userGrade;

    /**
     * 上级ID
     */
    @ExcelProperty(value = "上级手机号")
    private String parentMobile;

    @ExcelProperty(value = "分行")
    private String bankBranch;

    @ExcelProperty(value = "支行")
    private String bankSubBranch;

    @ExcelProperty(value = "网点")
    private String bankWebsite;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;
}
