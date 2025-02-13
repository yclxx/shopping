package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 15:15
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpMsgListResultVo {
    private String foldText;
    private List<Object> filterEncryptIdList;
    private List<ZpMsgFriendVo> friendList;
    private List<Object> filterBossIdList;
}
