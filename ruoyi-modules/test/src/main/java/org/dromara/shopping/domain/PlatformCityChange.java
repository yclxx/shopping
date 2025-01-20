package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 平台切换对象 t_platform_city_change
 *
 * @author yzg
 * @date 2024-03-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_city_change")
public class PlatformCityChange extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 行政编码
     */
    private String adcode;
    /**
     * 替换后平台
     */
    private Long changePlatformKey;
    /**
     * 状态
     */
    private String status;
    /**
     * 生效时间
     */
    private Date startTime;
    /**
     * 失效时间
     */
    private Date endTime;
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;

}
