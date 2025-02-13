package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 15:55
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpLastMessageInfoVo {
    /*
    {
                    "msgId": 150274220893697,
                    "encryptMsgId": "1c0e274cc3f369821nF62dq5ElBQyoW8V_OY",
                    "showText": "日结周结月结都可以的,聊聊吗?",
                    "fromId": 666274801,
                    "toId": 79775643,
                    "status": 0,
                    "msgTime": 1731827242194
                }
     */
    private Long msgId;
    private String encryptMsgId;
    private String showText;
    private Long fromId;
    private Long toId;
    private Long status;
    private Long msgTime;
}
