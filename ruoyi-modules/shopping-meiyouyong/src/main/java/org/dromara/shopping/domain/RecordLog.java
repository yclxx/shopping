package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 记录日志对象 t_record_log
 *
 * @author yzg
 * @date 2023-08-01
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_record_log")
public class RecordLog extends BaseEntity {

    private static final long serialVersionUID=1L;

    @TableId(value = "record_id")
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
     * 订单购买次数
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
