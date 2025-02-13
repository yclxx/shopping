package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 22:06
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpMsgBossJobVo {
    /*
    {
            "jobName": "后端Java工程师",
            "salaryDesc": "15-20K",
            "brandName": "数砚科技",
            "locationName": "广州",
            "proxyJob": 0,
            "proxyType": 0,
            "jobSource": 0,
            "degreeName": "本科",
            "lowSalary": 15,
            "highSalary": 20,
            "experienceName": "5-10年"
        }
     */

    private String jobName;
    private String salaryDesc;
    private String brandName;
    private String locationName;
    private Long proxyJob;
    private Long proxyType;
    private Long jobSource;
    private String degreeName;
    private Long lowSalary;
    private Long highSalary;
    private String experienceName;
}
