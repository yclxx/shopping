package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 15:17
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpMsgFriendVo {
    // {"friendId":683434327,
    // "friendSource":0,
    // "encryptFriendId":"379b084bf0ed28020Xx53965E1BX",
    // "name":"杨茜",
    // "updateTime":1731671391000,
    // "brandName":"纬创软件"}
    private Long friendId;
    private Long friendSource;
    private String encryptFriendId;
    private String name;
    private Long updateTime;
    private String brandName;
    private String friendIdStr;

    public String getFriendIdStr() {
        if (null == friendId) {
            return null;
        }
        return friendId.toString();
    }
}
