package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/10 17:55
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpDetailAtsOnlineApplyInfoVo {
    //        {
    //            "inviteType": 0,
    //                "alreadyApply": false
    //        }
    private Long inviteType;
    private Boolean alreadyApply;
}
