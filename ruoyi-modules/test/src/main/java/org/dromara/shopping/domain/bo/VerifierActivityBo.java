package org.dromara.shopping.domain.bo;

import org.dromara.common.mybatis.core.domain.BaseEntity;
import com.ruoyi.zlyyh.domain.ActivityMerchant;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * 活动核销人员关联业务对象
 *
 * @author yzg
 * @date 2023-12-14
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class VerifierActivityBo extends BaseEntity {

    /**
     *
     */
    //@NotNull(message = "不能为空", groups = { EditGroup.class })
    private Long id;

    /**
     * 平台标识
     */
    //@NotNull(message = "平台标识不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long platformKey;

    /**
     * 活动id
     */
    //@NotNull(message = "活动id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long activityId;

    /**
     * 核销人员id
     */
    //@NotNull(message = "核销人员id不能为空", groups = { AddGroup.class, EditGroup.class })
    private Long verifierId;

    /**
     * 活动名称
     */
    //@NotBlank(message = "活动名称不能为空", groups = { AddGroup.class, EditGroup.class })
    private String activityName;

    /**
     * 支持端
     */
    //@NotBlank(message = "支持端不能为空", groups = { AddGroup.class, EditGroup.class })
    private String channel;

    private List<ActivityMerchant> activityMerchant;
}
