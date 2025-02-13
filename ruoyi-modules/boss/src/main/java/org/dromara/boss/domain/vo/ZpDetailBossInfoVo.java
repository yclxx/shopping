package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * boos 信息
 *
 * @author xiexi
 * @description
 * @date 2024/11/10 17:32
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpDetailBossInfoVo {
    //        {
    //            "name": "何柳艳",
    //            "title": "招聘职位",
    //            "tiny": "https://img.bosszhipin.com/beijin/upload/avatar/20240622/607f1f3d68754fd00a4c8ea88c5b3b7355ea2440dbf28f2aa3b593af0cd47c75b555cfb87793e4e0_s.jpg.webp",
    //            "large": "https://img.bosszhipin.com/beijin/upload/avatar/20240622/607f1f3d68754fd00a4c8ea88c5b3b7355ea2440dbf28f2aa3b593af0cd47c75b555cfb87793e4e0.jpg.webp",
    //            "activeTimeDesc": "3日内活跃",
    //            "bossOnline": false,
    //            "brandName": "广州飞傲电子科技...",
    //            "bossSource": 0,
    //            "certificated": true,
    //            "tagIconUrl": null,
    //            "avatarStickerUrl": null
    //        }
    private String name;
    private String title;
    private String tiny;
    private String large;
    private String activeTimeDesc;
    private Boolean bossOnline;
    private String brandName;
    private Long bossSource;
    private Boolean certificated;
    private String tagIconUrl;
    private String avatarStickerUrl;
}
