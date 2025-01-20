package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 推广任务记录对象 t_promotion_log
 *
 * @author yzg
 * @date 2023-11-22
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_promotion_log")
public class PromotionLog extends BaseEntity {

    private static final long serialVersionUID=1L;

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
     * 商户申请表id
     */
    private Long approvalId;
    /**
     * 品牌名称
     */
    private String approvalBrandName;
    /**
     * id
     */
    private Long taskId;
    /**
     * 任务名称
     */
    private String taskName;
    /**
     * 核销员id
     */
    private Long verifierId;
    /**
     * 核销员手机号
     */
    private String verifierMobile;
    /**
     * 状态
     */
    private String status;
    /**
     * 拒绝原因
     */
    private String reason;

}
