package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 浏览任务对象 t_browse
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_browse")
public class Browse extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "browse_id")
    private Long browseId;
    /**
     * 任务名称
     */
    private String browseName;
    /**
     * 奖励说明
     */
    private String browseRemark;
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
     * 展示城市
     */
    private String showCity;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 支持端
     */
    private String supportChannel;
    /**
     * 指定周几
     */
    private String assignDate;
    /**
     * 周几能领
     */
    private String weekDate;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 排序
     */
    private Long sort;
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
