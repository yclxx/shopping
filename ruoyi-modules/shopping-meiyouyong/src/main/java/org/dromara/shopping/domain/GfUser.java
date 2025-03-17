package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 广发用户对象 t_gf_user
 *
 * @author yzg
 * @date 2024-05-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_gf_user")
public class GfUser extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id")
    private Long userId;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 状态（0正常 1停用）
     */
    private String status;
    /**
     * 发放会员（0未发放 1已发放）
     */
    private String sendVip;
    /**
     * 发放会员时间
     */
    private Date sendVipTime;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;

}
