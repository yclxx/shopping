package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import org.dromara.common.excel.annotation.ExcelDictFormat;
import org.dromara.common.excel.convert.ExcelDictConvert;
import lombok.Data;

/**
 * 巡检奖励视图对象
 *
 * @author yzg
 * @date 2024-01-28
 */
@Data
@ExcelIgnoreUnannotated
public class ShopTourRewardVo {

    private static final long serialVersionUID = 1L;

    /**
     * 巡检奖励id
     */
    @ExcelProperty(value = "巡检奖励id")
    private Long tourRewardId;

    /**
     * 巡检人员id
     */
    @ExcelProperty(value = "巡检人员id")
    private Long verifierId;

    /**
     * 巡检次数
     */
    @ExcelProperty(value = "巡检次数")
    private Long count;

    /**
     * 巡检奖励  单位：分
     */
    @ExcelProperty(value = "巡检奖励  单位：分")
    private Long amount;

    /**
     * 发放状态  0-未发放  1-发放中  2-已发放
     */
    @ExcelProperty(value = "发放状态  0-未发放  1-发放中  2-已发放", converter = ExcelDictConvert.class)
    @ExcelDictFormat(dictType = "t_reward_status")
    private String status;


}
