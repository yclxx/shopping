package org.dromara.boss.controller;

import cn.hutool.core.thread.ThreadUtil;
import lombok.extern.slf4j.Slf4j;
import org.dromara.boss.domain.vo.*;
import org.dromara.boss.utils.ZpUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 15:03
 */
@Slf4j
public class BossMsgTest {

    public static void main(String[] args) {
        String cookie = "ab_guid=41f96b5b-6c82-49dd-b139-d51cef2ba072; lastCity=101210100; wt2=DdAWpKAC3TU4YXGxO5c92b4Ww5dCwO216bJV9HXHn-tzJVTjfMq8c284jq7NQSI9jjr5STdio5OnbF4PE5vylsw~~; wbg=0; zp_at=8uzOqVO-MsdO1DQBIKutfMn6wZmbi_6YxJ7kinJz1v8~; __g=-; __zp_stoken__=9422fOzzDs8K6wrXCuFEtDwgHIgxRLTw8Kl81OyhSPVE7PDw%2FUzs8NB09K2TCpQUgVcOLHD0vOzxTPTo1PDxQHztQxLrCujs6JnfCpCIhcsOLDsO%2BwrwPGcK9CWjCvA%2FDkMK9CMK%2FwrsrKMKTwr07OjU8W8K%2BOsK3DMK%2BO8OQDcK5O8OROj08O0E%2BDQchID49T0peCE9zS1RyYw5iYmMvPFA9PVDCjSY%2BIAcGISEOCQgPDwwLCiAgCg0MCwshBgcgICU6wpfCu8Ohw4TDoMOBxLDFqcOjw4LEtcKow5zCjMS1w6zEr8KexKTDg8Omw7vErsOBxJPCisS0wqfEucKpw5bCm8O%2BY8STSsOsTMOdwrzDqsKsw4rCj8SDwp7DvcK7wqLCuMOtTsO%2BwqXCm8K7w7zCsMO4wqbChkrCssKDwqnCt8Kqwr%2FDkGPCiEnCm3PDg15EwqhKZE1YZnVnZ1tFwrd%2FwoBvwr5Xe2FdISIHIT9fNQzDjw%3D%3D; Hm_lvt_194df3105ad7148dcf2b98a91b5e727a=1731372868,1731744579,1731897500,1731934618; Hm_lpvt_194df3105ad7148dcf2b98a91b5e727a=1731934618; HMACCOUNT=DEA65BA8B3E75B7F; __c=1731934616; __a=92813523.1731325621.1731897500.1731934616.51.6.2.51; bst=V2RtMgFuH73l1gXdJqzRQRKimw7DLfwQ~~|RtMgFuH73l1gXdJqzRQRKimw7DnUwg~~";
        String zpToken = "V2RtMgFuH73l1gXdJqzRQRKimw7DLfwQ~~|RtMgFuH73l1gXdJqzRQRKimw7DnUwg~~";
        String token = "OGrDF30Eu5GwT9G";
        String traceid = "F-59877cTKMtwG8qwS";

        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", cookie);
        headers.put("zp_token", zpToken);
        headers.put("token", token);
        headers.put("traceid", traceid);

        ZpUtils instance = ZpUtils.getInstance(headers);

        ZpMsgListResultVo zpMsgListResultVo = instance.queryMsgList(true);

        // 取前100条
        List<ZpMsgFriendVo> zpMsgFriendVos = zpMsgListResultVo.getFriendList().subList(0, 100);

        // 获取friendId
        String friendIds = zpMsgFriendVos.stream().map(ZpMsgFriendVo::getFriendIdStr).collect(Collectors.joining(","));

        List<ZpMsgInfoListVo> zpMsgInfoListVos = instance.queryMsgInfoList(friendIds, true);

        ZpMsgInfoListVo zpMsgInfoListVo = zpMsgInfoListVos.get(0);

        ThreadUtil.sleep(2000);
        // 获取回话boss信息，https://www.zhipin.com/wapi/zpchat/geek/getBossData?bossId=b1721a63cebb851d1nR-3t60E1NY&bossSource=0&securityId=8b5u1ZZidYzg--o18QYaQPxATFlas7HFdnqKRSQM1WGFv8NybOmlsxTA3lldOboPrWRt58HknFIgwW4bgSEhXL-LkApKwfm1sieNlKyfdyZpaSQvaYUmSPkt9lpJfD2A52jGqXCS7NoWtLRmF7tM1jvdZsaUg2md_gpoBas-mVere18Kk7kqHw~~
        // 返回结果中的securityId 发送简历需要
        ZpMsgBossDataResultVo zpMsgBossDataResultVo = instance.queryBossData(zpMsgInfoListVo.getEncryptBossId(), zpMsgInfoListVo.getSecurityId(), true);

        List<ZpMsgHistoryListVo> zpMsgHistoryListVos = instance.queryMsgInfo(zpMsgInfoListVo.getEncryptBossId(), zpMsgInfoListVo.getSecurityId(), true);

        // 发送简历
        // 三个请求
        // post
        // https://www.zhipin.com/wapi/zpCommon/actionLog/common.json
        // p 为 bossId
        // 参数：ba: {"action":"chat-resume-send-click","p":"39327099"}
        // 返回：{"code":0,"message":"Success","zpData":true}

        // post
        // https://www.zhipin.com/wapi/zpchat/exchange/test
        // 参数：securityId: rQBRf3x7Q9jxj-V1LNTb_3OK_9ZPZc2bKx-_kRQLTdr1Yd9RaBEQDil1oG0MJe3TfYIvzMyJh-n4Lr0qaeaQhGVLBtWnFCPhss0X3kSmGn6r777fRFrIqms~
        //      type: 3
        // 返回：{"code":0,"message":"Success","zpData":{"alertType":0,"type":3,"status":0}}

        // get
        // https://www.zhipin.com/wapi/zpgeek/resume/attachment/checkbox.json
        // 参数：无
        // 返回：{
        //    "code": 0,
        //    "message": "Success",
        //    "zpData": {
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
        //}

        ZpResumeListResultVo zpResumeListResultVo = instance.checkSendResume(zpMsgBossDataResultVo.getData().getBossId(), zpMsgBossDataResultVo.getData().getSecurityId(), true);

        // 发送简历
        // post
        // https://www.zhipin.com/wapi/zpchat/exchange/request
        // 参数：securityId: rQBRf3x7Q9jxj-V1LNTb_3OK_9ZPZc2bKx-_kRQLTdr1Yd9RaBEQDil1oG0MJe3TfYIvzMyJh-n4Lr0qaeaQhGVLBtWnFCPhss0X3kSmGn6r777fRFrIqms~
        //      type: 3
        //      encryptResumeId: 376961d20775af2c1nB62Nq9EVpUwI-4VPOe
        //      mid:
        // 返回：{"code":0,"message":"Success","zpData":{"type":3,"status":0}}
        ThreadUtil.sleep(3000);
        instance.sendResume(zpMsgBossDataResultVo.getData().getSecurityId(), zpResumeListResultVo.getResumeList().getFirst().getResumeId());
    }
}
