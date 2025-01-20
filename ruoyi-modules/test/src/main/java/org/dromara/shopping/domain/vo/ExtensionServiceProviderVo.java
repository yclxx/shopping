package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 商户拓展服务商视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class ExtensionServiceProviderVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 服务商名称
     */
    @ExcelProperty(value = "服务商名称")
    private String providerName;

    /**
     * 服务商联系人
     */
    @ExcelProperty(value = "服务商联系人")
    private String providerUserName;

    /**
     * 联系电话
     */
    @ExcelProperty(value = "联系电话")
    private String providerUserMobile;

    /**
     * 状态（0正常 1停用）
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 部门id
     */
    @ExcelProperty(value = "部门id")
    private Long sysDeptId;

    /**
     * 用户id
     */
    @ExcelProperty(value = "用户id")
    private Long sysUserId;


}
