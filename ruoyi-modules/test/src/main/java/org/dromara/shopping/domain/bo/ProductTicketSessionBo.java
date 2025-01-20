package org.dromara.shopping.domain.bo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * 演出(场次)日期业务对象
 *
 * @author yzg
 * @date 2023-09-12
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductTicketSessionBo extends BaseEntity {
    /**
     * 数据id
     */
    @NotNull(message = "数据id不能为空", groups = {EditGroup.class})
    private Long sessionId;
    /**
     * 商品id
     */
    @NotNull(message = "商品id不能为空", groups = {AddGroup.class, EditGroup.class})
    private Long productId;
    /**
     * 场次名称
     */
    @NotBlank(message = "场次名称不能为空", groups = {AddGroup.class, EditGroup.class})
    private String session;
    /**
     * 状态 0正常 1停用
     */
    @NotBlank(message = "状态不能为空", groups = {AddGroup.class, EditGroup.class})
    private String status;
    /**
     * 日期
     */
    @NotNull(message = "日期不能为空", groups = {AddGroup.class, EditGroup.class})
    private Date date;
    /**
     * 是否时间范围
     */
    private String isRange;
    /**
     * 开始时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date beginDate;
    /**
     * 结束时间
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date endDate;
    /**
     * 票种信息
     */
    private List<ProductTicketLineBo> ticketLine;
    /**
     * 说明
     */
    private String description;
}
