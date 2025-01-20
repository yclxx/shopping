package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 商圈门店关联对象 t_business_district_shop
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_business_district_shop")
public class BusinessDistrictShop extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商圈ID
     */
    private Long businessDistrictId;
    /**
     * 门店ID
     */
    private Long shopId;

}
