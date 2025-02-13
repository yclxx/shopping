package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 15:49
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpMsgInfoListVo {
    /*
     *
            {
                "friendSource": 0,
                "securityId": "hf51ZwW2x3HOu-41qsucxMSeRownwvAwGYuu5NSfBLYxQYp5NSP3qiYOpkQAW4yPJPAHg3n5o0ypAdIikS9tcfnvUhqjBDmMRD8fHoyyfRqKbpRfAyCeCq8jbVLkgKbumBpAtl4gt2GQCwW2EkdWwbz2J2ZETbZQtTuffrF_dkEh0XHTiXZ19To~",
                "name": "张女士",
                "avatar": "https://img.bosszhipin.com/beijin/upload/avatar/20240905/607f1f3d68754fd063503ceff7c0d177ebdfcddecb0fb8ad2b2f6f0b373bbea0a0cc155e1e1bf343_s.jpg.webp",
                "isTop": 0,
                "sourceTitle": "",
                "relationType": 1,
                "lastMsg": "日结周结月结都可以的,聊聊吗?",
                "lastMessageInfo": {
                    "msgId": 150274220893697,
                    "encryptMsgId": "1c0e274cc3f369821nF62dq5ElBQyoW8V_OY",
                    "showText": "日结周结月结都可以的,聊聊吗?",
                    "fromId": 666274801,
                    "toId": 79775643,
                    "status": 0,
                    "msgTime": 1731827242194
                },
                "lastTime": "15:07",
                "lastTS": 1731827242194,
                "sourceType": 0,
                "sourceExtend": null,
                "jobId": 396976158,
                "jobSource": 0,
                "encryptJobId": "d123cefb53ad45fb1H180tq7EVdY",
                "itemType": 0,
                "title": "招聘主管",
                "brandName": "急达",
                "unreadMsgCount": 0,
                "filterReasonList": null,
                "encryptBossId": "12062437a81cf1c00XJ82dq5GFJR",
                "filtered": false,
                "tinyUrl": "https://img.bosszhipin.com/beijin/upload/avatar/20240905/607f1f3d68754fd063503ceff7c0d177ebdfcddecb0fb8ad2b2f6f0b373bbea0a0cc155e1e1bf343_s.jpg.webp",
                "uid": 666274801,
                "encryptUid": "12062437a81cf1c00XJ82dq5GFJR",
                "isFiltered": false
            },
     */

    private Long friendSource;
    private String securityId;
    private String name;
    private String avatar;
    private Long isTop;
    private String sourceTitle;
    private Long relationType;
    private String lastMsg;
    private ZpLastMessageInfoVo lastMessageInfo;
    private String lastTime;
    private Long lastTS;
    private Long sourceType;
    private Object sourceExtend;
    private Long jobId;
    private Long jobSource;
    private String encryptJobId;
    private Long itemType;
    private String title;
    private String brandName;
    private Long unreadMsgCount;
    private Object filterReasonList;
    private String encryptBossId;
    private Boolean filtered;
    private String tinyUrl;
    private Long uid;
    private String encryptUid;
    private Boolean isFiltered;
}
