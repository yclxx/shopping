package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 活动核销人员关联对象 t_verifier_activity
 *
 * @author yzg
 * @date 2023-12-14
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_verifier_activity")
public class VerifierActivity extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 平台标识
     */
    private Long platformKey;
    /**
     * 活动id
     */
    private Long activityId;
    /**
     * 核销人员id
     */
    private Long verifierId;
    /**
     * 活动名称
     */
    private String activityName;
    /**
     * 支持端
     */
    private String channel;

}
