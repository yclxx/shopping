package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 演出(场次)日期对象 t_product_ticket_session
 *
 * @author yzg
 * @date 2023-09-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_ticket_session")
public class ProductTicketSession extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 数据id
     */
    @TableId(value = "session_id")
    private Long sessionId;
    /**
     * 商品id
     */
    private Long productId;
    /**
     * 场次名称
     */
    private String session;
    /**
     * 状态 0正常 1停用
     */
    private String status;
    /**
     * 是否时间范围
     */
    private String isRange;
    /**
     * 开始时间
     */
    private Date beginDate;
    /**
     * 结束时间
     */
    private Date endDate;
    /**
     * 日期
     */
    private Date date;
    /**
     * 说明
     */
    private String description;
}
