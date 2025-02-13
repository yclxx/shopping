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
public class ZpMsgHistoryFromVo {
    /*
    {
                "uid": 618506805,
                "headImg": 0,
                "name": "姚先生",
                "company": "",
                "avatar": "https://img.bosszhipin.com/boss/avatar/avatar_16.png",
                "source": 0,
                "certification": 0
            }
     */
    private Long uid;
    private Long headImg;
    private String name;
    private String company;
    private String avatar;
    private Long source;
    private Long certification;
}
