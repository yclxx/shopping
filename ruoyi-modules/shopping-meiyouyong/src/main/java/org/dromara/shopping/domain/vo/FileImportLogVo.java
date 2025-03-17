package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 文件导入记录视图对象
 *
 * @author yzg
 * @date 2024-01-04
 */
@Data
@ExcelIgnoreUnannotated
public class FileImportLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * 文件导入记录id
     */
    @ExcelProperty(value = "文件导入记录id")
    private Long importLogId;

    /**
     * 文件地址
     */
    @ExcelProperty(value = "文件地址")
    private String url;

    /**
     * 文件名
     */
    @ExcelProperty(value = "文件名")
    private String name;

    /**
     * 数据数量
     */
    @ExcelProperty(value = "数据数量")
    private Long count;

    /**
     * 导入数据数量
     */
    private Long importCount;

    /**
     * 页面标题
     */
    private String pageTitle;

    private String pageUrl;

}
