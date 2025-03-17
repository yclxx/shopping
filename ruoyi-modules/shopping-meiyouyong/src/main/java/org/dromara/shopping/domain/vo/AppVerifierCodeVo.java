package org.dromara.shopping.domain.vo;


import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class AppVerifierCodeVo {

    private static final long serialVersionUID = 1L;

    /**
     * 查询状态,0成功，1失败
     */
    private Integer status = 0;
    /**
     * 返回说明
     */
    private String msg = "查询成功";

    private List<ShopVo> shopVoList = new ArrayList<>();

    private CodeVo codeVo;
    private OrderVo orderVo;
    /** 是否需要在线预约，0不需要，1-需要 */
    private String appointMent;
    private String appointmentRemark;

    private List<AppointmentVo> appointmentVoList;
    /**
     * 组装预约列表的map
     */
    private Map<Date, List<AppointmentVo>> appointmentMap;
}
