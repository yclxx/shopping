package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * 演出票种业务对象
 *
 * @author yzg
 * @date 2023-09-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTicketLineBo extends BaseEntity {

    /**
     * 票种id
     */
    @NotNull(message = "票种id不能为空", groups = {EditGroup.class})
    private Long lineId;

    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long productId;

    /**
     * 场次id
     */
    @NotNull(message = "场次id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long sessionId;

    /**
     * 第三方id
     */
    //@NotNull(message = "第三方id不能为空", groups = {AddGroup.class, EditGroup.class})
    private String otherId;

    /**
     * 票种名称
     */
    @NotBlank(message = "票种名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String lineTitle;

    /**
     * 销售价格
     */
    @NotNull(message = "销售价格不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal linePrice;
    /**
     * 结算价格
     */
    @NotNull(message = "结算价格不能为空", groups = {AddGroup.class, EditGroup.class})
    private BigDecimal lineSettlePrice;
    /**
     * 最大数量
     */
    @NotNull(message = "价格不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long lineNumber;
    /**
     * 单次购买上限
     */
    @NotNull(message = "单次购买上限不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long lineUpperLimit;
    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String lineStatus;
    /**
     * 说明
     */
    private String lineDescription;
}
