package org.dromara.shopping.base.domain;

import org.dromara.common.tenant.core.TenantEntity;
import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.Date;

import java.io.Serial;

/**
 * 商品信息对象 t_product
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_product")
public class Product extends TenantEntity {

    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "product_id")
    private Long productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品图片
     */
    private Long productImg;

    /**
     * 商品类型
     */
    private String productType;

    /**
     * 状态
     */
    private String status;

    /**
     * 排序
     */
    private Long sort;

    /**
     * 被搜索
     */
    private String searchStatus;

    /**
     * 显示首页
     */
    private String showIndex;

    /**
     * 展示开始时间
     */
    private Date showStartDate;

    /**
     * 展示结束时间
     */
    private Date showEndDate;

    /**
     * 售卖开始时间
     */
    private Date sellStartDate;

    /**
     * 售卖结束时间
     */
    private Date sellEndDate;

    /**
     * 售价
     */
    private Long price;

    /**
     * 原价
     */
    private Long otPrice;

    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;


}
