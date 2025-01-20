package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 到货清单对象 t_product_arrival_list
 *
 * @author yzg
 * @date 2024-05-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_arrival_list")
public class ProductArrivalList extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 库存id
     */
    @TableId(value = "arrival_list_id")
    private Long arrivalListId;
    /**
     * 产品名称
     */
    private String productName;
    /**
     * 日期
     */
    private Date listDate;
    /**
     * 数量
     */
    private Long qty;
    /**
     * 发货时间
     */
    private Date sendTime;
    /**
     * 到货时间
     */
    private Date arriveTime;
    /**
     * 确认到货  0-是  1-否
     */
    private String isArrive;
    /**
     * 删除标志  0-存在 2-删除
     */
    @TableLogic
    private String delFlag;

    private Long productId;

    private Long platformKey;

}
