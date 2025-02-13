package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/18 21:22
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpUserInfoVo {
    /*
        "userId": 79775643,
        "identity": 0,
        "encryptUserId": "247ba82a5a7817880H193Ni7FFE~",
        "name": "谢茜",
        "showName": "谢茜",
        "tinyAvatar": "https://img.bosszhipin.com/beijin/upload/avatar/20191026/efa2d3cbccd5d061ae9148fafda5f200681a39419070c5b77e7d6ededc3d31be_s.png",
        "largeAvatar": "https://img.bosszhipin.com/beijin/upload/avatar/20191026/efa2d3cbccd5d061ae9148fafda5f200681a39419070c5b77e7d6ededc3d31be.png",
        "token": "OGrDF30Eu5GwT9G",
        "isHunter": false,
        "clientIP": "2409:8a28:8ce:f4f0:d115:f86f:dfce:26c2",
        "email": null,
        "phone": null,
        "brandName": null,
        "doubleIdentity": false,
        "recruit": false,
        "agentRecruit": false,
        "industryCostTag": 0,
        "gender": 0,
        "trueMan": false,
        "studentFlag": false,
        "completeDayStatus": false,
        "complete": true,
        "multiExpect": false,
        "encryptComId": null
     */
    private Long userId;
    private Long identity;
    private String encryptUserId;
    private String name;
    private String showName;
    private String tinyAvatar;
    private String largeAvatar;
    private String token;
    private Boolean isHunter;
    private String clientIP;
    private String email;
    private String phone;
    private String brandName;
    private Boolean doubleIdentity;
    private Boolean recruit;
    private Boolean agentRecruit;
    private Long industryCostTag;
    private Long gender;
    private Boolean trueMan;
    private Boolean studentFlag;
    private Boolean completeDayStatus;
    private Boolean complete;
    private Boolean multiExpect;
    private Object encryptComId;
}
