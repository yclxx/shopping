package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * 订单数据统计（月份）业务对象
 *
 * @author yzg
 * @date 2023-07-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductComputeMonthBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long id;

    /**
     * 统计月份
     */
    @NotBlank(message = "统计月份不能为空", groups = { AddGroup.class, EditGroup.class })
    private String month;

    /**
     * 商品编号
     */
    @NotNull(message = "商品编号不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productId;

    /**
     * 行政区号
     */
    @NotBlank(message = "行政区号不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cityCode;

    /**
     * 城市
     */
    @NotBlank(message = "城市不能为空", groups = { AddGroup.class, EditGroup.class })
    private String cityName;

    /**
     * 每个城市用户人数
     */
    @NotNull(message = "每个城市用户人数不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cityUserNumber;

    /**
     * 每个城市订单数量
     */
    @NotNull(message = "每个城市订单数量不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long cityOrderNumber;
}
