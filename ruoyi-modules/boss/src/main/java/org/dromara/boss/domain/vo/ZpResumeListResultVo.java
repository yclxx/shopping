package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 22:19
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpResumeListResultVo {
    /*
    {
        //        "supportVideoResume": false,
        //        "resumeList": [
        //            {
        //                "resumeId": "376961d20775af2c1nB62Nq9EVpUwI-4VPOe",
        //                "showName": "谢茜-JAVA.pdf",
        //                "resumeSize": 378778,
        //                "resumeSizeDesc": "369.9KB",
        //                "suffixName": "pdf",
        //                "annexType": 0,
        //                "uploadTime": "2024.10.20 15:27",
        //                "parserId": null,
        //                "syncStatus": 0,
        //                "previewType": 1,
        //                "restricted": false,
        //                "cvId": "",
        //                "securityStatus": 0,
        //                "restrictedDays": -1,
        //                "target": 0,
        //                "nlpParserType": 0,
        //                "pdf2Image": false
        //            }
        //        ],
        //        "videoResumeList": [],
        //        "supportAnnexType": false,
        //        "supportCommonResume": true,
        //        "showUploadBtnType": false,
        //        "complete": true,
        //        "maxCount": 3,
        //        "resumeCount": 1
        //    }
     */
    private Boolean supportVideoResume;
    private List<ZpResumeListVo> resumeList;
    private List<Object> videoResumeList;
    private Boolean supportAnnexType;
    private Boolean supportCommonResume;
    private Boolean showUploadBtnType;
    private Boolean complete;
    private Long maxCount;
    private Long resumeCount;
}
