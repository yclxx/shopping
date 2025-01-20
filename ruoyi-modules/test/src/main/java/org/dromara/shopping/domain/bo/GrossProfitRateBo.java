package org.dromara.shopping.domain.bo;

import lombok.Data;

import java.util.List;

/**
 * @author xiexi
 * @description
 * @date 2024/8/14 10:51
 */
@Data
public class GrossProfitRateBo {
    /**
     * 查询方式，1：按城市，2：按分类，3：分类+城市
     */
    private String queryType;
    /**
     * 平台
     */
    private Long platformKey;
    /**
     * 类别id
     */
    private Long categoryId;
    /**
     * 类别名称
     */
    private String categoryName;
    /**
     * 类别id集合
     */
    private List<Long> categoryIds;
    /**
     * 省份 编码
     */
    private String procode;
    /**
     * 省份名称
     */
    private String province;
}
