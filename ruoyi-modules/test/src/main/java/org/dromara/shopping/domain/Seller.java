package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 店家配置对象 t_seller
 *
 * @author yzg
 * @date 2024-10-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_seller")
public class Seller extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商家名称
     */
    private String sellerName;
    /**
     * 展示名称
     */
    private String showName;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 展示内容类型：0-图片
     */
    private String showContentType;
    /**
     * 展示内容
     */
    private String showContent;
    /**
     * 跳转类型：0-无需跳转，1-内部页面，2-外部页面，3-小程序跳转，4-图片页面，5-RN跳转
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
     * 平台标识
     */
    private Long platformKey;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;
    /**
     * 部门id
     */
    private Long sysDeptId;
    /**
     * 用户id
     */
    private Long sysUserId;

}
