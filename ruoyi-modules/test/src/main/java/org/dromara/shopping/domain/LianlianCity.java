package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 联联市级城市对象 t_lianlian_city
 *
 * @author yzg
 * @date 2023-09-15
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_lianlian_city")
public class LianlianCity extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "city_id")
    private Long cityId;
    /**
     * 城市名称
     */
    private String cityName;
    /**
     * 城市区号
     */
    private String cityCode;
    /**
     * 状态：0-可用，1-禁用
     */
    private String status;

}
