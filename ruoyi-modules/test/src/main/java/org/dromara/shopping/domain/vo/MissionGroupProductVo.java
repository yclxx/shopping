package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;



/**
 * 任务组可兑换商品配置视图对象
 *
 * @author yzg
 * @date 2023-05-10
 */
@Data
@ExcelIgnoreUnannotated
public class MissionGroupProductVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 任务组ID
     */
    @ExcelProperty(value = "任务组ID")
    private Long missionGroupId;

    /**
     * 任务ID
     */
    private Long missionId;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

    /**
     * 排序：从小到大
     */
    @ExcelProperty(value = "排序")
    private Long sort;

    /**
     * 创建者
     */
    @ExcelProperty(value = "创建者")
    private String createBy;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 更新者
     */
    @ExcelProperty(value = "更新者")
    private String updateBy;

    /**
     * 更新时间
     */
    @ExcelProperty(value = "更新时间")
    private Date updateTime;


}
