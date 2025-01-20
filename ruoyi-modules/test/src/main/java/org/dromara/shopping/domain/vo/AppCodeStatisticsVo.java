package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author xiexi
 * @description
 * @date 2024/7/2 15:01
 */
@Data
public class AppCodeStatisticsVo {
    private Long shopId;
    private String shopName;
    private Long productId;
    private String productName;
    private Long ct;
    private Long verifierCount;
    private Long appointmentCount;
    private Date appointmentDate;
    private String startTime;
    private String endTime;
}
