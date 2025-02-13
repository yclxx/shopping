package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/9 22:08
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpAddResultDataVo {
    private Boolean showGreeting;
    private String greeting;
    private String securityId;
    private Long bossSource;
    private String source;
    private String encBossId;
}
