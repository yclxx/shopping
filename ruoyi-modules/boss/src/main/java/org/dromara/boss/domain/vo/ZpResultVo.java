package org.dromara.boss.domain.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import lombok.Data;
import org.dromara.common.json.utils.JsonUtils;

/**
 * @author xiexi
 * @description
 * @date 2024/11/9 18:45
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class ZpResultVo {
    private Long code;
    private String message;
    private Object zpData;

    public <T> T getZpData(Class<T> clazz) {
        if (null == zpData) {
            return null;
        }
        String json = JsonUtils.toJsonString(zpData);
        return JsonUtils.parseObject(json, clazz);
    }

    public <T> T getZpData(TypeReference<T> typeReference) {
        if (null == zpData) {
            return null;
        }
        String json = JsonUtils.toJsonString(zpData);
        return JsonUtils.parseObject(json, typeReference);
    }
}
