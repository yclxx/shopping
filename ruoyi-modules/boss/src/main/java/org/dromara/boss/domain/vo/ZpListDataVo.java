package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

/**
 * @author xiexi
 * @description
 * @date 2024/11/9 18:43
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpListDataVo {
    /**
     * 是否有更多
     */
    private Boolean hasMore;
    private Long type;
    /**
     * 职位列表
     */
    private List<ZpListJobInfoVo> jobList;
}
