package org.dromara.boss.utils;

import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.dromara.boss.domain.vo.*;
import org.dromara.common.core.exception.ServiceException;
import org.dromara.common.core.utils.StringUtils;
import org.dromara.common.json.utils.JsonUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author xiexi
 * @description
 * @date 2024/11/9 19:23
 */
@Slf4j
public class ZpUtils {
    @Getter
    private boolean initHeaders = false;
    /**
     * 请求头，全局配置
     */
    private Map<String, String> headers;
    private static volatile ZpUtils instance;

    private final List<String> resumeBeforeKeyword = List.of(new String[]{"发"});

    private ZpUtils() {
    }

    public static ZpUtils getInstance() {
        return getInstance(null);
    }

    public static ZpUtils getInstance(Map<String, String> headers) {
        if (null == instance) {
            synchronized (ZpUtils.class) {
                if (null == instance) {
                    instance = new ZpUtils();
                }
            }
        }
        if (ObjectUtil.isNotEmpty(headers)) {
            instance.setHeaders(headers);
            instance.initHeaders = true;
        }
        return instance;
    }

    private void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public ZpUserInfoVo queryUserInfo(boolean showLog) {
        String url = "https://www.zhipin.com/wapi/zpuser/wap/getUserInfo.json";

        return sendHttpRequest(Method.GET, url, null, ZpUserInfoVo.class, showLog);
    }

    /**
     * 查询招聘列表
     *
     * @param encryptExpectId 选择的城市
     * @param page            页码
     * @return 招聘列表
     */
    public ZpListDataVo queryZpList(String encryptExpectId, Integer page, boolean showLog) {
        // 请求地址
        String url = "https://www.zhipin.com/wapi/zpgeek/pc/recommend/job/list.json";
        // ?city=&experience=&payType=&partTime=&degree=&industry=&scale=&salary=&jobType=
        // &encryptExpectId=56aa55211a9b5edc1nR93tm4FlNZxA~~&page=1&pageSize=15
        Map<String, Object> params = new HashMap<>();
        params.put("city", "");
        params.put("experience", "");
        params.put("payType", "");
        params.put("partTime", "");
        params.put("degree", "");
        params.put("industry", "");
        params.put("scale", "");
        params.put("salary", "");
        params.put("jobType", "");
        params.put("encryptExpectId", encryptExpectId);
        params.put("page", page);
        params.put("pageSize", 15);

        return sendHttpRequest(Method.GET, url, params, ZpListDataVo.class, showLog);
    }

    /**
     * 查询招聘详情
     *
     * @param securityId 安全id
     * @param lid        lid
     * @return 招聘详情
     */
    public ZpDetailInfoVo queryZpInfoVo(String securityId, String lid, boolean showLog) {
        // 请求地址
        // https://www.zhipin.com/wapi/zpgeek/job/detail.json
        // 参数
        // ?securityId=Yl6DUfvvEpj8b-31YuVndKl6Xx3VYm-81e0uQHCmBLaL8INLJtE0QTepE6JmuZcaVbM5ySzSeX8WcxPMr2L7roqh75DyfcpMD8l_Sw1kuAGxCPhB_Jmx2Fz96orKL21d425vQu53QJn2OZ3umkNn1t5JxfZe5Hsiuz4EGXJ6aB9V9dE51p00G5Ijdz8FCUpsvYRMT3L7-ocfv48kB65_-jOXaybRzCHp3dGJPQ~~&lid=6465a9ca-09f3-4da1-8e31-57ce56756fd7.f1:common.eyJzZXNzaW9uSWQiOiI0Yzc0Y2UwZC1jOTU3LTRlOTgtYmM1OS0yNGU5OWNjM2RmMTMiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.1

        // 返回结果
        // {"code":0,"message":"Success",
        // "zpData":{"pageType":0,"selfAccess":false,"securityId":"sDM8Qq0Qp6He_-21HVG-LEOkL1sx5s6xhoSCC6myW0p0rmg4PrMJKgUXgTPHIRBmEOqCGPak_1gVtEXOMpOyNKAixMFbdJirLuw0IJsMMf-p1-Q8MA7Jue3lcPC33hCel6jnruTx4SDf0D2qvwvxSbxShBYZ6qt9YK9bCAdSywd7aKgwMmsnLz3rygErZJlYrmKUK5vyz_5MqXTQBvx28hZcAad2nuTDj6t4Uw~~"
        // ,"sessionId":null,"lid":"f6c2d8a7-8ab4-4382-8558-360153b21adf.f1:common.eyJzZXNzaW9uSWQiOiI5MWRhMTc4ZC02NzdlLTQ0NjItOTIzYS1lODM5OGIzY2QyYzUiLCJyY2RCelR5cGUiOiJmMV9ncmNkIn0.1"
        // ,"jobInfo":{"encryptId":"40e30d5b49f355531H1y29-7FFtS","encryptUserId":"3ed72fe18cbbb5521XR_3dq1Elc~"
        // ,"invalidStatus":false,"jobName":"Android应用工程师","position":100202,"positionName":"Android"
        // ,"location":101280100,"locationName":"广州","experienceName":"1-3年","degreeName":"本科"
        // ,"jobType":0,"proxyJob":0,"proxyType":0,"salaryDesc":"12-15K·13薪","payTypeDesc":null
        // ,"postDescription":"工作职责：\n1、负责Android和IOS应用开发与迭代，完成软件设计、开发、调试等工作。\n2、负责项目的质量优化、架构和难点攻关等工作，提升产品基础质量。\n3、研究行业新兴技术，满足产品需求。\n\n岗位职责：\n1、具有1年以上安卓平台开发经验、计算机相关专业、本科及以上学历。\n2、熟悉Java，在数据结构、软件设计方等方面有扎实的技术功底。\n3、熟悉Android SDK，熟悉Android UI设计规范和系统UI实现原理。\n4、熟悉网络开发、多线程开发、进程间通信等知识。"
        // ,"encryptAddressId":"9f839c3f2797d9e40XR929-0Elc~","address":"广州白云区广州飞傲电子科技有限公司白云区龙归街夏良龙良路21号"
        // ,"longitude":113.324126,"latitude":23.283803,"staticMapUrl":"https://img.bosszhipin.com/beijin/amap/staticMap/e0fc42066597daefea2bd7435052c017"
        // ,"pcStaticMapUrl":"https://img.bosszhipin.com/beijin/amap/staticMap/86d1df74fd944818798e908e38e4815a"
        // ,"overseasAddressList":[],"overseasInfo":null,"showSkills":["Java","Android客户端产品研发"]
        // ,"anonymous":0,"jobStatusDesc":"最新"}
        // ,"bossInfo":{"name":"何柳艳","title":"招聘职位","tiny":"https://img.bosszhipin.com/beijin/upload/avatar/20240622/607f1f3d68754fd00a4c8ea88c5b3b7355ea2440dbf28f2aa3b593af0cd47c75b555cfb87793e4e0_s.jpg.webp"
        // ,"large":"https://img.bosszhipin.com/beijin/upload/avatar/20240622/607f1f3d68754fd00a4c8ea88c5b3b7355ea2440dbf28f2aa3b593af0cd47c75b555cfb87793e4e0.jpg.webp","activeTimeDesc":"3日内活跃","bossOnline":false,"brandName":"广州飞傲电子科技...","bossSource":0,"certificated":true,"tagIconUrl":null,"avatarStickerUrl":null},"brandComInfo":{"encryptBrandId":"e638385a55d4ca590nB62ty0FQ~~","brandName":"广州飞傲电子科技...","logo":"https://img.bosszhipin.com/beijin/mcs/chatphoto/20190809/6821bf6c65abefb0468f3926d5669a8fa3b593af0cd47c75b555cfb87793e4e0_s.jpg","stage":801,"stageName":"未融资","scale":303,"scaleName":"100-499人","industry":101304,"industryName":"其他行业","introduce":"广州飞傲电子科技有限公司成立于2007年，是一家专注于高品质HiFi设备的创新型科技企业。一直以来，飞傲都致力于自主研发生产，目前拥有品牌“FiiO（飞傲）”和主打年轻、时尚、高性价比的电子科技品牌“JadeAudio（翡声）”。\n         目前公司有300多员工，核心研发团队拥有近30年音频产品研发经验的核心研发团队，包括软件、硬件、工业设计、结构、电声等多个专业的研发队伍达到了100多人。\n         公司2022年迁入15000平方米的独立工业园，包含2000平研发中心、2000平营销中心、8000平制造中心。在产品生产过程中，我们追求高效化、科技化，配备先进齐全的设备，如价值20万的示波器、30万元的B&K仿真人头、50万元AP测试仪、百万级的OTA微波暗室等。公司全新升级的无尘车间，4条双向总装流水线，年产量可达200万台，并预留了200万台的产能扩充场地。\n          飞傲不仅致力于自主研发生产，还在海内外发展本地代理商，目前已覆盖包括亚洲、美洲、欧洲、大洋洲和非洲的五个大洲60多个国家和地区，并在行业内取得了良好的口碑。\n            公司关注每一位员工的成长，尊重员工、关爱员工，通过丰富多彩的员工集体活动，形成良好企业文化氛围，与员工共谋发展、共享美好。自公司创办以来，公司内部员工稳定性高，人员流动率较低。随着公司规模的扩大，在维持优秀老员工稳定性的同时，我们也在不断注入新鲜血液，广纳贤才，欢迎有志之士加入飞傲大家庭。","labels":["五险一金","定期体检","年终奖","带薪年假","员工旅游","节日福利"],"activeTime":1731059980000,"visibleBrandInfo":true,"focusBrand":false,"customerBrandName":"广州飞傲电子科技...","customerBrandStageName":"未融资"},"oneKeyResumeInfo":{"inviteType":0,"alreadySend":false,"canSendResume":true,"canSendPhone":false,"canSendWechat":false},"relationInfo":{"interestJob":false,"beFriend":false},"handicappedInfo":null,"appendixInfo":{"canFeedback":true,"chatBubble":null},"atsOnlineApplyInfo":{"inviteType":0,"alreadyApply":false}}}

        String url = "https://www.zhipin.com/wapi/zpgeek/job/detail.json";

        Map<String, Object> params = new HashMap<>();
        params.put("securityId", securityId);
        params.put("lid", lid);

        return sendHttpRequest(Method.GET, url, params, ZpDetailInfoVo.class, showLog);
    }

    /**
     * 发起沟通
     *
     * @param securityId 安全id
     * @param jobId      工作id
     * @param lid        lid
     * @return 沟通结果
     */
    public ZpAddResultDataVo addZp(String securityId, String jobId, String lid, boolean showLog) {
        // 返回结果
        //        "showGreeting": true,
        //        "greeting": "您好，我对这个岗位以及贵公司都很有兴趣，也觉得岗位非常适合自己，相信自己也能为贵公司提供价值。可以查看我的简历，如果您觉得合适，可以随时联系我，感谢",
        //        "securityId": "3T1vPCUqhocWo-b1NQs23aHSFvsL5jnJlwzoVlyMS9sTqQBWg9NTja_QlQYXWO_gnxgQKeTJpwqChpAorzTEIVfFpls8WS_ul-qfORURtEKuwitp6TBPMA~~",
        //        "bossSource": 0,
        //        "source": "",
        //        "encBossId": "5bbf6635f516a3071XR82tS4EVU~"

        // 请求地址
        // https://www.zhipin.com/wapi/zpgeek/friend/add.json
        // ?securityId=3T1vPCUqhocWo-b1NQs23aHSFvsL5jnJlwzoVlyMS9sTqQBWg9NTja_QlQYXWO_gnxgQKeTJpwqChpAorzTEIVfFpls8WS_ul-qfORURtEKuwitp6TBPMA~~&jobId=c101d100fc253e5f1nB80t-4EFRX&lid=7VagaMw8aHA.search.1

        String url = "https://www.zhipin.com/wapi/zpgeek/friend/add.json";

        Map<String, Object> params = new HashMap<>();
        params.put("securityId", securityId);
        params.put("lid", lid);
        params.put("jobId", jobId);

        try {
            return sendHttpRequest(Method.POST, url, params, ZpAddResultDataVo.class, showLog);
        } catch (Exception e) {
            log.error("投递失败，", e);
            return null;
        }
    }

    /**
     * 获取沟通列表
     *
     * @return {"foldText":"以上是30天内联系人",
     * "filterEncryptIdList":[],
     * "friendList":[{"friendId":683434327,"friendSource":0,"encryptFriendId":"379b084bf0ed28020Xx53965E1BX","name":"杨茜","updateTime":1731671391000,"brandName":"纬创软件"}]
     * }
     */
    public ZpMsgListResultVo queryMsgList(boolean showLog) {
        String url = "https://www.zhipin.com/wapi/zprelation/friend/geekFilterByLabel";

        Map<String, Object> params = new HashMap<>();
        params.put("labelId", 0);

        return sendHttpRequest(Method.GET, url, params, ZpMsgListResultVo.class, showLog);
    }

    /**
     * 获取消息详细列表
     *
     * @param friendIds 消息id 字符串，英文逗号分隔
     */
    public List<ZpMsgInfoListVo> queryMsgInfoList(String friendIds, boolean showLog) {
        String url = "https://www.zhipin.com/wapi/zprelation/friend/getGeekFriendList.json";

        Map<String, Object> params = new HashMap<>();
        params.put("friendIds", friendIds);

        Map<String, List<ZpMsgInfoListVo>> resultMap = sendHttpRequest(Method.GET, url, params, new TypeReference<>() {
        }, showLog);
        return resultMap.get("result");
    }

    /**
     * 聊天列表 boss信息
     *
     * @param bossId     加密bossId
     * @param securityId 安全Id
     * @param showLog    是否打印日志
     * @return boss信息
     */
    public ZpMsgBossDataResultVo queryBossData(String bossId, String securityId, boolean showLog) {
        // https://www.zhipin.com/wapi/zpchat/geek/getBossData?bossId=b1721a63cebb851d1nR-3t60E1NY&bossSource=0&securityId=8b5u1ZZidYzg--o18QYaQPxATFlas7HFdnqKRSQM1WGFv8NybOmlsxTA3lldOboPrWRt58HknFIgwW4bgSEhXL-LkApKwfm1sieNlKyfdyZpaSQvaYUmSPkt9lpJfD2A52jGqXCS7NoWtLRmF7tM1jvdZsaUg2md_gpoBas-mVere18Kk7kqHw~~

        String url = "https://www.zhipin.com/wapi/zpchat/geek/getBossData";

        Map<String, Object> params = new HashMap<>();
        params.put("bossId", bossId);
        params.put("bossSource", 0);
        params.put("securityId", securityId);

        return sendHttpRequest(Method.GET, url, params, ZpMsgBossDataResultVo.class, showLog);
    }

    public List<ZpMsgHistoryListVo> queryMsgInfo(String bossId, String securityId, boolean showLog) {
        // https://www.zhipin.com/wapi/zpchat/geek/historyMsg?bossId=12062437a81cf1c00XJ82dq5GFJR&groupId=12062437a81cf1c00XJ82dq5GFJR&maxMsgId=0&c=20&page=1&src=0&securityId=hf51ZwW2x3HOu-41qsucxMSeRownwvAwGYuu5NSfBLYxQYp5NSP3qiYOpkQAW4yPJPAHg3n5o0ypAdIikS9tcfnvUhqjBDmMRD8fHoyyfRqKbpRfAyCeCq8jbVLkgKbumBpAtl4gt2GQCwW2EkdWwbz2J2ZETbZQtTuffrF_dkEh0XHTiXZ19To~&loading=true&_t=1731831031528
        String url = "https://www.zhipin.com/wapi/zpchat/geek/historyMsg";

        Map<String, Object> params = new HashMap<>();
        params.put("bossId", bossId);
        params.put("groupId", bossId);
        params.put("maxMsgId", 0);
        params.put("c", 20);
        params.put("page", 1);
        params.put("src", 0);
        params.put("securityId", securityId);
        params.put("loading", true);
        params.put("_t", System.currentTimeMillis());

        ZpMsgInfoResultVo zpMsgInfoResultVo = sendHttpRequest(Method.GET, url, params, ZpMsgInfoResultVo.class, showLog);

        return zpMsgInfoResultVo.getMessages();
    }

    /**
     * 检查是否可以发送简历
     *
     * @param bossId     明文bossId 39327099
     * @param securityId 安全Id
     */
    public ZpResumeListResultVo checkSendResume(Long bossId, String securityId, boolean showLog) {
        // 发送简历
        // 三个请求
        // post
        // https://www.zhipin.com/wapi/zpCommon/actionLog/common.json
        // p 为 bossId
        // 参数：ba: {"action":"chat-resume-send-click","p":"39327099"}
        // 返回：{"code":0,"message":"Success","zpData":true}

        String commonUrl = "https://www.zhipin.com/wapi/zpCommon/actionLog/common.json";
        Map<String, Object> commonParams = new HashMap<>();
        commonParams.put("ba", "{\"action\":\"chat-resume-send-click\",\"p\":\"" + bossId + "\"}");

        Boolean b = sendHttpRequest(Method.POST, commonUrl, commonParams, Boolean.class, showLog);
        if (!b) {
            throw new RuntimeException("检查是否可以发送简历失败");
        }

        // post
        // https://www.zhipin.com/wapi/zpchat/exchange/test
        // 参数：securityId: rQBRf3x7Q9jxj-V1LNTb_3OK_9ZPZc2bKx-_kRQLTdr1Yd9RaBEQDil1oG0MJe3TfYIvzMyJh-n4Lr0qaeaQhGVLBtWnFCPhss0X3kSmGn6r777fRFrIqms~
        //      type: 3
        // 返回：{"code":0,"message":"Success","zpData":{"alertType":0,"type":3,"status":0}}

        String exchangeUrl = "https://www.zhipin.com/wapi/zpchat/exchange/test";
        Map<String, Object> exchangeParams = new HashMap<>();
        exchangeParams.put("securityId", securityId);
        exchangeParams.put("type", 3);

        sendHttpRequest(Method.POST, exchangeUrl, exchangeParams, Object.class, showLog);

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

        String attachmentUrl = "https://www.zhipin.com/wapi/zpgeek/resume/attachment/checkbox.json";
        return sendHttpRequest(Method.GET, attachmentUrl, null, ZpResumeListResultVo.class, showLog);
    }

    public void sendResume(String securityId, String encryptResumeId) {
        // 发送简历
        // post
        // https://www.zhipin.com/wapi/zpchat/exchange/request
        // 参数：securityId: rQBRf3x7Q9jxj-V1LNTb_3OK_9ZPZc2bKx-_kRQLTdr1Yd9RaBEQDil1oG0MJe3TfYIvzMyJh-n4Lr0qaeaQhGVLBtWnFCPhss0X3kSmGn6r777fRFrIqms~
        //      type: 3
        //      encryptResumeId: 376961d20775af2c1nB62Nq9EVpUwI-4VPOe
        //      mid:
        // 返回：{"code":0,"message":"Success","zpData":{"type":3,"status":0}}

        String exchangeUrl = "https://www.zhipin.com/wapi/zpchat/exchange/request";
        Map<String, Object> exchangeParams = new HashMap<>();
        exchangeParams.put("securityId", securityId);
        exchangeParams.put("type", 3);
        exchangeParams.put("encryptResumeId", encryptResumeId);
        exchangeParams.put("mid", "");

        sendHttpRequest(Method.POST, exchangeUrl, exchangeParams, Object.class, true);
    }

    public boolean checkSalary(String salaryDesc, Long minSalary, Long maxSalary) {
        if (StringUtils.isBlank(salaryDesc) || !salaryDesc.contains("-")) {
            log.info("薪资{}不符合预期，跳过", salaryDesc);
            return false;
        }
        String[] split = salaryDesc.split("-");
        // 最低薪资
        String minSalaryStr = split[0];
        // 最高薪资 可能是 15K·13薪
        String maxSalaryStr = split[1];
        if (!maxSalaryStr.contains("K")) {
            log.info("薪资单位未识别处理，{}", salaryDesc);
            return false;
        }
        // 截取K前面的数字
        maxSalaryStr = maxSalaryStr.substring(0, maxSalaryStr.indexOf("K"));
        if (!NumberUtil.isInteger(minSalaryStr) || !NumberUtil.isInteger(maxSalaryStr)) {
            log.info("薪资{}不符合预期，跳过", salaryDesc);
            return false;
        }
        // 转整为int
        int minSalaryInt = Integer.parseInt(minSalaryStr);
        int maxSalaryInt = Integer.parseInt(maxSalaryStr);
        if (minSalaryInt < minSalary && maxSalaryInt < maxSalary) {
            log.info("薪资{}不符合预期，跳过", salaryDesc);
            return false;
        }
        return true;
    }

    public boolean checkPositionName(String positionName, String jobPositionName) {
        if (StringUtils.isNotBlank(positionName)) {
            boolean flag = true;
            for (String str : positionName.split(",")) {
                if (StringUtils.isNotBlank(str) && jobPositionName.contains(str)) {
                    flag = false;
                }
            }
            if (flag) {
                log.info("岗位不是{}，跳过,{}", positionName, jobPositionName);
                return false;
            }
        }
        return true;
    }

    public boolean checkMsgSendResume(String msg) {
        if (StringUtils.isBlank(msg)) {
            return false;
        }
        if (!msg.contains("简历")) {
            return false;
        }
        // 截取简历前面的字符串
        String resumeName = StringUtils.substringBefore(msg, "简历");

        for (String keyword : resumeBeforeKeyword) {
            if (resumeName.contains(keyword)) {
                return true;
            }
        }

        return false;
    }

    /**
     * 发送get 请求
     *
     * @param method 请求方式
     * @param url    请求地址
     * @param params 请求参数
     * @param type   返回数据类型
     * @return 返回结果
     */
    private <T> T sendHttpRequest(Method method, String url, Map<String, Object> params, Class<T> type, boolean showLog) {
        ZpResultVo zpResult = baseRequest(method, url, params, showLog);

        checkResponse(zpResult);

        return zpResult.getZpData(type);
    }

    /**
     * 发送get 请求
     *
     * @param method        请求方式
     * @param url           请求地址
     * @param params        请求参数
     * @param typeReference 返回数据类型
     * @return 返回结果
     */
    private <T> T sendHttpRequest(Method method, String url, Map<String, Object> params, TypeReference<T> typeReference, boolean showLog) {
        ZpResultVo zpResult = baseRequest(method, url, params, showLog);

        checkResponse(zpResult);

        return zpResult.getZpData(typeReference);
    }

    private ZpResultVo baseRequest(Method method, String url, Map<String, Object> params, boolean showLog) {
        HttpRequest request = HttpUtil.createRequest(method, url);
        request.addHeaders(headers);

        String result = request.form(params).execute().body();
        if (showLog) {
            log.info("请求url：{}，请求参数：{}，返回结果：{}", url, params, result);
        }

        return JsonUtils.parseObject(result, ZpResultVo.class);
    }

    private void checkResponse(ZpResultVo zpResult) {
        if (null == zpResult) {
            throw new ServiceException("请求boos直聘接口失败，返回结果为空");
        }
        if (0 != zpResult.getCode()) {
            throw new ServiceException(zpResult.getMessage());
        }
    }
}
