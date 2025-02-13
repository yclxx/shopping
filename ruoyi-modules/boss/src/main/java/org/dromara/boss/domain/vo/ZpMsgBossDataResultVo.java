package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 22:02
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpMsgBossDataResultVo {
    /*
    {
        "data": {
            "bossFreezeStatus": 0,
            "companyName": "数砚科技",
            "tinyUrl": "https://img.bosszhipin.com/beijin/upload/avatar/20200416/b96229b6eab2e6a13a41ab672f0c3d44be095a55e53ec471cadf6b2b43fe5b45_s.png",
            "title": "招聘者",
            "mobileVisible": 0,
            "encryptJobId": "c421408289b9ba0e0HBy3N61E1o~",
            "regionCode": "",
            "bothTalked": true,
            "encryptBossId": "b1721a63cebb851d1nR-3t60E1NY",
            "warningTips": [],
            "bossId": 104539318,
            "weixinVisible": 0,
            "mobile": "",
            "securityId": "Br9tv25vGgz4R-O1QHpMRGuz6DWholU_uDcHVJ0T3CpyhgHMxXzi8XHJcqSAvJjjDHQanGooFLdk8mEN2vfzgY4epgsMTV0R8JJHhze3JskgiqfSssVP4EM~",
            "bossPreFreezeStatus": 0,
            "weixin": "",
            "curTime": 1731851587396,
            "isTop": 0,
            "name": "赵先生",
            "hasInterview": false,
            "bossSource": 0
        },
        "job": {
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
    }
     */

    private ZpMsgBossDataVo data;
    private ZpMsgBossJobVo job;
}
