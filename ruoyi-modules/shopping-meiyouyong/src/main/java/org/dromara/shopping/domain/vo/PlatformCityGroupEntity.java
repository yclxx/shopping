package org.dromara.shopping.domain.vo;

import lombok.Data;

@Data
public class PlatformCityGroupEntity {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long platformKey;
    private String name;
    private String cityCode;
    private String groupImages;
    private String status;
}
