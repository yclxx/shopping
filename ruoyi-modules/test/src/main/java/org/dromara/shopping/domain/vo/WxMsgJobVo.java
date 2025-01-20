package org.dromara.shopping.domain.vo;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class WxMsgJobVo {
    private String templateId;
    private String page;
    private List<Long> userIds;
    private Map<String, String> msgMap;
}
