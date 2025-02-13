package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * @author xiexi
 * @description
 * @date 2024/12/4 11:10
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpDetailHandicappedInfoVo {
    /*
    {
            "onlyAccept": false,
            "detailInfos": [
                {
                    "icon": "https://img.bosszhipin.com/beijin/icon/9db3de8ea9a1853ab1a00ba4746690019baf293ffd3c9d784f125c8bc60422b7.png?darkmode=https://img.bosszhipin.com/beijin/icon/db428f8b77002963e999b6b80671c5fb9baf293ffd3c9d784f125c8bc60422b7.png",
                    "title": "听力残疾",
                    "content": "该职位支持三级及四级障碍人群及以下"
                }
            ]
        }
     */
    private Boolean onlyAccept;
    private Object[] detailInfos;
}
