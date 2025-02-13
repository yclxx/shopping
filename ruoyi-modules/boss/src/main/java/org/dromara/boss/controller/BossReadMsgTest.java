package org.dromara.boss.controller;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.RandomUtil;
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
public class BossReadMsgTest {

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

        ZpUserInfoVo zpUserInfoVo = instance.queryUserInfo(true);
        if (null == zpUserInfoVo) {
            return;
        }

        ZpMsgListResultVo zpMsgListResultVo = instance.queryMsgList(true);

        // 取前100条
        List<ZpMsgFriendVo> zpMsgFriendVos = zpMsgListResultVo.getFriendList().subList(0, 100);

        // 获取friendId
        String friendIds = zpMsgFriendVos.stream().map(ZpMsgFriendVo::getFriendIdStr).collect(Collectors.joining(","));

        List<ZpMsgInfoListVo> zpMsgInfoListVos = instance.queryMsgInfoList(friendIds, true);

        int msgCount = 0;
        for (ZpMsgInfoListVo zpMsgInfoListVo : zpMsgInfoListVos) {
            // 排除最后一条消息是自己发送的
            if (zpUserInfoVo.getUserId().equals(zpMsgInfoListVo.getLastMessageInfo().getFromId())) {
                continue;
            }
            // 校验最后一条消息时间
            if (zpMsgInfoListVo.getLastTS() < System.currentTimeMillis() - 1000 * 60 * 60 * 24) {
                continue;
            }
            // 跳过 对方已同意，您的附件简历已发送给对方 该类型消息
            if (zpMsgInfoListVo.getRelationType() == 3) {
                continue;
            }
            // 排除最后一条消息是简历文件
            if (zpMsgInfoListVo.getLastMsg().contains(".pdf")) {
                continue;
            }

            boolean sendResumeFlag = instance.checkMsgSendResume(zpMsgInfoListVo.getLastMsg());
            if (sendResumeFlag) {
                // 简单粗暴一点，最后一条消息和简历相关，就直接发送简历
                log.info(zpMsgInfoListVo.getLastMsg());

                int sleepTime = (RandomUtil.getRandom().nextInt(10) + 10) * 1000;
                ThreadUtil.sleep(sleepTime);

                // 返回结果中的securityId 发送简历需要
                ZpMsgBossDataResultVo zpMsgBossDataResultVo = instance.queryBossData(zpMsgInfoListVo.getEncryptBossId(), zpMsgInfoListVo.getSecurityId(), true);

                List<ZpMsgHistoryListVo> zpMsgHistoryListVos = instance.queryMsgInfo(zpMsgInfoListVo.getEncryptBossId(), zpMsgInfoListVo.getSecurityId(), true);

                // 校验薪资是否符合
                boolean salary = instance.checkSalary(zpMsgBossDataResultVo.getJob().getSalaryDesc(), 15L, 18L);
                if (salary == false) {
                    continue;
                }

                ZpResumeListResultVo zpResumeListResultVo = instance.checkSendResume(zpMsgBossDataResultVo.getData().getBossId(), zpMsgBossDataResultVo.getData().getSecurityId(), true);

                ThreadUtil.sleep(2000);
                instance.sendResume(zpMsgBossDataResultVo.getData().getSecurityId(), zpResumeListResultVo.getResumeList().getFirst().getResumeId());

                msgCount++;
            }

            // 获取最新boss发送的消息
//            int sleepTime = (RandomUtil.getRandom().nextInt(10) + 10) * 1000;
//            ThreadUtil.sleep(sleepTime);
//
//            // 获取会话中boss信息
//            // 返回结果中的securityId 发送简历需要
//            ZpMsgBossDataResultVo zpMsgBossDataResultVo = instance.queryBossData(zpMsgInfoListVo.getEncryptBossId(), zpMsgInfoListVo.getSecurityId(), true);
//
//            List<ZpMsgHistoryListVo> zpMsgHistoryListVos = instance.queryMsgInfo(zpMsgInfoListVo.getEncryptBossId(), zpMsgInfoListVo.getSecurityId(), true);
//
//            // 校验薪资是否符合
//            boolean salary = instance.checkSalary(zpMsgBossDataResultVo.getJob().getSalaryDesc(), 15L, 18L);
//            if (salary == false) {
//                continue;
//            }
        }
        log.info("共{}条消息", msgCount);
    }
}
