package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 详情接口返回数据
 *
 * @author xiexi
 * @description
 * @date 2024/11/9 20:03
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpDetailInfoVo {
    private Long pageType;
    private Boolean selfAccess;
    private String securityId;
    private String sessionId;
    private String lid;
    private ZpDetailJobInfoVo jobInfo;
    private ZpDetailBossInfoVo bossInfo;
    private ZpDetailBrandComInfoVo brandComInfo;
    private ZpDetailOneKeyResumeInfoVo oneKeyResumeInfo;
    private ZpDetailRelationInfoVo relationInfo;
    private ZpDetailHandicappedInfoVo handicappedInfo;
    private ZpDetailAppendixInfoVo appendixInfo;
    private ZpDetailAtsOnlineApplyInfoVo atsOnlineApplyInfo;
}
