package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;


/**
 * 订单下载记录视图对象
 *
 * @author yzg
 * @date 2023-04-01
 */
@Data
@ExcelIgnoreUnannotated
public class OrderDownloadLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long tOrderDownloadId;

    /**
     * 文件地址
     */
    @ExcelProperty(value = "文件地址")
    private String fileUrl;

    /**
     * 状态：0：未导出   1：导出中   2：导出成功   3：导出失败
     */
    @ExcelProperty(value = "状态", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_order_download_status")
    private String status;

    private String failReason;

    private Date createTime;

}
