package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * 巡检活动白名单视图对象
 *
 * @author yzg
 * @date 2024-04-16
 */
@Data
@ExcelIgnoreUnannotated
public class ShopTourActivityVerifierVo {

    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ExcelProperty(value = "id")
    private Long id;

    /**
     * 巡检活动id
     */
    @ExcelProperty(value = "巡检活动id")
    private Long tourActivityId;

    /**
     * 巡检人员id,bd
     */
    @ExcelProperty(value = "巡检人员id,bd")
    private Long verifierId;

    private VerifierVo verifierVo;


}
