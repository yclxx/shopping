package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/10 16:18
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpRandKeyResultVo {
    private String qrId;
    private String randKey;
    private String secretKey;
    private String shortRandKey;
    private String qrCodeImg;
}
