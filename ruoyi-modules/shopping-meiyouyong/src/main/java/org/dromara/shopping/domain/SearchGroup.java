package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 搜索彩蛋配置对象 t_search_group
 *
 * @author yzg
 * @date 2023-07-24
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_search_group")
public class SearchGroup extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "search_id")
    private Long searchId;
    /**
     * 搜索内容
     */
    private String searchContent;
    /**
     * 商品编号
     */
    private Long productId;
    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转，6-页面指定位置
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
     * 展示城市：ALL-全部、否则填城市行政区号，多个之间用英文逗号隔开
     */
    private String showCity;
    /**
     * 指定周几：0-不指定，1-指定周几
     */
    private String assignDate;
    /**
     * 周几能领：1-周日，2-周一，3-周二...7-周六，多个之间用英文逗号隔开
     */
    private String weekDate;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 支持端
     */
    private String supportChannel;

    /**
     * 部门id
     */
    private Long sysDeptId;

    /**
     * 用户id
     */
    private Long sysUserId;
}
