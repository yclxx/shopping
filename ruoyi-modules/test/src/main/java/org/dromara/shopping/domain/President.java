package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 支行长对象 t_president
 *
 * @author yzg
 * @date 2024-04-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_president")
public class President extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * 支行长id
     */
    @TableId(value = "president_id")
    private Long presidentId;
    /**
     * 姓名
     */
    private String name;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * openId
     */
    private String openId;
    /**
     * 银行
     */
    private String bank;
    /**
     * 一支
     */
    private String linkmanBranch;
    /**
     * 二支
     */
    private String linkmanBranchSecond;
    /**
     * 状态  0-正常  1-停用
     */
    private String status;
    /**
     * 删除状态  0-存在  2-删除
     */
    @TableLogic
    private String delFlag;

    private Long platformKey;

    private String identification;

}
