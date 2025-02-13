package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/11/10 17:52
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpDetailBrandComInfoVo {
    //        {
    //            "encryptBrandId": "e638385a55d4ca590nB62ty0FQ~~",
    //                "brandName": "广州飞傲电子科技...",
    //                "logo": "https://img.bosszhipin.com/beijin/mcs/chatphoto/20190809/6821bf6c65abefb0468f3926d5669a8fa3b593af0cd47c75b555cfb87793e4e0_s.jpg",
    //                "stage": 801,
    //                "stageName": "未融资",
    //                "scale": 303,
    //                "scaleName": "100-499人",
    //                "industry": 101304,
    //                "industryName": "其他行业",
    //                "introduce": "广州飞傲电子科技有限公司成立于2007年，是一家专注于高品质HiFi设备的创新型科技企业。一直以来，飞傲都致力于自主研发生产，目前拥有品牌“FiiO（飞傲）”和主打年轻、时尚、高性价比的电子科技品牌“JadeAudio（翡声）”。\n         目前公司有300多员工，核心研发团队拥有近30年音频产品研发经验的核心研发团队，包括软件、硬件、工业设计、结构、电声等多个专业的研发队伍达到了100多人。\n         公司2022年迁入15000平方米的独立工业园，包含2000平研发中心、2000平营销中心、8000平制造中心。在产品生产过程中，我们追求高效化、科技化，配备先进齐全的设备，如价值20万的示波器、30万元的B&K仿真人头、50万元AP测试仪、百万级的OTA微波暗室等。公司全新升级的无尘车间，4条双向总装流水线，年产量可达200万台，并预留了200万台的产能扩充场地。\n          飞傲不仅致力于自主研发生产，还在海内外发展本地代理商，目前已覆盖包括亚洲、美洲、欧洲、大洋洲和非洲的五个大洲60多个国家和地区，并在行业内取得了良好的口碑。\n            公司关注每一位员工的成长，尊重员工、关爱员工，通过丰富多彩的员工集体活动，形成良好企业文化氛围，与员工共谋发展、共享美好。自公司创办以来，公司内部员工稳定性高，人员流动率较低。随着公司规模的扩大，在维持优秀老员工稳定性的同时，我们也在不断注入新鲜血液，广纳贤才，欢迎有志之士加入飞傲大家庭。",
    //                "labels": [
    //            "五险一金",
    //                    "定期体检",
    //                    "年终奖",
    //                    "带薪年假",
    //                    "员工旅游",
    //                    "节日福利"
    //            ],
    //            "activeTime": 1731059980000,
    //                "visibleBrandInfo": true,
    //                "focusBrand": false,
    //                "customerBrandName": "广州飞傲电子科技...",
    //                "customerBrandStageName": "未融资"
    //        }
    private String encryptBrandId;
    private String brandName;
    private String logo;
    private Long stage;
    private String stageName;
    private Long scale;
    private String scaleName;
    private Long industry;
    private String industryName;
    private String introduce;
    private String[] labels;
    private Long activeTime;
    private Boolean visibleBrandInfo;
    private Boolean focusBrand;
    private String customerBrandName;
    private String customerBrandStageName;
}
