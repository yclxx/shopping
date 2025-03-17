package org.dromara.shopping.domain.vo;

import lombok.Data;

@Data
public class OcrBizLicenseVo {
    /** 错误码 */
    private Integer errcode;
    /** 错误信息 */
    private String errmsg;
    /** 注册号 统一社会信用代码 */
    private String reg_num;
    /** 法定代表人姓名 */
    private String legal_representative;
    /** 企业名称 */
    private String enterprise_name;
    /** 经营场所/企业住所 */
    private String address;
    /** 公司类型 */
    private String type_of_enterprise;
    /** 经营范围 */
    private String business_scope;
    /** 注册资本 */
    private String registered_capital;
    /** 注册日期/成立日期 */
    private String registered_date;
}
