package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 22:20
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpResumeListVo {
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

    private String resumeId;
    private String showName;
    private Long resumeSize;
    private String resumeSizeDesc;
    private String suffixName;
    private Long annexType;
    private String uploadTime;
    private String parserId;
    private Long syncStatus;
    private Long previewType;
    private Boolean restricted;
    private String cvId;
    private Long securityStatus;
    private Long restrictedDays;
    private Long target;
    private Long nlpParserType;
    private Boolean pdf2Image;
}
