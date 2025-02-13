package org.dromara.boss.domain.bo;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * 初始化boss请求参数
 *
 * @author xiexi
 * @description
 * @date 2024/11/17 13:54
 */
@Data
public class InitZpUtilsBo {
    private String cookie;
    private String zpToken;
    private String token;
    private String traceId;

    /**
     * 获取请求头信息
     *
     * @return 请求头信息
     */
    public Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("cookie", cookie);
        headers.put("zp_token", zpToken);
        headers.put("token", token);
        headers.put("traceid", traceId);

        return headers;
    }
}
