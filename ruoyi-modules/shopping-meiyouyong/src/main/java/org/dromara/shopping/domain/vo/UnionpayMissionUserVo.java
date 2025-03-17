package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 银联任务用户视图对象
 *
 * @author yzg
 * @date 2024-02-21
 */
@Data
@ExcelIgnoreUnannotated
public class UnionpayMissionUserVo {

    private static final long serialVersionUID = 1L;

    /**
     * 银联任务用户ID
     */
    @ExcelProperty(value = "银联任务用户ID")
    private Long upMissionUserId;

    /**
     * 银联任务组ID
     */
    @ExcelProperty(value = "银联任务组ID")
    private Long upMissionGroupId;

    /**
     * 用户Id
     */
    @ExcelProperty(value = "用户Id")
    private Long userId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 状态  0-正常  1-停用
     */
    @ExcelProperty(value = "状态  0-正常  1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    private UserVo userVo;

}
