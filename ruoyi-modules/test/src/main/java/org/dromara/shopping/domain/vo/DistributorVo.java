package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;



/**
 * 分销商信息视图对象
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@ExcelIgnoreUnannotated
public class DistributorVo {

    private static final long serialVersionUID = 1L;

    /**
     * 分销商ID
     */
    @ExcelProperty(value = "分销商ID")
    private String distributorId;

    /**
     * 公钥
     */
    @ExcelProperty(value = "公钥")
    private String publicKey;

    /**
     * 私钥
     */
    @ExcelProperty(value = "私钥")
    private String privateKey;

    /**
     * 分销商名称
     */
    @ExcelProperty(value = "分销商名称")
    private String distributorName;

    /**
     * 通知地址
     */
    @ExcelProperty(value = "通知地址")
    private String backUrl;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
