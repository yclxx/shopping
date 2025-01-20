package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商品组规则配置对象 t_product_group
 *
 * @author yzg
 * @date 2024-01-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product_group")
public class ProductGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "product_group_id")
    private Long productGroupId;
    /**
     * 商品组名称
     */
    private String productGroupName;
    /**
     * 用户端提醒
     */
    private String productGroupTip;
    /**
     * 每日同一用户限领数量，0为不限制
     */
    private Long dayUserCount;
    /**
     * 每周同一用户限领数量，0为不限制
     */
    private Long weekUserCount;
    /**
     * 当月同一用户限领数量，0为不限制
     */
    private Long monthUserCount;
    /**
     * 活动周期同一用户限领数量，0为不限制
     */
    private Long totalUserCount;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
