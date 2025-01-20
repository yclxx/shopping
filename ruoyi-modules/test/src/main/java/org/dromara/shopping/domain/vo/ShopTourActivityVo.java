package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

import java.util.Date;



/**
 * 巡检活动视图对象
 *
 * @author yzg
 * @date 2024-03-01
 */
@Data
@ExcelIgnoreUnannotated
public class ShopTourActivityVo {

    private static final long serialVersionUID = 1L;

    /**
     * 巡检活动id
     */
    @ExcelProperty(value = "巡检活动id")
    private Long tourActivityId;

    /**
     * 巡检活动名称
     */
    @ExcelProperty(value = "巡检活动名称")
    private String tourActivityName;

    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private Date startDate;

    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private Date endDate;

    /**
     * 状态  0-正常  1-停用
     */
    @ExcelProperty(value = "状态  0-正常  1-停用", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "sys_normal_disable")
    private String status;

    /**
     * 可巡检人员：0-不限制，1-白名单限制
     */
    private String openType;

    private Date createTime;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
