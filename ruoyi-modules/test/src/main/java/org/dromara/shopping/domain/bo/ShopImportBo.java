package org.dromara.shopping.domain.bo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

@Data
@ExcelIgnoreUnannotated
public class ShopImportBo {

    private static final long serialVersionUID = 1L;

    /**
     * 品牌（商户）
     */
    @ExcelProperty(value = "品牌（商户）")
    private String commercialTenantName;

    /**
     * 门店名称
     */
    @ExcelProperty(value = "门店名称")
    private String shopName;

    /**
     * 门店地址
     */
    @ExcelProperty(value = "门店地址")
    private String address;

    /**
     * 门店电话
     */
    @ExcelProperty(value = "门店电话")
    private String shopTel;

    /**
     * 营业时间
     */
    @ExcelProperty(value = "营业时间")
    private String businessHours;

    /**
     * 云闪付商户号
     */
    @ExcelProperty(value = "云闪付商户号(多个之间用英文逗号隔开)")
    private String ysfMerchantNo;

    /**
     * 支付宝商户号
     */
    @ExcelProperty(value = "支付宝商户号(多个之间用英文逗号隔开)")
    private String zfbMerchantNo;

    /**
     * 微信商户号
     */
    @ExcelProperty(value = "微信商户号(多个之间用英文逗号隔开)")
    private String weChatMerchantNo;

    /**
     * 云闪付商户号
     */
    @ExcelProperty(value = "终端号")
    private String terminalNo;
}
