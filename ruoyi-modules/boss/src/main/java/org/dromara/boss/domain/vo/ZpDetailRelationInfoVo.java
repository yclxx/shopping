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
public class ZpDetailRelationInfoVo {
    //        {
    //            "interestJob": false,
    //                "beFriend": false
    //        }
    private Boolean interestJob;
    private Boolean beFriend;
}
