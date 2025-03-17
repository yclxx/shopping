package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;


/**
 * 云闪付参数配置视图对象
 *
 * @author yzg
 * @date 2023-07-31
 */
@Data
@ExcelIgnoreUnannotated
public class YsfConfigVo {

    private static final long serialVersionUID = 1L;

    /**
     * 参数主键
     */
    @ExcelProperty(value = "参数主键")
    private Long configId;

    /**
     * 平台id
     */
    @ExcelProperty(value = "平台id")
    private Long platformId;

    /**
     * 参数名称
     */
    @ExcelProperty(value = "参数名称")
    private String configName;

    /**
     * 参数键名
     */
    @ExcelProperty(value = "参数键名")
    private String configKey;

    /**
     * 参数键值
     */
    @ExcelProperty(value = "参数键值")
    private String configValue;

    /**
     * 是否全局参数
     */
    @ExcelProperty(value = "是否全局")
    private String isAll;

    /**
     * 备注
     */
    @ExcelProperty(value = "备注")
    private String remark;


}
