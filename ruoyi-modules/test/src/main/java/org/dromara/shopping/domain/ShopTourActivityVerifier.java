package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 巡检活动白名单对象 t_shop_tour_activity_verifier
 *
 * @author yzg
 * @date 2024-04-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_shop_tour_activity_verifier")
public class ShopTourActivityVerifier extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * id
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 巡检活动id
     */
    private Long tourActivityId;
    /**
     * 巡检人员id,bd
     */
    private Long verifierId;

}
