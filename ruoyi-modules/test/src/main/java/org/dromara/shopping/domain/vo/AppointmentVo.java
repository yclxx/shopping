package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * 预约信息视图对象
 *
 * @author yzg
 * @date 2024-06-28
 */
@Data
@ExcelIgnoreUnannotated
public class AppointmentVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    private Long id;

    /**
     * 商品ID
     */
    @ExcelProperty(value = "商品ID")
    private Long productId;

    /**
     * 套餐ID
     */
    private Long productSkuId;

    /**
     * 可预约时间
     */
    @ExcelProperty(value = "可预约时间")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date appointmentDate;

    /**
     * 可预约数量
     */
    @ExcelProperty(value = "可预约数量")
    private Long appointmentCount;

    private Long version;
    /**
     * 开始时间
     */
    @ExcelProperty(value = "开始时间")
    private String startTime;
    /**
     * 结束时间
     */
    @ExcelProperty(value = "结束时间")
    private String endTime;

    private String productName;
    /**
     * 截止时间
     */
    private String jzTime;


}
