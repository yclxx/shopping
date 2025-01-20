package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

import java.util.Date;



/**
 * 邀请记录视图对象
 *
 * @author yzg
 * @date 2023-08-08
 */
@Data
@ExcelIgnoreUnannotated
public class InviteUserLogVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 用户ID
     */
    @ExcelProperty(value = "用户ID")
    private Long userId;

    /**
     * 被邀请用户ID
     */
    @ExcelProperty(value = "被邀请用户ID")
    private Long inviteUserId;

    /**
     * 被邀请用户所在城市
     */
    @ExcelProperty(value = "被邀请用户所在城市")
    private String inviteCityName;

    /**
     * 被邀请用户城市行政区号
     */
    @ExcelProperty(value = "被邀请用户城市行政区号")
    private String inviteCityCode;

    /**
     * 订单号
     */
    @ExcelProperty(value = "订单号")
    private Long number;

    /**
     * 任务ID
     */
    @ExcelProperty(value = "任务ID")
    private Long missionId;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 创建时间
     */
    @ExcelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 支持端
     */
    @ExcelProperty(value = "支持端")
    private String supportChannel;

}
