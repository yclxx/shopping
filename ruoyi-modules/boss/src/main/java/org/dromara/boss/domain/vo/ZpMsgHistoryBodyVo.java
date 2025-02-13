package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/17 16:41
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpMsgHistoryBodyVo {
    /*
    "type": 8,
        "templateId": 1,
        "jobDesc": {
            "education": "本科",
            "boss": {
                "uid": 618506805,
                "headImg": 0,
                "name": "姚先生",
                "company": "",
                "avatar": "https://img.bosszhipin.com/boss/avatar/avatar_16.png",
                "source": 0,
                "certification": 0
            },
            "distance": "",
            "city": "广州 番禺区 大石",
            "lid": "",
            "partTimeDesc": "",
            "expectId": 0,
            "title": "JAVA开发",
            "salary": "1.4-1.7万元",
            "experience": "3-5年",
            "bottomText": "11月16日 19:38 向你发起的沟通",
            "content": "岗位要求：\n1、计算机相关专业，具备扎实的操作系统、数据库、网络基础专业能力；\n2、具备良好的编码风格和开发习惯，扎实的Java基础，熟练掌握Java开发技术细节和各类常用数据结构和算法，具有架构设计能力；\n3、熟练使用springboot、SpringCloud等开发框架；",
            "jobLabel": "",
            "latlon": "",
            "expectPosition": "",
            "company": "德科信息有限公司",
            "url": "bosszp://bosszhipin.app/openwith?type=jobview&jid=396459930&uid=618506805&securityId=H2uOeA4AUkf0y-_1UtRZhefgUVf4BDj0H61CWEWY58ZBEVpQVbiGlOqjovLv639Ve0wGxkJmxFXZe_HR6dURMpJxvmcuAir9bFU2usNVdS4GCmYNm65STwEGsJSA9yeu9XE-HwnFYqZsEvr8u-8UbMZDHNXGQMZuFJdvRuPyOTdaeDCJLn5fubJPUOvNNn9ffOGQjbur0SDmKx9xc5NJPVektpgJJYepnom-SgxImNJCOFsmWo4nOBUDGIh22VIn_LQmFreTH0Qq96ZWK5G8OBfTNBG2XB3kmbiW_BpLtlOabfxve_AiVRADIloppxsXzCply5T8MFisjYbGAD1FM4CXDToLM-OHIeGNoCw8GMs0B1A_dRf3AcPsNfYW3DCwlsroyIIxEzNyMOYyw5byJzdJG2cl_9SMZCE1m6S6f-UP4DuHhqWjbddv39ufEptV-cyOVBisywuzE7dAPfsFcp39shuuqzgl4LsVF8Toehc5qxdmotFpWPV7J6lnK0abjFsldH63IuLl91XxjA4~",
            "labels": [
                "Java",
                "Spring",
                "SpringCloud"
            ],
            "extend": "{\"jobType\":0}",
            "jobId": 396459930,
            "stage": "不需要融资",
            "geek": {
                "uid": 79775643,
                "headImg": 0,
                "name": "",
                "company": "",
                "avatar": "",
                "source": 0,
                "certification": 0
            },
            "iconFlag": 0,
            "positionCategory": "Java",
            "bossTitle": "招聘总监",
            "expectSalary": ""
        },
        "headTitle": "Boss姚先生希望就如下职位与您沟通"
     */
    private Long type;
    private Long templateId;
    private ZpMsgHistoryBodyJobDesc jobDesc;
    private String headTitle;
}
