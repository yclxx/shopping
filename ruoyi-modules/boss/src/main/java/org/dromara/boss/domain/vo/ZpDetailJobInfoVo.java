package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * 详情中的岗位信息
 *
 * @author xiexi
 * @description
 * @date 2024/11/10 17:51
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpDetailJobInfoVo {
    //        "encryptId":"40e30d5b49f355531H1y29-7FFtS",
    //        "encryptUserId":"3ed72fe18cbbb5521XR_3dq1Elc~",
    //        "invalidStatus":false,
    //        "jobName":"Android应用工程师",
    //        "position":100202,
    //        "positionName":"Android",
    //        "location":101280100,
    //        "locationName":"广州",
    //        "experienceName":"1-3年",
    //        "degreeName":"本科",
    //        "jobType":0,
    //        "proxyJob":0,
    //        "proxyType":0,
    //        "salaryDesc":"12-15K·13薪",
    //        "payTypeDesc":null,
    //        "postDescription":"工作职责：\n1、负责Android和IOS应用开发与迭代，完成软件设计、开发、调试等工作。\n2、负责项目的质量优化、架构和难点攻关等工作，提升产品基础质量。\n3、研究行业新兴技术，满足产品需求。\n\n岗位职责：\n1、具有1年以上安卓平台开发经验、计算机相关专业、本科及以上学历。\n2、熟悉Java，在数据结构、软件设计方等方面有扎实的技术功底。\n3、熟悉Android SDK，熟悉Android UI设计规范和系统UI实现原理。\n4、熟悉网络开发、多线程开发、进程间通信等知识。",
    //        "encryptAddressId":"9f839c3f2797d9e40XR929-0Elc~",
    //        "address":"广州白云区广州飞傲电子科技有限公司白云区龙归街夏良龙良路21号",
    //        "longitude":113.324126,
    //        "latitude":23.283803,
    //        "staticMapUrl":"https://img.bosszhipin.com/beijin/amap/staticMap/e0fc42066597daefea2bd7435052c017",
    //        "pcStaticMapUrl":"https://img.bosszhipin.com/beijin/amap/staticMap/86d1df74fd944818798e908e38e4815a",
    //        "overseasAddressList":[],
    //        "overseasInfo":null,
    //        "showSkills":[
    //        "Java",
    //        "Android客户端产品研发"
    //        ],
    //        "anonymous":0,
    //        "jobStatusDesc":"最新"
    private String encryptId;
    private String encryptUserId;
    private Boolean invalidStatus;
    private String jobName;
    private Long position;
    private String positionName;
    private Long location;
    private String locationName;
    private String experienceName;
    private String degreeName;
    private Long jobType;
    private Long proxyJob;
    private Long proxyType;
    private String salaryDesc;
    private String payTypeDesc;
    private String postDescription;
    private String encryptAddressId;
    private String address;
    private Double longitude;
    private Double latitude;
    private String staticMapUrl;
    private String pcStaticMapUrl;
    private Object[] overseasAddressList;
    /**
     * {
     * "countryList": [
     * "日本"
     * ],
     * "languageList": [
     * "日语"
     * ],
     * "duration": "长期驻外",
     * "overseasWelfareList": [
     * "当地公共假期"
     * ]
     * }
     */
    private Object overseasInfo;
    private String[] showSkills;
    private Long anonymous;
    private String jobStatusDesc;
}
