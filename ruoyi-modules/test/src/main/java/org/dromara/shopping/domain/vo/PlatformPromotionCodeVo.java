package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 平台用户推广码视图对象
 *
 * @author yzg
 * @date 2024-05-27
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformPromotionCodeVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 推广码
     */
    @ExcelProperty(value = "推广码")
    private String promotionCode;

    /**
     * 手机号码
     */
    @ExcelProperty(value = "手机号码")
    private String mobile;

    /**
     * 姓名
     */
    @ExcelProperty(value = "姓名")
    private String name;

    /**
     * 所属机构
     */
    @ExcelProperty(value = "所属机构")
    private String institution;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
