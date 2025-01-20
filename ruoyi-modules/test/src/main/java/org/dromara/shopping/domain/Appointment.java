package org.dromara.shopping.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import org.dromara.common.mybatis.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Date;

/**
 * 预约信息对象 t_appointment
 *
 * @author yzg
 * @date 2024-06-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("t_appointment")
public class Appointment extends BaseEntity {

    private static final long serialVersionUID=1L;

    /**
     * ID
     */
    @TableId(value = "id")
    private Long id;
    /**
     * 商品ID
     */
    private Long productId;
    /**
     * 套餐ID
     */
    private Long productSkuId;
    /**
     * 可预约时间
     */
    private Date appointmentDate;
    /**
     * 可预约数量
     */
    private Long appointmentCount;
    /**
     * 删除标志
     */
    @TableLogic
    private Long delFlag;
    /**
     * 乐观锁
     */
    @Version
    private Long version;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 截止时间
     */
    private String jzTime;

}
