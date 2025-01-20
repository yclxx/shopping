package org.dromara.shopping.domain.bo;

import org.dromara.common.core.validate.EditGroup;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import jakarta.validation.constraints.NotNull;

/**
 * 记录日志业务对象
 *
 * @author yzg
 * @date 2023-08-01
 */

@Data
@EqualsAndHashCode(callSuper = true)
public class RecordLogBo extends BaseEntity {

    /**
     *
     */
    @NotNull(message = "不能为空", groups = {EditGroup.class})
    private Long recordId;

    /**
     * 平台标识
     */
    private Long platformKey;

    /**
     * 用户点击次数
     */
    private Long userNumber;

    /**
     * 用户人数
     */
    private Long userPeople;

    /**
     * 订单购买次数
     */
    private Long orderBuyNumber;

    /**
     * 用户购买人数
     */
    private Long orderBuyUser;

    /**
     * 记录日期
     */
    private String recordDate;

    /**
     * 来源
     */
    private String source;

    /**
     * 支持端
     */
    private String supportChannel;


}
