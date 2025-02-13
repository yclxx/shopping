package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 16:47
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpMsgInfoResultVo {
    private Boolean hasMore;
    private List<ZpMsgHistoryListVo> messages;
    private Long type;
    private Long minMsgId;
}
