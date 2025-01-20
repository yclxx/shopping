package org.dromara.shopping.domain.vo;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;



/**
 * 平台渠道配置视图对象
 *
 * @author yzg
 * @date 2023-10-12
 */
@Data
@ExcelIgnoreUnannotated
public class PlatformChannelVo {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @ExcelProperty(value = "ID")
    private Long id;

    /**
     * 平台标识
     */
    @ExcelProperty(value = "平台标识")
    private Long platformKey;

    /**
     * 小程序标题
     */
    @ExcelProperty(value = "小程序标题")
    private String platformTitle;

    /**
     * appId
     */
    @ExcelProperty(value = "appId")
    private String appId;

    /**
     * 小程序ID
     */
    @ExcelProperty(value = "小程序ID")
    private String encryptAppId;

    /**
     * 密钥
     */
    @ExcelProperty(value = "密钥")
    private String secret;

    /**
     * 对称密钥
     */
    @ExcelProperty(value = "对称密钥")
    private String symmetricKey;

    /**
     * rsa签名私钥
     */
    @ExcelProperty(value = "rsa签名私钥")
    private String rsaPrivateKey;

    /**
     * rsa签名公钥
     */
    @ExcelProperty(value = "rsa签名公钥")
    private String rsaPublicKey;

    /**
     * 渠道：0-云闪付，1-微信小程序
     */
    @ExcelProperty(value = "渠道：0-云闪付，1-微信小程序")
    private String channel;

    /**
     * 默认支付商户
     */
    @ExcelProperty(value = "默认支付商户")
    private Long merchantId;


}
