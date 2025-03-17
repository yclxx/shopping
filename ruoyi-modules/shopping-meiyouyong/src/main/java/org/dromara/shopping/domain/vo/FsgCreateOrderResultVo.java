package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.Data;

import java.io.Serializable;

/**
 * @author xiexi
 * @description
 * @date 2024/8/9 18:06
 */
@Data
@ExcelIgnoreUnannotated
public class FsgCreateOrderResultVo implements Serializable {
    private String orderId;
    private String payH5Url;
    private String payAppUrl;
}
