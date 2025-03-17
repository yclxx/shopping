package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 下载记录视图对象
 *
 * @author yzg
 * @date 2024-08-15
 */
@Data
@ExcelIgnoreUnannotated
public class DownloadLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long downloadId;

    /**
     * 下载说明
     */
    @ExcelProperty(value = "下载说明")
    private String downloadRemark;

    /**
     * 下载来源
     */
    @ExcelProperty(value = "下载来源")
    private String downloadSource;

    /**
     * 文件地址
     */
    @ExcelProperty(value = "文件地址")
    private String fileUrl;

    /**
     * 状态
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "download_status")
    private String status;

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

    /**
     * 失败原因
     */
    @ExcelProperty(value = "失败原因")
    private String failReason;


}
