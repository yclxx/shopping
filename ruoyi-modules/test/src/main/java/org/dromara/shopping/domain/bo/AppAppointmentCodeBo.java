package org.dromara.shopping.domain.bo;

import lombok.Data;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Data
public class AppAppointmentCodeBo {

    private static final long serialVersionUID = 1L;

    /** 门店ID */
    @NotNull(message = "预约门店不能为空")
    private Long shopId;
    /** 券码code */
    @NotBlank(message = "核销码不能为空")
    private String code;
    /** 预约日期 */
    @NotNull(message = "预约日期不能为空")
    private Long dateId;

    private Long userId;

    /**
     * 平台端
     */
    private String channel;

    /**
     * 客户电话
     */
    private String customMobile;


    /**
     * 后台用id
     */
    private Long codeId;


}
