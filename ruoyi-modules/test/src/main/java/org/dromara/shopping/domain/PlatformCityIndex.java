package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 自定义首页对象 t_platform_city_index
 *
 * @author yzg
 * @date 2023-08-07
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_platform_city_index")
public class PlatformCityIndex extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 行政编码
     */
    private String adcode;
    /**
     * 状态
     */
    private String status;
    /**
     * 跳转类型
     */
    private String toType;
    /**
     * 小程序ID
     */
    private String appId;
    /**
     * 页面地址
     */
    private String url;
    /**
     * 生效时间
     */
    private Date startTime;
    /**
     * 失效时间
     */
    private Date endTime;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 指定周几
     */
    private String assignDate;
    /**
     * 周几能领
     */
    private String weekDate;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
