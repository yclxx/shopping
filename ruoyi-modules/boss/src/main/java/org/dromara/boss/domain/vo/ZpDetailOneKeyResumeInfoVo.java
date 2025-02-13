package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/10 17:54
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpDetailOneKeyResumeInfoVo {
    //        {
    //            "inviteType": 0,
    //                "alreadySend": false,
    //                "canSendResume": true,
    //                "canSendPhone": false,
    //                "canSendWechat": false
    //        }
    private Long inviteType;
    private Boolean alreadySend;
    private Boolean canSendResume;
    private Boolean canSendPhone;
    private Boolean canSendWechat;
}
