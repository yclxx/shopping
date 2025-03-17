package org.dromara.shopping.domain.vo;

import lombok.Data;

@Data
public class OcrBizLicenseYsfVo {
    /** 商户编号 */
    private String merchantNo;
    /** 终端编号 */
    private String terminalNo;
    /** 收单机构 */
    private String collectCompany;

}
