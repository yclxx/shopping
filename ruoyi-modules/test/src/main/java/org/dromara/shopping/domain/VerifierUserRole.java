package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户和角色关联对象 t_verifier_user_role
 *
 * @author yzg
 * @date 2024-11-26
 */
@Data
@TableName("t_verifier_user_role")
public class VerifierUserRole implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    @TableId(value = "user_id", type = IdType.INPUT)
    private Long userId;
    /**
     * 角色ID
     */
    private Long roleId;

}
