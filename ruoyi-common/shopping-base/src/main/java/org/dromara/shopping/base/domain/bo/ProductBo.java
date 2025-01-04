package org.dromara.shopping.base.domain.bo;

import org.dromara.shopping.base.domain.Product;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import org.dromara.common.core.validate.AddGroup;
import org.dromara.common.core.validate.EditGroup;
import io.github.linpeilie.annotations.AutoMapper;
import lombok.Data;
import lombok.EqualsAndHashCode;
import jakarta.validation.constraints.*;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 商品信息业务对象 t_product
 *
 * @author Xie Xi
 * @date 2025-01-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@AutoMapper(target = Product.class, reverseConvertGenerate = false)
public class ProductBo extends BaseEntity {

    /**
     * 商品ID
     */
    @NotNull(message = "商品ID不能为空", groups = { EditGroup.class })
    private Long productId;

    /**
     * 商品名称
     */
    @NotBlank(message = "商品名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productName;

    /**
     * 商品图片
     */
    @NotBlank(message = "商品图片不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long productImg;

    /**
     * 商品类型
     */
    @NotBlank(message = "商品类型不能为空", groups = { AddGroup.class, EditGroup.class })
    private String productType;

    /**
     * 状态
     */
    @NotBlank(message = "状态不能为空", groups = { AddGroup.class, EditGroup.class })
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
    @NotNull(message = "展示结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date showEndDate;

    /**
     * 售卖开始时间
     */
    private Date sellStartDate;

    /**
     * 售卖结束时间
     */
    @NotNull(message = "售卖结束时间不能为空", groups = { AddGroup.class, EditGroup.class })
    private Date sellEndDate;

    /**
     * 售价
     */
    @NotNull(message = "售价不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long price;

    /**
     * 原价
     */
    private Long otPrice;


}
